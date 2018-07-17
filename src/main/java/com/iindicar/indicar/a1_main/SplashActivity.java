package com.iindicar.indicar.a1_main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iindicar.indicar.Constant;
import com.iindicar.indicar.R;
import com.iindicar.indicar.utils.CarDB;
import com.iindicar.indicar.utils.LocaleHelper;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class SplashActivity extends AppCompatActivity {
    //회원가입 메소드
    String id, login_method, name, profile_img_url, email;

    CarDB carDB;
    String version;
    String versionOnline;
    String dbVersion;
    String dbVersionOnline;

    LinearLayout rootView; Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_splash);

        //앱 설치 후 첫 실행인지를 체크한다. 첫 실행이면 알람 셋팅과 국가 셋팅을 미리 해 둔다.
        SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        SharedPreferences pref = getSharedPreferences("firstexe", MODE_PRIVATE);
        if (pref.getString("index", "").equals("")) {
            /*Intent i = new Intent(FirstActivity.this,GuideActivity.class);
            startActivity(i);*/
            Locale systemLocale = getApplicationContext().getResources().getConfiguration().locale;
            SharedPreferences.Editor editor2 = prefLogin.edit();
            editor2.putInt("EventAlarm", 1);
            editor2.putInt("OtherAlarm", 0);
            editor2.putString("locale",systemLocale.getLanguage()); //ko이거나 아니거나.
            Constant.locale=systemLocale.getLanguage();
            editor2.commit();
        }

        Context splachContext = LocaleHelper.setLocale(getApplicationContext());
        resources = splachContext.getResources();

        rootView = findViewById(R.id.ll_splash_root);
        TextView tvS_wait = findViewById(R.id.tvS_wait);
        ImageView ivS_title = findViewById(R.id.ivS_title);
        tvS_wait.setText(resources.getString(R.string.Splash_wait));
        ivS_title.setImageDrawable(resources.getDrawable(R.drawable.splash_center_image));

        ImageView progress = (ImageView) findViewById(R.id.anim_loading);
        AnimationDrawable frameAnimation = (AnimationDrawable) progress.getDrawable();
        frameAnimation.start();
