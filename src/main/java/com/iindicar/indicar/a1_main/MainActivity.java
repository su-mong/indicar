package com.iindicar.indicar.a1_main;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.iindicar.indicar.BaseActivity;
import com.iindicar.indicar.R;
import com.iindicar.indicar.b1_tunning.TunningFragment;
import com.iindicar.indicar.b2_community.CommunityFragment;
import com.iindicar.indicar.b2_community.boardList.BoardListFragment;
import com.iindicar.indicar.b3_shopping.ShoppingFragment;
import com.iindicar.indicar.b4_account.AccountFragment;
import com.iindicar.indicar.databinding.MainActivityBinding;

import java.security.MessageDigest;
import java.util.Observable;

import static com.iindicar.indicar.Constant.ACCOUNT;
import static com.iindicar.indicar.Constant.COMMUNITY;
import static com.iindicar.indicar.Constant.SHOPPING;
import static com.iindicar.indicar.Constant.TUNING;

public class MainActivity extends BaseActivity<MainActivityBinding> {

    //뒤로가기 버튼을 두 번 클릭시 종료. 이를 구현하기 위한 변수를 선언한다.
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressTime = 0;

    public final ObservableField<String> currentTab = new ObservableField<>(TUNING.get());

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
        if(prefLogin.getInt("EventAlarm",1)==1) {
            FirebaseMessaging.getInstance().subscribeToTopic("Event");
            FirebaseInstanceId.getInstance().getToken();
        }
        if(prefLogin.getInt("OtherAlarm",0)==1) {
            FirebaseMessaging.getInstance().subscribeToTopic("Other");
            FirebaseInstanceId.getInstance().getToken();
        }

        binding.setActivity(this);

        changeTab();
    }

    public void setTab(View view){
        String tag = view.getTag().toString();
        currentTab.set(tag);
        changeTab();
    }

    private void changeTab() {
        Fragment fragment = null;

        if (TUNING.get().equals(currentTab.get())) {
            fragment = new TunningFragment();
            centerImageId.set(R.drawable.logo_tuning);

        } else if (COMMUNITY.get().equals(currentTab.get())){
            fragment = new CommunityFragment();
            centerImageId.set(R.drawable.logo_community);

        } else if (SHOPPING.get().equals(currentTab.get())){
            fragment = new ShoppingFragment();
            centerImageId.set(R.drawable.logo_shopping);

        } else if (ACCOUNT.get().equals(currentTab.get())){
            fragment = new AccountFragment();
            centerImageId.set(R.drawable.logo_account);
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.view_pager_main,fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
            overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
        }
        else {
            backPressTime = tempTime;
            Toast.makeText(MainActivity.this, "뒤로 버튼을 한 번 더 누르시면 종료됩니다.",Toast.LENGTH_SHORT).show();
        }
    }
}
