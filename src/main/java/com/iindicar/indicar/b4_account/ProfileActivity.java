package com.iindicar.indicar.b4_account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.ObservableInt;
import android.databinding.ObservableBoolean;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.iindicar.indicar.BaseActivity;
import com.iindicar.indicar.R;
import com.iindicar.indicar.a1_main.LoginActivity;
import com.iindicar.indicar.data.vo.UserVO;
import com.iindicar.indicar.databinding.ActivityProfileBinding;
import com.iindicar.indicar.utils.ConstClass;
import com.iindicar.indicar.utils.DialogUtil;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProfileActivity extends BaseActivity<ActivityProfileBinding> {
    private final int REQUEST_UPDATE_NAME = 100; // request code 이름 수정
    private final int REQUEST_UPDATE_ADDRESS = 101; // request code 주소 수정

    public final ObservableBoolean isEventAlarmOn = new ObservableBoolean();
    public final ObservableBoolean isOtherAlarmOn = new ObservableBoolean();

    private SharedPreferences prefLogin;
    private UserVO userVO = new UserVO();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_profile;
    }

    @Override
    protected void setActionBarImage(ObservableInt centerImageId, ObservableInt leftImageId) {
        centerImageId.set(R.drawable.logo_account);
        leftImageId.set(R.drawable.btn_back);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.setActivity(this);
        binding.setUser(userVO);

        getUserPreference();
        getAlarmPreference();
        initView();
    }

    private void getUserPreference() {
        prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        userVO.setUserId(prefLogin.getString("_id", ""));
        userVO.setUserName(prefLogin.getString("name", ""));
        userVO.setUserEmail(prefLogin.getString("email", ""));
        userVO.setUserAddress(prefLogin.getString("address", ""));
        userVO.setProfileImageUrl(prefLogin.getString("profile_img_url",""));
    }

    private void getAlarmPreference() {
        prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        isEventAlarmOn.set(prefLogin.getBoolean("eventAlarm", false));
        isOtherAlarmOn.set(prefLogin.getBoolean("otherAlarm", false));
    }

    private void initView(){
        // 액션바 뒤로가기 버튼
        actionBarBinding.buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // 이용약관
        binding.btnClause.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,ProfileTerm.class);
                startActivity(intent);
            }
        });

        // 의견 보내기
        binding.btnOpinion.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,ProfileSuggest.class);
                startActivity(intent);
            }
        });
    }

    // 이름 수정 클릭 리스너
    public void onEditNameClicked(){
        if(userVO.getUserId() == null || userVO.getUserId().equals("")){
            showSnackBar(ConstClass.strNotAllowUpdateUser);
            return;
        }

        Intent intent = new Intent(getApplicationContext(), EditProfileActivity.class);
        startActivityForResult(intent, REQUEST_UPDATE_NAME);
    }

    // 주소 수정 클릭 리스너
    public void onEditAddressClicked(){
        if(userVO.getUserId() == null || userVO.getUserId().equals("")){
            showSnackBar(ConstClass.strNotAllowUpdateUser);
            return;
        }

//        Intent intent = new Intent(getApplicationContext(), DaumAddressActivity.class);
//        startActivityForResult(intent, REQUEST_UPDATE_ADDRESS);
    }

    // 이벤트 알람 클릭 리스너
    public void onEventAlarmClicked(){
        isEventAlarmOn.set(!isEventAlarmOn.get()); // toggle button

        SharedPreferences.Editor editor = prefLogin.edit();
        editor.putBoolean("eventAlarm", isEventAlarmOn.get());
        editor.apply();
    }

    // 기본 알람 클릭 리스너
    public void onOtherAlarmClicked(){
        isOtherAlarmOn.set(!isOtherAlarmOn.get()); // toggle button

        SharedPreferences.Editor editor = prefLogin.edit();
        editor.putBoolean("otherAlarm", isOtherAlarmOn.get());
        editor.apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_UPDATE_NAME
                && resultCode == EditProfileActivity.RESULT_UPDATED){ // 유저 이름 수정됨

            showSnackBar(ConstClass.strUserInfoChanged);
            getUserPreference();
        }