//        ImageView mImgView = (ImageView) findViewById(R.id.loadin_dot);
//        final Animation animTransRight = AnimationUtils.loadAnimation(
//                this,R.anim.loading);
//        mImgView.startAnimation(animTransRight);

        //로딩 이미지 셋팅
        //diskCacheStrategy를 쓰면 gif 로딩이 빨라짐
        //ImageView ivLoading = findViewById(R.id.ivLoading);
        //Glide.with(getApplicationContext()).load(R.drawable.loading2).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(ivLoading);

        //GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(ivLoading);
        //Glide.with(this).load(R.drawable.loading2).into(imageViewTarget);

        //인터넷 상태 검사
        if (isNetworkConnected() == false) {
            Toast.makeText(SplashActivity.this, getString(R.string.strNoInternet), Toast.LENGTH_SHORT).show();
            finish();
        } else {//로그인 여부 확인
            try {
//                new versionCheck().execute();
                loginCheck();//임시
            } catch (Exception e) {

                Log.e("Indicar Tuning Error",e.toString());
                Toast.makeText(getApplicationContext(), getString(R.string.strServerCheck), Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    //앱 및 DB 버전 체크
    public class versionCheck extends AsyncTask<String, Void, String> {

        Response response;

        @Override
        protected String doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(getString(R.string.versionLink)).get().build();
                response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                return e.toString();
            } finally {
                response.body().close();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                versionOnline = jsonObject.getString("appVersion");
                dbVersionOnline = jsonObject.getString("carSpecVersion");

                SharedPreferences prefLogin = getApplication().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);

                dbVersion = prefLogin.getString("dbVersion", "1");
                PackageInfo info = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
                version = Integer.toString(info.versionCode);

                if (!version.equals(versionOnline)) {
//                if (version.equals(versionOnline)) {
                    String marketuri = "market://details?id=" + info.packageName;
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(marketuri));
                    finish();
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), getString(R.string.strVersionErr), Toast.LENGTH_SHORT).show();
                } else {
                    if (!dbVersion.equals(dbVersionOnline)) {
                        new CarDBLoad().execute();
                    } else {
                        loginCheck();
                    }
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), getString(R.string.strServerCheck), Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    //차량 데이터 받아와서 db에 기록하기
    public class CarDBLoad extends AsyncTask<String, Void, String> {

        Response response;
        String resultEng;

        @Override
        protected String doInBackground(String... params) {
            try {
                OkHttpClient clientEng = new OkHttpClient();
                RequestBody bodyEng = new FormBody.Builder()
                        .add("branch_id", "en")
                        .build();
                Request requestEng = new Request.Builder()
                        .url("http://13.125.173.118:9000/carSpec/list")
                        .post(bodyEng)
                        .build();
                response = clientEng.newCall(requestEng).execute();
                resultEng = response.body().string();


                OkHttpClient client = new OkHttpClient();
                RequestBody body = new FormBody.Builder()
                        .add("branch_id", "ko")
                        .build();
                Request request = new Request.Builder()
                        .url(getString(R.string.carDBLink))
                        .post(body)
                        .build();
                response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                //Log.d("Indicar Tuning",result);
                //Log.d("Indicar Tuning",resultEng);
                carDB = new CarDB(getApplicationContext(), null, 1);
                carDB.getWritableDatabase();
                carDB.deleteTable();

                JSONObject jsonObject = new JSONObject(result);
                String jsonResult = jsonObject.getString("result");
                if(jsonResult.equals("S")) {
                    JSONArray jsonArray = jsonObject.getJSONArray("content");
                    int allNum = jsonArray.length();
                    Log.d("Indicar Tuning",Integer.toString(allNum));
                    for (int i = 0; i < allNum; i++) {
                        JSONObject jsonObjectMain = jsonArray.getJSONObject(i);
                        String specName = jsonObjectMain.getString("spec_name");
                        //specName = specName.replace("[]","");//이름에 들어가 있는 []을 지운다.
                        int level = jsonObjectMain.getInt("level");
                        if (level == 1) {//level이 1인 경우 parentName이 넘어오지 않는다.
                            carDB.addCarKor(specName, 1, null, 0);
                        } else if (level == 2) {//level이 2인 경우 해당 specName 전체를 선택할 수 있는 level 3의 요소를 추가해야 한다.
                            String parentName = jsonObjectMain.getString("parent_name");
                            carDB.addCarKor(specName, 2, parentName, 0);
                        } else {//level이 3인 경우
                            String parentName = jsonObjectMain.getString("parent_name");
                            carDB.addCarKor(specName, 3, parentName, 0);
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(),resources.getString(R.string.strServerCheck),Toast.LENGTH_SHORT).show();
                    finish();
                }

                JSONObject jsonObject2 = new JSONObject(resultEng);
                String jsonResult2 = jsonObject2.getString("result");
                if(jsonResult2.equals("S")) {
                    JSONArray jsonArray = jsonObject2.getJSONArray("content");
                    int allNum = jsonArray.length();
                    Log.d("Indicar Tuning",Integer.toString(allNum));
                    for (int i = 0; i < allNum; i++) {
                        JSONObject jsonObjectMain = jsonArray.getJSONObject(i);
                        String specName = jsonObjectMain.getString("spec_name");
                        //specName = specName.replace("[]","");//이름에 들어가 있는 []을 지운다.
                        int level = jsonObjectMain.getInt("level");
                        if (level == 1) {//level이 1인 경우 parentName이 넘어오지 않는다.
                            carDB.addCarEng(specName, 1, null, 0);
                        } else if (level == 2) {//level이 2인 경우 해당 specName 전체를 선택할 수 있는 level 3의 요소를 추가해야 한다.
                            String parentName = jsonObjectMain.getString("parent_name");
                            carDB.addCarEng(specName, 2, parentName, 0);
                        } else {//level이 3인 경우
                            String parentName = jsonObjectMain.getString("parent_name");
                            carDB.addCarEng(specName, 3, parentName, 0);
                        }
                    }
                    SharedPreferences prefLogin = getApplication().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefLogin.edit();
                    editor.putString("dbVersion", dbVersionOnline);
                    editor.commit();
                    loginCheck();
                } else {
                    Toast.makeText(getApplicationContext(),resources.getString(R.string.strServerCheck),Toast.LENGTH_SHORT).show();
                    finish();
                }
            } catch (Exception e) {
                Log.d("Indicar Tuning", e.toString());
                //Toast.makeText(getApplicationContext(), resources.getString(R.string.strServerCheck), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),resources.getString(R.string.strServerCheck),Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    //로그인+국가 체크하는 함수
    private void loginCheck() {
        SharedPreferences prefLogin = getApplication().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        email = prefLogin.getString("email", "fail");
        //로그인 체크
        if (!email.equals("fail")) {
            new CheckUser().execute();
        } else {
            Intent i = new Intent(SplashActivity.this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);
            overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
            SplashActivity.this.finish();
        }
    }

    //유저 가입여부 체크
    public class CheckUser extends AsyncTask<String, Void, String> {
        String strcheckUser;

        @Override
        protected void onPreExecute() { }

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
            Log.d("ddf check2Login", result);

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
                    editor.commit();

                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
                    SplashActivity.this.finish();
                } catch (Exception e) {//에러
                    redirectLoginActivitywithFail(getString(R.string.strLoginedErr), 2);
                }
            } else {//유저 존재하지 않음
                redirectLoginActivitywithFail(getString(R.string.strLoginedErr), 2);
            }
        }
    }

    //인터넷 연결상태 확인
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    //로그인 실패 시 작동하는 함수
    private void redirectLoginActivitywithFail(final String strErr, int errCode) {//errCode: 0은 kakao, 1은 google, 2는 facebook
        switch (errCode) {
            case 0://카카오
                UserManagement.requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        Toast.makeText(getApplicationContext(), strErr, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        SplashActivity.this.finish();
                    }
                });
                break;
            case 1://구글
                Toast.makeText(SplashActivity.this, strErr, Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                SplashActivity.this.finish();
                break;
            case 2://페이스북
                Toast.makeText(SplashActivity.this, strErr, Toast.LENGTH_SHORT).show();
                LoginManager.getInstance().logOut();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Intent i2 = new Intent(SplashActivity.this, LoginActivity.class);
                i2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i2);
                SplashActivity.this.finish();
                break;
        }
    }
}
