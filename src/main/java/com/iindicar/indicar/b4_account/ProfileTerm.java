package com.iindicar.indicar.b4_account;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.ProfileSuggestBinding;
import com.iindicar.indicar.databinding.ProfileTermBinding;
import com.iindicar.indicar.utils.HttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;


public class ProfileTerm extends Activity {

    ProfileTermBinding binding;
    private final String TAG = ProfileTerm.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.profile_term);
        binding.btnProfileTermOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
