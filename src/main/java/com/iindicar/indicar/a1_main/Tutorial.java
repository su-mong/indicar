package com.iindicar.indicar.a1_main;

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
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new TutorialAdapter(this);
        viewPager.setAdapter(adapter);
        ImageView btnSkip = (ImageView) findViewById(R.id.btn_skip);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}