//        else if(requestCode == REQUEST_UPDATE_ADDRESS
//                && resultCode == DaumAddressActivity.RESULT_UPDATED){ // 유저 주소 수정됨
//
//            showSnackBar(ConstClass.strUserInfoChanged);
//            getUserPreference();
//        }
    }

    // 회원 탈퇴 혹은 로그아웃 시
    // 이 액티비티를 닫고 로그인 액티비티를 실행한다
    private void startLoginActivity() {
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    // 회원탈퇴 버튼 클릭 리스너
    public void onDeleteAccountClicked() {

        DialogUtil.showDialog(this,
                "회원탈퇴 후에는 계정 정보 복구가 불가합니다.\n정말로 회원을 탈퇴하시겠습니까?",
                "Delete my account.",
                0.9, 0.25,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteAccount();
                    }
                });
    }

    public void deleteAccount(){

        if(Session.getCurrentSession().isClosed() != true) {
            UserManagement.requestUnlink(new UnLinkResponseCallback() {
                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    startLoginActivity();
                }

                @Override
                public void onNotSignedUp() {}

                @Override
                public void onSuccess(Long result) { //탈퇴처리
                    new DeleteUser().execute();
                    Toast.makeText(getApplicationContext(), userVO.getUserName() + ConstClass.strUnlink, Toast.LENGTH_SHORT).show();
                    startLoginActivity();
                }
            });
        } else if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseAuth.getInstance().getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()) {
                        new DeleteUser().execute();
                        Toast.makeText(getApplicationContext(), userVO.getUserName() + ConstClass.strUnlink, Toast.LENGTH_SHORT).show();
                        startLoginActivity();
                    } else {
                        Toast.makeText(getApplicationContext(),"죄송합니다. 탈퇴에 실패했습니다.",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if(AccessToken.getCurrentAccessToken() != null) {
            LoginManager.getInstance().unregisterCallback(new CallbackManager() {
                @Override
                public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
                    new DeleteUser().execute();
                    Toast.makeText(getApplicationContext(), userVO.getUserName() + ConstClass.strUnlink, Toast.LENGTH_SHORT).show();
                    startLoginActivity();
                    return true;
                }
            });
        } else {
            Toast.makeText(getApplicationContext(),"오류가 발생하였습니다.",Toast.LENGTH_SHORT).show();
        }
    }

    // 로그아웃 버튼 클릭 리스너
    public void onLogoutClicked(){

        if(Session.getCurrentSession().isClosed() != true) {
            UserManagement.requestLogout(new LogoutResponseCallback() {
                @Override
                public void onCompleteLogout() {
                    SharedPreferences.Editor editor = prefLogin.edit();
                    editor.putString("profileEditDate",null);
                    editor.putString("_id","0");
                    editor.putString("login_method","0");
                    editor.putString("name","0");
                    editor.putString("profile_img_url","0");
                    editor.putString("email","fail");
                    editor.apply();

                    startLoginActivity();
                }
            });
        } else if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            SharedPreferences.Editor editor = prefLogin.edit();
            editor.putString("profileEditDate",null);
            editor.putString("_id","0");
            editor.putString("login_method","0");
            editor.putString("name","0");
            editor.putString("profile_img_url","0");
            editor.putString("email","fail");
            editor.apply();

            FirebaseAuth.getInstance().signOut();
            startLoginActivity();
        } else if(AccessToken.getCurrentAccessToken() != null) {
            SharedPreferences.Editor editor = prefLogin.edit();
            editor.putString("profileEditDate",null);
            editor.putString("_id","0");
            editor.putString("login_method","0");
            editor.putString("name","0");
            editor.putString("profile_img_url","0");
            editor.putString("email","fail");
            editor.apply();

            LoginManager.getInstance().logOut();
            startLoginActivity();
        } else {
            Toast.makeText(getApplicationContext(),ConstClass.strLoginedErr,Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = prefLogin.edit();
            editor.putString("profileEditDate",null);
            editor.putString("_id","0");
            editor.putString("login_method","0");
            editor.putString("name","0");
            editor.putString("profile_img_url","0");
            editor.putString("email","fail");
            editor.apply();

            startLoginActivity();
        }
    }

    //유저 정보 우리DB에서 삭제
    public class DeleteUser extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String result;
            try {
                OkHttpClient client = new OkHttpClient();
                RequestBody body = new FormBody.Builder()
                        .add("_id", userVO.getUserId())
                        .build();
                Request request = new Request.Builder()
                        .url(ConstClass.delete_User)
                        .post(body)
                        .build();
                Response response = client.newCall(request).execute();
                //result = response.body().string();
                response.body().close();

                SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefLogin.edit();
                editor.putString("profileEditDate",null);
                editor.putString("_id","0");
                editor.putString("login_method","0");
                editor.putString("name","0");
                editor.putString("profile_img_url","0");
                editor.putString("email","fail");
                editor.apply();

                return ConstClass.strUnlink;
            } catch(Exception e) {
                result = ConstClass.strErrwithCode+e.toString();
                return result;
            }
        }
    }

}
