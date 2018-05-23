package com.iindicar.indicar.a1_main;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.iindicar.indicar.R;

public class Tutorial extends AppCompatActivity {
    TutorialAdapter adapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        ((Activity) this).getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        getWindow().getDecorView().getRootView().setBackground(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        adapter = new TutorialAdapter(this);
        viewPager.setAdapter(adapter);


    }

}