package com.iindicar.indicar.a1_main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.iindicar.indicar.BaseActivity;
import com.iindicar.indicar.R;
import com.iindicar.indicar.b1_tunning.TunningFragment;
import com.iindicar.indicar.b2_community.CommunityFragment;
import com.iindicar.indicar.b3_shopping.ShoppingFragment;
import com.iindicar.indicar.b4_account.AccountFragment;
import com.iindicar.indicar.databinding.MainActivityBinding;

import java.security.MessageDigest;
import java.util.ArrayList;

public class MainActivity extends BaseActivity<MainActivityBinding> {
    private final int NUM_OF_TAB_BUTTONS = 4;
    private final int tabImageIds[] = {
            R.drawable.tab_t,
            R.drawable.tab_c,
            R.drawable.tab_s,
            R.drawable.tab_a
    };

    //뒤로가기 버튼을 두 번 클릭시 종료. 이를 구현하기 위한 변수를 선언한다.
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressTime = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    protected void setActionBarImage(ObservableInt centerImageId, ObservableInt leftImageId) {
        centerImageId.set(R.drawable.logo_tuning);
        leftImageId.set(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (LoginActivity.LoginAct != null)
            LoginActivity.LoginAct.finish();

        //키해쉬 받아오기
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.iindicar.indicar", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //푸시알람을 받아오는 부분을 구현한다.
        SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        if (!prefLogin.getBoolean("eventAlarm", false)) {
            FirebaseMessaging.getInstance().subscribeToTopic("Event");
            FirebaseInstanceId.getInstance().getToken();
        }
        if (!prefLogin.getBoolean("otherAlarm", false)) {
            FirebaseMessaging.getInstance().subscribeToTopic("Other");
            FirebaseInstanceId.getInstance().getToken();
        }
        SharedPreferences.Editor editor = prefLogin.edit();

        initTab();

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                SharedPreferences prefLogin = getApplication().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefLogin.edit();
                boolean isFirst = prefLogin.getBoolean("isFirst",true);
                if(isFirst){
                    startActivity(new Intent(MainActivity.this,Tutorial.class));
                    editor.putBoolean("isFirst", false);
                    editor.commit();
                }

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                finish();
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("필수 권한을 거절하시면 앱을 이용하실 수 없습니다.\n [설정] > [권한] 에서 권한을 허용할 수 있어요\n")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE
                        , Manifest.permission.READ_PHONE_STATE
                        , Manifest.permission.CAMERA
                        , Manifest.permission.ACCESS_FINE_LOCATION)
                .check();
    }

    private void initTab() {

        binding.tabMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;

                switch ((int) tab.getTag()) {
                    case 0:
                        fragment = new TunningFragment();
                        centerImageId.set(R.drawable.logo_tuning);
                        leftImageId.set(0);
                        break;
                    case 1:
                        fragment = new CommunityFragment();
                        centerImageId.set(R.drawable.logo_community);
                        leftImageId.set(R.drawable.top_language);
                        break;
                    case 2:
                        fragment = new ShoppingFragment();
                        centerImageId.set(R.drawable.logo_shopping);
                        leftImageId.set(0);
                        break;
                    case 3:
                        fragment = new AccountFragment();
                        centerImageId.set(R.drawable.logo_account);
                        leftImageId.set(0);
                        break;
                }
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.view_pager_main, fragment);
                fragmentTransaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for(int i = 0 ; i < NUM_OF_TAB_BUTTONS ; i++){
            View view = View.inflate(this, R.layout.main_tab_layout, null);
            ImageView imageView = view.findViewById(R.id.image);
            imageView.setScaleY(-1);
            imageView.setImageResource(tabImageIds[i]);
            TabLayout.Tab tab = binding.tabMain.newTab();
            tab.setCustomView(view);
            tab.setTag(i);
            binding.tabMain.addTab(tab);
        }
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
            Toast.makeText(MainActivity.this, "뒤로 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
