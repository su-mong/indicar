package com.iindicar.indicar.a1_main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
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
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
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
import com.iindicar.indicar.Constant;
import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.ActivityLoginBinding;
import com.iindicar.indicar.utils.LocaleHelper;
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
import com.linecorp.linesdk.auth.LineLoginApi;
import com.linecorp.linesdk.auth.LineLoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Locale;

import io.fabric.sdk.android.Fabric;
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

    //언어 관련 객체
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        binding.setActivity(this);

        //hasActionBar.set(false);
        // Initialize SDK before setContentView(Layout ID)

        //앱 설치 후 첫 실행인지를 체크한다.
        //첫 실행이면 You're New!를 띄우고 그게 아니면 You're Back!을 띄운다.
        SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        SharedPreferences pref = getSharedPreferences("firstexe", MODE_PRIVATE);
        if (pref.getString("index", "").equals("")) {
            /*Intent i = new Intent(FirstActivity.this,GuideActivity.class);
            startActivity(i);*/
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("index", "execute");
            editor.commit();

            Context boardFilterContext = LocaleHelper.setLocale(getApplicationContext());
            resources = boardFilterContext.getResources();
            binding.imageviewLTitle.setImageDrawable(resources.getDrawable(R.drawable.logintitle2));
        } else {
            Context boardFilterContext = LocaleHelper.setLocale(getApplicationContext());
            resources = boardFilterContext.getResources();
            binding.imageviewLTitle.setImageDrawable(resources.getDrawable(R.drawable.logintitle));
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

        //국가별 뷰 초기화
        binding.btnLoginGoogle.setBackground(resources.getDrawable(R.drawable.btn_googlelogin_light));
        binding.btnLoginFacebook.setBackground(resources.getDrawable(R.drawable.btn_facebooklogin));
        if (!LocaleHelper.getLanguage(getApplicationContext()).equals("ko")) {
            binding.btnLoginKakao.setVisibility(View.GONE);
            binding.btnLoginLine.setVisibility(View.VISIBLE);
        } else {
            binding.btnLoginKakao.setVisibility(View.VISIBLE);
            binding.btnLoginLine.setVisibility(View.GONE);
        }
        Constant.locale=LocaleHelper.getLanguage(getApplicationContext());


    }

    //구글 로그인 버튼
    public void googleLogin() {
        Intent intent = GoogleClient.getSignInIntent();
        startActivityForResult(intent, 9001);
    }

    //카카오 로그인 버튼
    public void kakaoLogin() {
        Session.getCurrentSession().open(AuthType.KAKAO_LOGIN_ALL, LoginActivity.this);
    }

    //페이스북 로그인 버튼
    public void fbLogin() {
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
                            profile_img_url = R.string.facebookGraphApi + userId + "/picture";

                            if (object.toString().contains("email")) {
                                email = object.getString("email");
                            } else {
                                email = "";
                            }

                            new CheckUser().execute();
                        } catch (Exception e) {
                            //Toast.makeText(getApplicationContext(), getString(R.string.strErrwithCode)+e.toString(), Toast.LENGTH_SHORT).show();
                            showSnackBar(resources.getString(R.string.strErrwithCode) + e.toString());
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
                //Toast.makeText(getApplicationContext(), "페이스북 로그인을 취소하셨습니다.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                //Toast.makeText(getApplicationContext(), getString(R.string.strErrwithCode) + error.toString(), Toast.LENGTH_SHORT).show();
                showSnackBar(resources.getString(R.string.strErrwithCode) + error.toString());
            }
        });
    }

    //라인 로그인 버튼
    public void lineLogin() {
        try {
            Intent lineIntent = LineLoginApi.getLoginIntent(getApplicationContext(), getString(R.string.line_channel_id));
            startActivityForResult(lineIntent, 7000);
        } catch (Exception e) {
            showSnackBar(resources.getString(R.string.strErrwithCode) + e.toString());
        }
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
            showSnackBar(resources.getString(R.string.strBackpressed));
        }
    }

    //각 로그인의 onActivityResult
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 9001) { //구글
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (Exception e) {
                showSnackBar(resources.getString(R.string.strErrwithCode) + e.toString());
            }
        } else if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        } else if (FacebookSdk.isFacebookRequestCode(requestCode)) {
            super.onActivityResult(requestCode, resultCode, data);
            FBcallBackManager.onActivityResult(requestCode, resultCode, data);
            return;
        } else if (requestCode == 7000) {
            super.onActivityResult(requestCode, resultCode, data);
            LineLoginResult result = LineLoginApi.getLoginResultFromIntent(data);

            switch (result.getResponseCode()) {
                case SUCCESS:
                    Toast.makeText(getApplicationContext(), result.getLineProfile().getDisplayName(), Toast.LENGTH_SHORT).show();
                    break;
                case CANCEL:
                    break;
                default:
                    showSnackBar(resources.getString(R.string.strErrwithCode) + result.getResponseCode().name());
                    break;
            }
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
                                showSnackBar(resources.getString(R.string.strgetUserInfoError) + e.toString());
                            }
                        } else {
                            showSnackBar(resources.getString(R.string.strErrwithCode) + task.getException().toString());
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
                    ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                    if (result == ErrorCode.CLIENT_ERROR_CODE) { //인터넷 연결이 끊어진 경우.
                        showSnackBar(resources.getString(R.string.strNoInternet));
                    } else {
                        showSnackBar(resources.getString(R.string.strErrwithCode) + errorResult.toString());
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    redirectLoginActivitywithFail(resources.getString(R.string.strLoginedErr));
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
                        showSnackBar(resources.getString(R.string.strgetUserInfoError) + e.toString());
                    }
                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException e) {
            if (e != null) {
                Toast.makeText(LoginActivity.this, getString(R.string.strKakaoLoginStop) + e.toString(), Toast.LENGTH_SHORT).show();
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
                        .url(getString(R.string.check_User))
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
            String jsonResult = "";
            String jsonContent= "";
            try {
                JSONObject jsonObject2 = null;
                jsonObject2 = new JSONObject(result);
                jsonResult = jsonObject2.getString("result");
                jsonContent= jsonObject2.getString("content");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (jsonResult.equals("S")) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonContent);
                    id = jsonObject.getString("id");
                    name = jsonObject.getString("name");
                    profile_img_url = jsonObject.getString("profile_img_url");
                    email = jsonObject.getString("email");

                    SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefLogin.edit();
                    editor.putLong("profileEditDate", 0);
                    editor.putString("id", id);
                    editor.putString("login_method", login_method);
                    editor.putString("name", name);
                    editor.putString("profile_img_url", profile_img_url);
                    editor.putString("email", email);
                    editor.putInt("EventAlarm", 1);
                    editor.putInt("OtherAlarm", 0);
                    Locale systemLocale = getApplicationContext().getResources().getConfiguration().locale;
                    editor.putString("locale", systemLocale.getLanguage());
                    editor.commit();
                    binding.pbLogin.setVisibility(View.GONE);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                } catch (Exception e) {//에러
                    Log.d("checkuserError", e.toString());
                    showSnackBar(resources.getString(R.string.strLoginedErr));
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
                if (profile_img_url != null && !profile_img_url.equals("null"))
                    formBuilder.add("profile_img_url", profile_img_url);


                Log.d("profile_img_url", profile_img_url);
                Log.d("email", email);
                Log.d("login_method", login_method);
                RequestBody body = formBuilder.build();


                Request request = new Request.Builder()
                        .url(getString(R.string.add_User))
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

            showSnackBar(resources.getString(R.string.strAddUserSuccess));
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
                        .url(getString(R.string.check_User))
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

            String jsonResult = "";
            String jsonContent= "";
            try {
                JSONObject jsonObject2 = null;
                jsonObject2 = new JSONObject(result);
                jsonResult = jsonObject2.getString("result");
                jsonContent= jsonObject2.getString("content");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (jsonResult.equals("S")) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonContent);
                    id = jsonObject.getString("id");
                    name = jsonObject.getString("name");
                    profile_img_url = jsonObject.getString("profile_img_url");
                    email = jsonObject.getString("email");
                    SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefLogin.edit();
                    editor.putLong("profileEditDate", 0);
                    editor.putString("id", id);
                    editor.putString("login_method", login_method);
                    editor.putString("name", name);
                    editor.putString("profile_img_url", profile_img_url);
                    editor.putString("email", email);
                    editor.putInt("EventAlarm", 1);
                    editor.putInt("OtherAlarm", 0);
                    Locale systemLocale = getApplicationContext().getResources().getConfiguration().locale;
                    editor.putString("locale", systemLocale.getLanguage());
                    editor.commit();
                    binding.pbLogin.setVisibility(View.GONE);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                } catch (Exception e) {//에러
                    Log.d("checkuserError", e.toString());
                    showSnackBar(resources.getString(R.string.strLoginedErr));
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
            ImageView nickTitle;
            ImageView emailTitle;
            int popupWidth = (int) Math.round(screenWidth * 0.88);
            int popupHeight = (int) Math.round(screenHeight * 0.406);

            View layout = inflater.inflate(R.layout.alert_login, (ViewGroup) findViewById(R.id.popup));
            layout.setBackground(resources.getDrawable(R.drawable.alert_login));
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
            nickTitle = layout.findViewById(R.id.ivAl_nick);
            emailTitle = layout.findViewById(R.id.ivAl_email);
            etName = layout.findViewById(R.id.et_AlertName);
            etEmail1 = layout.findViewById(R.id.et_AlertEmail1);
            etEmail2 = layout.findViewById(R.id.et_AlertEmail2);
            pWindow.setTouchable(true);

            //언어별 뷰 셋팅
            nickTitle.setImageDrawable(resources.getDrawable(R.drawable.alert_login_nick));
            emailTitle.setImageDrawable(resources.getDrawable(R.drawable.alert_login_email));

            //회원가입하려는 정보에 이메일이 존재하면 이메일은 수정이 불가능하게 만든다.
            if (email.length() != 0) {
                etEmail1.setClickable(false);
                etEmail1.setFocusable(false);
                etEmail2.setClickable(false);
                etEmail2.setFocusable(false);
                etEmail1.setTextColor(getResources().getColor(R.color.colorAddUserEmail));
                etEmail2.setTextColor(getResources().getColor(R.color.colorAddUserEmail));

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
                        showSnackBar(resources.getString(R.string.strTooLongNickname));
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
                        editor.putString("id", "0");
                        editor.putString("login_method", "0");
                        editor.putString("name", "0");
                        editor.putString("profile_img_url", "0");
                        editor.putString("email", "fail");
                        editor.apply();
                    } else if (AccessToken.getCurrentAccessToken() != null) {
                        SharedPreferences.Editor editor = prefLogin.edit();
                        editor.putLong("profileEditDate", 0);
                        editor.putString("id", "0");
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
                                editor.putString("id", "0");
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
        showSnackBar(strErr);
    }

    public void showSnackBar(String text) {
        Snackbar.make(binding.getRoot(), "" + text, Snackbar.LENGTH_SHORT).show();
    }
}

    /*protected void requestMe() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "Kakao Login Fail : " + errorResult;
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) { //인터넷 연결이 끊어진 경우.
                    Toast.makeText(getApplicationContext,"인터넷 연결이 끊어졌습니다. 다시 시도해 주세요.",Toast.LENGTH_SHORT).show;
                } else {
                    Toast.makeText(getApplicationContext,"다음 에러가 발생했습니다 : " + errorResult,Toast.LENGTH_SHORT).show;
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Toast.makeText(getApplicationContext,"로그인 세션이 닫혔습니다. 다시 시도해 주세요.",Toast.LENGTH_SHORT).show;
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
*/