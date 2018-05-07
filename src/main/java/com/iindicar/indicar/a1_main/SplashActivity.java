package com.iindicar.indicar.a1_main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.databinding.ObservableInt;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.iindicar.indicar.BaseActivity;
import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.SplashActivityBinding;
import com.iindicar.indicar.utils.CarDB;
import com.iindicar.indicar.utils.ConstClass;
import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SplashActivity extends AppCompatActivity {

    CarDB carDB;
    String version;
    String versionOnline;
    String dbVersion;
    String dbVersionOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_splash);

        //로딩 이미지 셋팅
        //diskCacheStrategy를 쓰면 gif 로딩이 빨라짐
        //ImageView ivLoading = findViewById(R.id.ivLoading);
        //Glide.with(getApplicationContext()).load(R.drawable.loading2).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(ivLoading);

        //GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(ivLoading);
        //Glide.with(this).load(R.drawable.loading2).into(imageViewTarget);

        //인터넷 상태 검사
        if(isNetworkConnected() == false) {
            Toast.makeText(SplashActivity.this, ConstClass.strNoInternet,Toast.LENGTH_SHORT).show();
            finish();
        } else {//로그인 여부 확인
            try {
                Thread.sleep(1700);
            } catch (Exception e) {}
            try {
                new versionCheck().execute();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),ConstClass.strServerCheck,Toast.LENGTH_SHORT).show();
            }
        }
    }

    //앱 및 DB 버전 체크
    public class versionCheck extends AsyncTask<String,Void,String> {

        Response response;

        @Override
        protected String doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(ConstClass.versionLink).get().build();
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
                SharedPreferences.Editor editor = prefLogin.edit();

                dbVersion = prefLogin.getString("dbVersion","1");
                PackageInfo info = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
                version = Integer.toString(info.versionCode);

                if(!version.equals(versionOnline)) {
                    String marketuri = "market://details?id="+info.packageName;
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(marketuri));
                    finish();
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),ConstClass.strVersionErr,Toast.LENGTH_SHORT).show();
                }
                if(!dbVersion.equals(dbVersionOnline)) {
                    editor.putString("dbVersion", dbVersionOnline);
                    new LoadingExe().execute();
                } else {
                    loginCheck();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),"서버 점검중입니다. 다시 시도해 주세요",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    //차량 데이터 받아와서 db에 기록하기
    public class LoadingExe extends AsyncTask<String,Void,String> {

        Response response;

        @Override
        protected String doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://13.125.173.118:8080/carSpec/list").get().build();
                response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                carDB = new CarDB(getApplicationContext(),"carDB",null,1);
                carDB.getWritableDatabase();
                carDB.deleteTable();

                JSONArray jsonArray = new JSONArray(result);
                int allNum = jsonArray.length();
                for(int i=0;i<allNum;i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String specName = jsonObject.getString("specName");
                    specName = specName.replace("[]","");
                    //specName = specName.replace("[]","");//이름에 들어가 있는 []을 지운다.
                    int level = Integer.parseInt(jsonObject.getString("level"));
                    if(level == 1) {//level이 1인 경우 parentName이 넘어오지 않는다.
                        carDB.addCar(specName,1,null,0);
                    } else if(level==2) {//level이 2인 경우 해당 specName 전체를 선택할 수 있는 level 3의 요소를 추가해야 한다.
                        String parentName = jsonObject.getString("parentName");
                        parentName = parentName.replace("[]","");
                        carDB.addCar(specName,2,parentName,0);
                        carDB.addCar(specName+" 전체",3,specName,0);
                    } else {//level이 3인 경우
                        String parentName = jsonObject.getString("parentName");
                        parentName = parentName.replace("[]","");
                        carDB.addCar(specName,3,parentName,0);
                    }
                }

                loginCheck();
            } catch (Exception e) {
                Log.d("dbCheck",e.toString());
                Toast.makeText(getApplicationContext(),ConstClass.strServerCheck,Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    //로그인 체크하는 함수
    private void loginCheck() {
        SharedPreferences prefLogin = getApplication().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        //로그인 체크
        if(Session.getCurrentSession().isClosed() == false ) { //카카오
            //Session.getCurrentSession().open(AuthType.KAKAO_LOGIN_ALL,SplashActivity.this);
            if(!prefLogin.getString("email","fail").equals("fail")) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
                SplashActivity.this.finish();
            } else {
                redirectLoginActivitywithFail(ConstClass.strLoginedErr,2);
            }
        } else if(FirebaseAuth.getInstance().getCurrentUser() != null) { //구글
            //Intent googleIntent = GoogleClient.getSignInIntent();
            //startActivityForResult(googleIntent,9001);
            if(!prefLogin.getString("email","fail").equals("fail")) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
                SplashActivity.this.finish();
            } else {
                redirectLoginActivitywithFail(ConstClass.strLoginedErr,2);
            }
        } else if(AccessToken.getCurrentAccessToken() != null) { //페이스북
            if(!prefLogin.getString("email","fail").equals("fail")) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
                SplashActivity.this.finish();
            } else {
                redirectLoginActivitywithFail(ConstClass.strLoginedErr,2);
            }
        } else {
            Intent i = new Intent(SplashActivity.this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);
            overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
            SplashActivity.this.finish();
        }
    }

    //인터넷 연결상태 확인
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo()!=null;
    }

    //로그인 실패 시 작동하는 함수
    private void redirectLoginActivitywithFail(final String strErr, int errCode) {//errCode: 0은 kakao, 1은 google, 2는 facebook
        switch(errCode) {
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
                Toast.makeText(SplashActivity.this, strErr,Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                SplashActivity.this.finish();
                break;
            case 2://페이스북
                Toast.makeText(SplashActivity.this, strErr,Toast.LENGTH_SHORT).show();
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
