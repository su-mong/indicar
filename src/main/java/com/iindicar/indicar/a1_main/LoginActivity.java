package com.iindicar.indicar.a1_main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.iindicar.indicar.BaseActivity2;
import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.ActivityLoginBinding;
import com.iindicar.indicar.utils.ConstClass;
import com.kakao.auth.AuthType;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;

import org.json.JSONObject;

import java.util.Arrays;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends BaseActivity2<ActivityLoginBinding> {

    //뒤로가기 버튼을 두 번 클릭시 종료. 이를 구현하기 위한 변수를 선언한다.
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressTime = 0;

    //로그인 관련 변수
    private FirebaseAuth GoogleModule;
    private GoogleSignInClient GoogleClient;
    private CallbackManager FBcallBackManager;
    private SessionCallback KakaoModule;

    //회원가입 메소드
    String id, login_method, name, profile_img_url, email;

    private PopupWindow pWindow;
    private Button btnAlertSignUp;
    private Button btnAlertCancel;
    private EditText etName;
    private EditText etEmail1;
    private EditText etEmail2;
    int screenWidth, screenHeight;
    public static Activity LoginAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        //hasActionBar.set(false);
// Initialize SDK before setContentView(Layout ID)
        //앱 설치 후 첫 실행인지를 체크한다.
        SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        SharedPreferences pref = getSharedPreferences("firstexe", MODE_PRIVATE);
        if (pref.getString("index", "").equals("")) {
            /*Intent i = new Intent(FirstActivity.this,GuideActivity.class);
            startActivity(i);*/
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("index", "execute");
            editor.commit();

            SharedPreferences.Editor editor2 = prefLogin.edit();
            editor2.putInt("EventAlarm", 1);
            editor2.putInt("OtherAlarm", 0);
            editor2.commit();
        }
        LoginAct = this;
        //화면 크기를 구한다.
        WindowManager w = getWindowManager();
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17) {
            try {
                screenWidth = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
                screenHeight = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
            } catch (Exception e) {
            }
        } else if (Build.VERSION.SDK_INT >= 17) {
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                screenWidth = realSize.x;
                screenHeight = realSize.y;
            } catch (Exception e) {
            }
        }

        //3종 로그인 초기화
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .build();
        GoogleClient = GoogleSignIn.getClient(this, gso);
        GoogleModule = FirebaseAuth.getInstance();
        FBcallBackManager = CallbackManager.Factory.create();
        KakaoModule = new SessionCallback();
        Session.getCurrentSession().addCallback(KakaoModule);


        //구글 로그인 버튼
        binding.btnLoginGoogle.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = GoogleClient.getSignInIntent();
                startActivityForResult(intent, 9001);
            }
        });

        binding.btnLoginFacebook.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbLogin();
            }
        });


        //카카오 로그인 버튼
        binding.btnLoginKakao.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session.getCurrentSession().open(AuthType.KAKAO_LOGIN_ALL, LoginActivity.this);
            }
        });


    }


    public void fbLogin() {
        //페이스북 로그인 버튼
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "user_photos", "public_profile"));
        LoginManager.getInstance().registerCallback(FBcallBackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                login_method = "facebook";

                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {//AccessToken.getCurrentAccessToken()
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            String userId = object.getString("id");
                            name = object.getString("name");
                            profile_img_url = "https://graph.facebook.com/" + userId + "/picture";

                            if (object.toString().contains("email")) {
                                email = object.getString("email");
                            } else {
                                email = "";
                            }

                            new CheckUser().execute();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "페이스북 로그인을 취소하셨습니다.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "페이스북 로그인 실패: 1단계", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
            overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
        } else {
            backPressTime = tempTime;
            Toast.makeText(LoginActivity.this, "뒤로 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }

    //각 로그인의 onActivityResult
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 9001) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "구글 로그인 실패", Toast.LENGTH_SHORT);
            }
        } else if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        } else if (FacebookSdk.isFacebookRequestCode(requestCode)) {
            super.onActivityResult(requestCode, resultCode, data);
            FBcallBackManager.onActivityResult(requestCode, resultCode, data);
            return;

        }
    }

    //구글 로그인 구현
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("Individual Car", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        GoogleModule.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser account = GoogleModule.getCurrentUser();
                            login_method = "google";
                            name = account.getDisplayName();
                            profile_img_url = account.getPhotoUrl().toString();
                            email = account.getEmail();

                            try {
                                new CheckUser().execute();
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "유저 정보 획득 실패", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "구글 로그인 오류: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //카카오 로그인 구현
    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            UserManagement.requestMe(new MeResponseCallback() {
                @Override
                public void onFailure(ErrorResult errorResult) {
                    String message = "Kakao Login Fail : " + errorResult;
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                    if (result == ErrorCode.CLIENT_ERROR_CODE) { //인터넷 연결이 끊어진 경우.
                        redirectLoginActivitywithFail("인터넷 연결이 끊어졌습니다. 다시 시도해 주세요.");
                    } else {
                        redirectLoginActivitywithFail("다음 에러가 발생했습니다 : " + errorResult);
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    redirectLoginActivitywithFail("로그인 세션이 닫혔습니다. 다시 시도해 주세요.");
                }

                @Override
                public void onNotSignedUp() {
                }

                @Override
                public void onSuccess(UserProfile result) {
                    login_method = "kakao";
                    name = result.getNickname();
                    profile_img_url = result.getProfileImagePath();
                    email = result.getEmail();

                    try {
                        new CheckUser().execute();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "유저 정보 획득 실패", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException e) {
            if (e != null) {
                Toast.makeText(LoginActivity.this, "카카오톡 로그인을 중지합니다.", Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        }
    }

    //유저 가입여부 체크
    public class CheckUser extends AsyncTask<String, Void, String> {
        String strcheckUser;

        @Override
        protected void onPreExecute() {
            binding.pbLogin.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient();

                RequestBody body = new FormBody.Builder()
                        .add("email", email)
                        .build();
                Request request = new Request.Builder()
                        .url(ConstClass.check_User)
                        .post(body)
                        .build();

                Response response = client.newCall(request).execute();
                strcheckUser = response.body().string();
                response.body().close();
                return strcheckUser;
            } catch (Exception e) {
                strcheckUser = "AsyncTask Fail: " + e.toString();
                return strcheckUser;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("onPostExecuteIn", result);
            if (!result.equals("no result")) {//유저 존재
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    id = jsonObject.getString("_id");
                    Log.d("ddf Login checkUser", id);
                    name = jsonObject.getString("name");
                    profile_img_url = jsonObject.getString("profile_img_url");
                    email = jsonObject.getString("email");

                    SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefLogin.edit();
                    editor.putLong("profileEditDate", 0);
                    editor.putString("_id", id);
                    editor.putString("login_method", login_method);
                    editor.putString("name", name);
                    editor.putString("profile_img_url", profile_img_url);
                    editor.putString("email", email);
                    editor.commit();
                    binding.pbLogin.setVisibility(View.GONE);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                } catch (Exception e) {//에러
                    Log.d("checkuserError", e.toString());
                    Toast.makeText(getApplicationContext(), ConstClass.strLoginedErr, Toast.LENGTH_SHORT).show();
                    binding.pbLogin.setVisibility(View.GONE);
                }
            } else {//유저 존재하지 않음
                initiatePopupWindow();
                binding.pbLogin.setVisibility(View.GONE);
            }
        }
    }

    //회원가입
    public class AddUser extends AsyncTask<String, Void, String> {
        String result;

        @Override
        protected void onPreExecute() {
            binding.pbLogin.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                OkHttpClient client = new OkHttpClient();
                FormBody.Builder formBuilder = new FormBody.Builder()
                        .add("login_method", login_method);
                formBuilder.add("name", name)
                        .add("email", email);
                if(profile_img_url!=null && !profile_img_url.equals("null"))
                        formBuilder.add("profile_img_url", profile_img_url);

                RequestBody body = formBuilder.build();


                Request request = new Request.Builder()
                        .url(ConstClass.add_User)
                        .post(body)
                        .build();
                Response response = client.newCall(request).execute();
                result = response.body().string();
                response.body().close();
                return result;
            } catch (Exception e) {
                result = "AsyncTask Fail: " + e.toString();
                return result;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            pWindow.dismiss();
            Toast.makeText(getApplicationContext(), ConstClass.strAddUserSuccess + result, Toast.LENGTH_SHORT).show();
            binding.pbLogin.setVisibility(View.GONE);

            new CheckUser2().execute();
        }
    }

    //유저 가입여부 체크
    public class CheckUser2 extends AsyncTask<String, Void, String> {
        String strcheckUser;

        @Override
        protected void onPreExecute() {
            binding.pbLogin.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient();

                RequestBody body = new FormBody.Builder()
                        .add("email", email)
                        .build();
                Request request = new Request.Builder()
                        .url(ConstClass.check_User)
                        .post(body)
                        .build();

                Response response = client.newCall(request).execute();
                strcheckUser = response.body().string();
                response.body().close();
                return strcheckUser;
            } catch (Exception e) {
                strcheckUser = "AsyncTask Fail: " + e.toString();
                return strcheckUser;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("onPostExecuteIn", result);
            if (!result.equals("no result")) {//유저 존재
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    id = jsonObject.getString("_id");
                    Log.d("ddf Login checkUser", id);
                    name = jsonObject.getString("name");
                    profile_img_url = jsonObject.getString("profile_img_url");
                    email = jsonObject.getString("email");

                    SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefLogin.edit();
                    editor.putLong("profileEditDate", 0);
                    editor.putString("_id", id);
                    editor.putString("login_method", login_method);
                    editor.putString("name", name);
                    editor.putString("profile_img_url", profile_img_url);
                    editor.putString("email", email);
                    editor.commit();
                    binding.pbLogin.setVisibility(View.GONE);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                } catch (Exception e) {//에러
                    Log.d("checkuserError", e.toString());
                    Toast.makeText(getApplicationContext(), ConstClass.strLoginedErr, Toast.LENGTH_SHORT).show();
                    binding.pbLogin.setVisibility(View.GONE);
                }
            } else {//유저 존재하지 않음
                binding.pbLogin.setVisibility(View.GONE);
            }
        }
    }

    //팝업창을 보여주는 함수
    private void initiatePopupWindow() {
        LayoutInflater inflater = (LayoutInflater) LoginActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        try {
            int popupWidth = (int) Math.round(screenWidth * 0.88);
            int popupHeight = (int) Math.round(screenHeight * 0.406);

            View layout = inflater.inflate(R.layout.alert_login, (ViewGroup) findViewById(R.id.popup));
            pWindow = new PopupWindow(layout, popupWidth, popupHeight, true);
            pWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
            btnAlertSignUp = layout.findViewById(R.id.btnAlertSignUp);
            /*RelativeLayout.LayoutParams agreeParams = new RelativeLayout.LayoutParams((int)Math.round(popupWidth*0.33),(int)Math.round(popupHeight*0.174));
            agreeParams.setMargins((int)Math.round(popupWidth*0.109),(int)Math.round(popupHeight*0.735),0,0);
            btnPopAgree.setLayoutParams(agreeParams);*/
            btnAlertCancel = layout.findViewById(R.id.btnAlertCancel);
            /*RelativeLayout.LayoutParams cancelParams = new RelativeLayout.LayoutParams((int)Math.round(popupWidth*0.33),(int)Math.round(popupHeight*0.174));
            cancelParams.setMargins((int)Math.round(popupWidth*0.552),(int)Math.round(popupHeight*0.735),0,0);
            btnPopCancel.setLayoutParams(cancelParams);*/
            etName = layout.findViewById(R.id.et_AlertName);
            etEmail1 = layout.findViewById(R.id.et_AlertEmail1);
            etEmail2 = layout.findViewById(R.id.et_AlertEmail2);
            pWindow.setTouchable(true);


            if (!email.equals("")) {
                String[] emailArr = email.split("@");
                etName.setText(name);
                etEmail1.setText(emailArr[0]);
                etEmail2.setText(emailArr[1]);
            }

            btnAlertSignUp.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = etName.getText().toString();
                    email = etEmail1.getText().toString() + "@" + etEmail2.getText().toString();

                    if (name.getBytes().length > 20) { //닉네임은 한글 10자, 영어 20자 이하여야 함
                        Toast.makeText(getApplicationContext(), ConstClass.strTooLongNickname, Toast.LENGTH_SHORT).show();
                        pWindow.dismiss();
                        if (login_method.equals("facebook")) {
                            LoginManager.getInstance().logOut();
                        } else if (login_method.equals("google")) {
                            FirebaseAuth.getInstance().signOut();
                        } else if (login_method.equals(("kakao"))) {
                            UserManagement.requestLogout(new LogoutResponseCallback() {
                                @Override
                                public void onCompleteLogout() {
                                }
                            });
                        }
                    } else {
                        SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefLogin.edit();
                        editor.putLong("profileEditDate", 0);
                        editor.putString("login_method", login_method);
                        editor.putString("name", name);
                        editor.putString("profile_img_url", profile_img_url);
                        editor.putString("email", email);
                        editor.commit();
                        Toast.makeText(LoginActivity.this, "add user send param:" + login_method + name + email, Toast.LENGTH_LONG).show();

                        new AddUser().execute();
                    }
                }
            });
            btnAlertCancel.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                    pWindow.dismiss();

                    if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                        FirebaseAuth.getInstance().signOut();
                        SharedPreferences.Editor editor = prefLogin.edit();
                        editor.putLong("profileEditDate", 0);
                        editor.putString("_id", "0");
                        editor.putString("login_method", "0");
                        editor.putString("name", "0");
                        editor.putString("profile_img_url", "0");
                        editor.putString("email", "fail");
                        editor.apply();
                    } else if (AccessToken.getCurrentAccessToken() != null) {
                        SharedPreferences.Editor editor = prefLogin.edit();
                        editor.putLong("profileEditDate", 0);
                        editor.putString("_id", "0");
                        editor.putString("login_method", "0");
                        editor.putString("name", "0");
                        editor.putString("profile_img_url", "0");
                        editor.putString("email", "fail");
                        editor.apply();
                    } else if (!Session.getCurrentSession().isClosed()) {
                        UserManagement.requestLogout(new LogoutResponseCallback() {
                            @Override
                            public void onCompleteLogout() {
                                SharedPreferences prefLogin = getApplicationContext().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = prefLogin.edit();
                                editor.putLong("profileEditDate", 0);
                                editor.putString("_id", "0");
                                editor.putString("login_method", "0");
                                editor.putString("name", "0");
                                editor.putString("profile_img_url", "0");
                                editor.putString("email", "fail");
                                editor.apply();
                            }
                        });
                    }
                    Intent intent = getIntent();

                    finish();
                    startActivity(intent);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void redirectLoginActivitywithFail(String strErr) {
        Toast.makeText(getApplicationContext(), strErr, Toast.LENGTH_SHORT).show();
    }

    protected void requestMe() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "Kakao Login Fail : " + errorResult;
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) { //인터넷 연결이 끊어진 경우.
                    redirectLoginActivitywithFail("인터넷 연결이 끊어졌습니다. 다시 시도해 주세요.");
                } else {
                    redirectLoginActivitywithFail("다음 에러가 발생했습니다 : " + errorResult);
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                redirectLoginActivitywithFail("로그인 세션이 닫혔습니다. 다시 시도해 주세요.");
            }

            @Override
            public void onNotSignedUp() {
            }

            @Override
            public void onSuccess(UserProfile result) {
                login_method = "kakao";
                name = result.getNickname();
                profile_img_url = result.getProfileImagePath();
                email = result.getEmail();

                try {
                    new CheckUser().execute();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "유저 정보 획득 실패", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}