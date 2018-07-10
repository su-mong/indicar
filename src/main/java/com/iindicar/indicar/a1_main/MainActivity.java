package com.iindicar.indicar.a1_main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.PopupMenu;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
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
import com.iindicar.indicar.utils.CarDB;
import com.iindicar.indicar.utils.LocaleHelper;

import java.security.MessageDigest;
import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;

import static com.iindicar.indicar.Constant.ACCOUNT;
import static com.iindicar.indicar.Constant.COMMUNITY;
import static com.iindicar.indicar.Constant.SHOPPING;
import static com.iindicar.indicar.Constant.TUNING;

public class MainActivity extends BaseActivity<MainActivityBinding> {

    //Context mainContext; Resources resources;

    //뒤로가기 버튼을 두 번 클릭시 종료. 이를 구현하기 위한 변수를 선언한다.
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressTime = 0;

    public final ObservableField<String> currentTab = new ObservableField<>(TUNING.get());

    Context mainContext; Resources resources; Fragment fragment = null;

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
        Fabric.with(this,new Crashlytics());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mainContext = LocaleHelper.setLocale(getApplicationContext());
        resources = mainContext.getResources();

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
        if (prefLogin.getInt("EventAlarm", 1) == 1) {
            FirebaseMessaging.getInstance().subscribeToTopic("Event");
            FirebaseInstanceId.getInstance().getToken();
        }
        if (prefLogin.getInt("OtherAlarm", 0) == 1) {
            FirebaseMessaging.getInstance().subscribeToTopic("Other");
            FirebaseInstanceId.getInstance().getToken();
        }
        //SharedPreferences.Editor editor = prefLogin.edit();

        binding.setActivity(this);

        Drawable aDrawable = resources.getDrawable(R.drawable.tab_account_selector);
        Drawable cDrawable = resources.getDrawable(R.drawable.tab_community_selector);
        Drawable sDrawable = resources.getDrawable(R.drawable.tab_shopping_selector);
        Drawable tDrawable = resources.getDrawable(R.drawable.tab_tuning_selector);
        binding.btnMAccount.setImageDrawable(aDrawable);
        binding.btnMCommunity.setImageDrawable(cDrawable);
        binding.btnMShopping.setImageDrawable(sDrawable);
        binding.btnMTuning.setImageDrawable(tDrawable);

        changeTab();

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
                Toast.makeText(MainActivity.this, R.string.strWhatPermissionDeny + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                finish();
            }


        };


        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage(getString(R.string.strPermissionDenied))
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE
                        , Manifest.permission.READ_PHONE_STATE
                        , Manifest.permission.CAMERA
                        , Manifest.permission.ACCESS_FINE_LOCATION)
                .check();

    }

    public void setTab(View view) {
        String tag = view.getTag().toString();
        currentTab.set(tag);
        changeTab();
    }

    private void changeTab() {
        if (TUNING.get().equals(currentTab.get())) {
            fragment = new TunningFragment();
            centerImageId.set(R.drawable.logo_tuning);
            leftImageId.set(R.drawable.top_language);

            actionBarBinding.buttonLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showMenuDialog(v);
                }
            });
        } else if (COMMUNITY.get().equals(currentTab.get())) {
            fragment = new CommunityFragment();
            centerImageId.set(R.drawable.logo_community);
            leftImageId.set(R.drawable.btn_search);
        } else if (SHOPPING.get().equals(currentTab.get())) {
            //Toast.makeText(getApplicationContext(),getString(R.string.strNotPrepare),Toast.LENGTH_SHORT).show();
            fragment = new ShoppingFragment();
            centerImageId.set(R.drawable.logo_shopping);
            leftImageId.set(0);
        } else if (ACCOUNT.get().equals(currentTab.get())) {
            fragment = new AccountFragment();
            centerImageId.set(R.drawable.logo_account);
            leftImageId.set(R.drawable.top_language);

            actionBarBinding.buttonLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showMenuDialog(v);
                }
            });
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.view_pager_main, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();

        Context mainContext = LocaleHelper.setLocale(getApplicationContext());
        resources = mainContext.getResources();

        Drawable aDrawable = resources.getDrawable(R.drawable.tab_account_selector);
        Drawable cDrawable = resources.getDrawable(R.drawable.tab_community_selector);
        Drawable sDrawable = resources.getDrawable(R.drawable.tab_shopping_selector);
        Drawable tDrawable = resources.getDrawable(R.drawable.tab_tuning_selector);
        binding.btnMAccount.setImageDrawable(aDrawable);
        binding.btnMCommunity.setImageDrawable(cDrawable);
        binding.btnMShopping.setImageDrawable(sDrawable);
        binding.btnMTuning.setImageDrawable(tDrawable);
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

    private void showMenuDialog(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        getMenuInflater().inflate(R.menu.language_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                    case R.id.korean:
                        LocaleHelper.setLanguage(getApplicationContext(),"ko");
                        mainContext = LocaleHelper.setLocale(getApplicationContext());
                        resources = mainContext.getResources();
                        fragment.onResume();

                        Drawable aDrawable1 = resources.getDrawable(R.drawable.tab_account_selector);
                        Drawable cDrawable1 = resources.getDrawable(R.drawable.tab_community_selector);
                        Drawable sDrawable1 = resources.getDrawable(R.drawable.tab_shopping_selector);
                        Drawable tDrawable1 = resources.getDrawable(R.drawable.tab_tuning_selector);
                        binding.btnMAccount.setImageDrawable(aDrawable1);
                        binding.btnMCommunity.setImageDrawable(cDrawable1);
                        binding.btnMShopping.setImageDrawable(sDrawable1);
                        binding.btnMTuning.setImageDrawable(tDrawable1);
                        break;
                    case R.id.english:
                        LocaleHelper.setLanguage(getApplicationContext(),"en");
                        mainContext = LocaleHelper.setLocale(getApplicationContext());
                        resources = mainContext.getResources();
                        fragment.onResume();

                        Drawable aDrawable2 = resources.getDrawable(R.drawable.tab_account_selector);
                        Drawable cDrawable2 = resources.getDrawable(R.drawable.tab_community_selector);
                        Drawable sDrawable2 = resources.getDrawable(R.drawable.tab_shopping_selector);
                        Drawable tDrawable2 = resources.getDrawable(R.drawable.tab_tuning_selector);
                        binding.btnMAccount.setImageDrawable(aDrawable2);
                        binding.btnMCommunity.setImageDrawable(cDrawable2);
                        binding.btnMShopping.setImageDrawable(sDrawable2);
                        binding.btnMTuning.setImageDrawable(tDrawable2);
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
}
