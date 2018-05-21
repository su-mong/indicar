package com.iindicar.indicar.b4_account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.databinding.ObservableInt;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.iindicar.indicar.BaseActivity;
import com.iindicar.indicar.R;
import com.iindicar.indicar.a1_main.LoginActivity;
import com.iindicar.indicar.databinding.ActivityProfileBinding;
import com.iindicar.indicar.utils.ConstClass;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;

import org.json.JSONObject;

import java.util.Observable;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProfileActivity extends BaseActivity<ActivityProfileBinding> {
    int eventAlarmSwitch, otherAlarmSwitch; //0이 알람X, 1이 알람o
    String id, name, email;
    InputMethodManager imm;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_profile;
    }

    @Override
    protected void setActionBarImage(ObservableInt centerImageId, ObservableInt leftImageId) {
        centerImageId.set(R.drawable.logo_account);
        leftImageId.set(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        binding.ivAProfileImage2.setBackground(new ShapeDrawable(new OvalShape()));
        if(Build.VERSION.SDK_INT>=21) {
            binding.ivAProfileImage2.setClipToOutline(true);
        }
        binding.etANickname.setBackgroundColor(Color.TRANSPARENT);
        binding.etAEmail.setBackgroundColor(Color.TRANSPARENT);
        binding.etAAddress.setBackgroundColor(Color.TRANSPARENT);

        final SharedPreferences prefLogin = getApplication().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        id = prefLogin.getString("_id","로그인 실패");

        if(id.equals("로그인 실패")) { //id 손실 시 다시 유저 정보를 찾는다.
            new CheckUser().execute();
        }

        name = prefLogin.getString("name","로그인 실패");
        email = prefLogin.getString("email","로그인 실패");
        binding.etANickname.setText(name);
        binding.etAEmail.setText(email);
        String profile_img_url = prefLogin.getString("profile_img_url","0");
        if(!profile_img_url.equals("0")) {
            Glide.with(getApplicationContext()).load(profile_img_url).into(binding.ivAProfileImage2);
        }

        eventAlarmSwitch = prefLogin.getInt("EventAlarm",1);
        otherAlarmSwitch = prefLogin.getInt("OtherAlarm",0);
        if(eventAlarmSwitch == 0) {
            binding.btnAEventAlarm.setBackgroundResource(R.drawable.btna_eventalarmoff);
        } else if(eventAlarmSwitch == 1) {
            binding.btnAEventAlarm.setBackgroundResource(R.drawable.btna_eventalarmon);
        }
        if(otherAlarmSwitch == 0) {
            binding.btnAOtherAlarm.setBackgroundResource(R.drawable.btna_otheralarmoff);
        } else if(otherAlarmSwitch == 1) {
            binding.btnAOtherAlarm.setBackgroundResource(R.drawable.btna_otheralarmon);
        }

        binding.btnAModify1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                long editdate = prefLogin.getLong("profileEditDate", 0);
                long diff = editdate - System.currentTimeMillis();
                long diffDays = diff / (24 * 60 * 60 * 1000);

                if(editdate !=0 && diffDays < 7) {
                    Toast.makeText(getApplicationContext(),ConstClass.strNotAllowUpdateUser,Toast.LENGTH_SHORT).show();
                    binding.etANickname.setText(name);
                } else {
                    hideKeyBoard();

                    if(binding.etANickname.getText().toString().getBytes().length > 20) { //닉네임은 한글 10자, 영어 20자 이하여야 함
                        Toast.makeText(getApplicationContext(),ConstClass.strTooLongNickname,Toast.LENGTH_SHORT).show();
                        binding.etANickname.setText(name);
                    } else {
                        name = binding.etANickname.getText().toString();
                        new UpdateUser().execute();
                    }
                }
            }
        });
        binding.btnAEventAlarm.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eventAlarmSwitch == 0) {
                    eventAlarmSwitch = 1;
                    binding.btnAEventAlarm.setBackgroundResource(R.drawable.btna_eventalarmon);
                } else if(eventAlarmSwitch == 1) {
                    eventAlarmSwitch = 0;
                    binding.btnAEventAlarm.setBackgroundResource(R.drawable.btna_eventalarmoff);
                }
                SharedPreferences.Editor editor = prefLogin.edit();
                editor.putInt("EventAlarm",eventAlarmSwitch);
                editor.commit();
            }
        });
        binding.btnAOtherAlarm.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otherAlarmSwitch == 0) {
                    otherAlarmSwitch = 1;
                    binding.btnAOtherAlarm.setBackgroundResource(R.drawable.btna_otheralarmon);
                } else if(otherAlarmSwitch == 1) {
                    otherAlarmSwitch = 0;
                    binding.btnAOtherAlarm.setBackgroundResource(R.drawable.btna_otheralarmoff);
                }
                SharedPreferences.Editor editor = prefLogin.edit();
                editor.putInt("OtherAlarm",otherAlarmSwitch);
                editor.commit();
            }
        });
        binding.btnAClause.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,ProfileTerm.class);
                startActivity(intent);
            }
        });
        binding.btnAOpinion.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,ProfileSuggest.class);
                startActivity(intent);
            }
        });
        binding.btnADeleteAccount.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Session.getCurrentSession().isClosed() != true) {
                    UserManagement.requestUnlink(new UnLinkResponseCallback() {
                        @Override
                        public void onSessionClosed(ErrorResult errorResult) {
                            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            ProfileActivity.this.finish();
                        }

                        @Override
                        public void onNotSignedUp() {}

                        @Override
                        public void onSuccess(Long result) { //탈퇴처리
                            new DeleteUser().execute();
                            Toast.makeText(getApplicationContext(),binding.etANickname.getText().toString()+ConstClass.strUnlink,Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            ProfileActivity.this.finish();
                        }
                    });
                } else if(FirebaseAuth.getInstance().getCurrentUser() != null) {
                    FirebaseAuth.getInstance().getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                new DeleteUser().execute();
                                Toast.makeText(getApplicationContext(),binding.etANickname.getText().toString()+ConstClass.strUnlink,Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                ProfileActivity.this.finish();
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
                            Toast.makeText(getApplicationContext(),binding.etANickname.getText().toString()+ConstClass.strUnlink,Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            ProfileActivity.this.finish();
                            return true;
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(),"오류가 발생하였습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.btnALogout2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            ProfileActivity.this.finish();
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
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    ProfileActivity.this.finish();
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
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    ProfileActivity.this.finish();
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

                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    ProfileActivity.this.finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(binding.pbALoading.getVisibility() != View.VISIBLE) {
            super.onBackPressed();
        }
    }

    //유저 정보 업데이트
    public class UpdateUser extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            binding.pbALoading.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            String result;
            try {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.connectTimeout(30, TimeUnit.SECONDS);
                builder.writeTimeout(30, TimeUnit.SECONDS);
                OkHttpClient client = builder.build();

                RequestBody body = new FormBody.Builder()
                        .add("_id",id)
                        .add("name",name)
                        .build();
                Request request = new Request.Builder()
                        .url(ConstClass.update_User)
                        .post(body)
                        .build();
                Response response = client.newCall(request).execute();
                response.body().close();
                return ConstClass.strUserInfoChanged;
            } catch(Exception e) {
                result = ConstClass.strErrwithCode+e.toString();
                return result;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            binding.pbALoading.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
            SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefLogin.edit();
            editor.putString("name",name);
            editor.putLong("profileEditDate",System.currentTimeMillis());
            editor.apply();
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
                        .add("_id",id)
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

    //유저 정보 받아오기
    //간혹, sharedPreference에서 유저 정보가 손실되거나, 제대로 불러오지 못하는 경우가 있다. 이를 대비하기 위한 소스 코드이다.
    public class CheckUser extends AsyncTask<String, Void, String> {
        String strcheckUser;
        @Override
        protected String doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient();

                RequestBody body = new FormBody.Builder()
                        .add("email",email)
                        .build();
                Request request = new Request.Builder()
                        .url(ConstClass.check_User)
                        .post(body)
                        .build();

                Response response = client.newCall(request).execute();
                strcheckUser = response.body().string();
                response.body().close();
                return strcheckUser;
            } catch(Exception e) {
                strcheckUser = "AsyncTask Fail: "+e.toString();
                return strcheckUser;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("onPostExecuteIn",result);
            if(!result.equals("no result")) {//유저 존재
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    id = jsonObject.getString("_id");
                } catch(Exception e) {//에러
                    Toast.makeText(getApplicationContext(),ConstClass.strLoginedErr,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    ProfileActivity.this.finish();
                }
            } else {//유저 존재하지 않음
                Toast.makeText(getApplicationContext(),ConstClass.strLoginedErr,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                ProfileActivity.this.finish();
            }
        }
    }

    //키보드 내리기
    private void hideKeyBoard() {
        imm.hideSoftInputFromWindow(binding.etANickname.getWindowToken(),0);
    }
}
