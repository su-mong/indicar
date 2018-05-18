package com.iindicar.indicar.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.iindicar.indicar.R;
import com.iindicar.indicar.a1_main.LoginActivity;
import com.iindicar.indicar.a1_main.MainActivity;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by candykick on 2018. 4. 29..
 */

public class KakaoSignupActivity extends Activity {
    private PopupWindow pWindow;
    private Button btnAlertSignUp;
    private Button btnAlertCancel;
    private EditText etName;
    private EditText etEmail1;
    private EditText etEmail2;
    int screenWidth, screenHeight;
public static Activity kakaoAct;
    String id,login_method, name, profile_img_url, email;

    @Override
    protected void onCreate(final Bundle savedInstance) {
        super.onCreate(savedInstance);
        kakaoAct=this;
        Intent intent = getIntent();
        screenWidth = intent.getIntExtra("screenWidth",1440);
        screenHeight = intent.getIntExtra("screenHeight",2560);
        requestMe();
    }

    protected void requestMe() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "Kakao Login Fail : "+errorResult;
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if(result == ErrorCode.CLIENT_ERROR_CODE) { //인터넷 연결이 끊어진 경우.
                    redirectLoginActivitywithFail("인터넷 연결이 끊어졌습니다. 다시 시도해 주세요.");
                } else {
                    redirectLoginActivitywithFail("다음 에러가 발생했습니다 : "+errorResult);
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                redirectLoginActivitywithFail("로그인 세션이 닫혔습니다. 다시 시도해 주세요.");
            }

            @Override
            public void onNotSignedUp() { }

            @Override
            public void onSuccess(UserProfile result) {
                login_method = "kakao";
                name = result.getNickname();
                profile_img_url = result.getProfileImagePath();
                email = result.getEmail();

                try {
                    new CheckUser().execute();
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(),"유저 정보 획득 실패",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {}

    //유저 가입여부 체크
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
                    name = jsonObject.getString("name");
                    profile_img_url = jsonObject.getString("profile_img_url");
                    email = jsonObject.getString("email");

                    SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefLogin.edit();
                    editor.putString("_id",id);
                    editor.putString("login_method",login_method);
                    editor.putString("name",name);
                    editor.putString("profile_img_url",profile_img_url);
                    editor.putString("email",email);
                    editor.commit();

                    Intent intent = new Intent(KakaoSignupActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    KakaoSignupActivity.this.finish();
                } catch(Exception e) {//에러
                    Toast.makeText(getApplicationContext(),ConstClass.strLoginedErr,Toast.LENGTH_SHORT).show();
                }
            } else {//유저 존재하지 않음
                initiatePopupWindow();
            }
        }
    }

    //회원가입
    public class AddUser extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String result;
            try {
                OkHttpClient client = new OkHttpClient();
                RequestBody body = new FormBody.Builder()
                        .add("login_method",login_method)
                        .add("name",name)
                        .add("profile_img_url",profile_img_url)
                        .add("email",email)
                        .build();
                Request request = new Request.Builder()
                        .url(ConstClass.add_User)
                        .post(body)
                        .build();
                Response response = client.newCall(request).execute();
                result = response.body().string();
                response.body().close();
                return result;
            } catch(Exception e) {
                result = "AsyncTask Fail: "+e.toString();
                return result;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            pWindow.dismiss();
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(KakaoSignupActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            KakaoSignupActivity.this.finish();
        }
    }

    //팝업창을 보여주는 함수
    private void initiatePopupWindow() {
        LayoutInflater inflater = (LayoutInflater) KakaoSignupActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        try {
            int popupWidth = (int)Math.round(screenWidth*0.88);
            int popupHeight = (int)Math.round(screenHeight*0.406);

            View layout = inflater.inflate(R.layout.alert_login, (ViewGroup)findViewById(R.id.popup));
            pWindow = new PopupWindow(layout, popupWidth, popupHeight, true);
            pWindow.showAtLocation(layout, Gravity.CENTER, 0,0);
            btnAlertSignUp = (Button) layout.findViewById(R.id.btnAlertSignUp);
            /*RelativeLayout.LayoutParams agreeParams = new RelativeLayout.LayoutParams((int)Math.round(popupWidth*0.33),(int)Math.round(popupHeight*0.174));
            agreeParams.setMargins((int)Math.round(popupWidth*0.109),(int)Math.round(popupHeight*0.735),0,0);
            btnPopAgree.setLayoutParams(agreeParams);*/
            btnAlertCancel = (Button) layout.findViewById(R.id.btnAlertCancel);
            /*RelativeLayout.LayoutParams cancelParams = new RelativeLayout.LayoutParams((int)Math.round(popupWidth*0.33),(int)Math.round(popupHeight*0.174));
            cancelParams.setMargins((int)Math.round(popupWidth*0.552),(int)Math.round(popupHeight*0.735),0,0);
            btnPopCancel.setLayoutParams(cancelParams);*/
            etName = layout.findViewById(R.id.et_AlertName);
            etEmail1 = layout.findViewById(R.id.et_AlertEmail1);
            etEmail2 = layout.findViewById(R.id.et_AlertEmail2);
            pWindow.setTouchable(true);


            String[] emailArr = email.split("@");
            etName.setText(name);
            etEmail1.setText(emailArr[0]);
            etEmail2.setText(emailArr[1]);

            btnAlertSignUp.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = etName.getText().toString();
                    email = etEmail1.getText().toString()+"@"+etEmail2.getText().toString();

                    if(name.getBytes().length > 20) { //닉네임은 한글 10자, 영어 20자 이하여야 함
                        Toast.makeText(getApplicationContext(), ConstClass.strTooLongNickname, Toast.LENGTH_SHORT).show();
                        pWindow.dismiss();

                        UserManagement.requestLogout(new LogoutResponseCallback() {
                            @Override
                            public void onCompleteLogout() {
                                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                KakaoSignupActivity.this.finish();
                            }
                        });
                    } else {
                        SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefLogin.edit();
                        editor.putString("login_method", login_method);
                        editor.putString("name", name);
                        editor.putString("profile_img_url", profile_img_url);
                        editor.putString("email", email);
                        editor.commit();

                        new AddUser().execute();
                    }
                }
            });
            btnAlertCancel.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pWindow.dismiss();

                    if(!Session.getCurrentSession().isClosed()) {
                        UserManagement.requestLogout(new LogoutResponseCallback() {@Override public void onCompleteLogout() {
                            SharedPreferences prefLogin = getApplicationContext().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefLogin.edit();
                            editor.putLong("profileEditDate",0);
                            editor.putString("_id","0");
                            editor.putString("login_method","0");
                            editor.putString("name","0");
                            editor.putString("profile_img_url","0");
                            editor.putString("email","fail");
                            editor.apply();
                        }});}
                    Intent intent = new Intent(KakaoSignupActivity.this,LoginActivity.class);
                    startActivity(intent);
                    KakaoSignupActivity.this.finish();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void redirectLoginActivitywithFail(String strErr) {
        Toast.makeText(getApplicationContext(),strErr,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(KakaoSignupActivity.this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        KakaoSignupActivity.this.finish();
    }
}
