package com.iindicar.indicar.a1_main;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.crashlytics.android.Crashlytics;
import com.iindicar.indicar.R;
import com.iindicar.indicar.utils.LocaleHelper;

import io.fabric.sdk.android.Fabric;

public class Tutorial extends AppCompatActivity {
    TutorialAdapter adapter;
    ViewPager viewPager;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());
        setContentView(R.layout.activity_tutorial);

        Context activityContext = LocaleHelper.setLocale(getApplicationContext());
        resources = activityContext.getResources();

        ((Activity) this).getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        int[] images = {resources.getIdentifier("tutorial_1","drawable","com.iindicar.indicar"),
                resources.getIdentifier("tutorial_2","drawable","com.iindicar.indicar"),
                resources.getIdentifier("tutorial_3","drawable","com.iindicar.indicar")};

        getWindow().getDecorView().getRootView().setBackground(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        adapter = new TutorialAdapter(this, images);
        viewPager.setAdapter(adapter);
    }

    /*@Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
    }*/
}