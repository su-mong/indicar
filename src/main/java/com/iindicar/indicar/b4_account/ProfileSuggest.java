package com.iindicar.indicar.b4_account;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.iindicar.indicar.R;


public class ProfileSuggest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_suggest);
        ImageView btnx=(ImageView)findViewById(R.id.btn_profile_suggest_x);
        btnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }




}
