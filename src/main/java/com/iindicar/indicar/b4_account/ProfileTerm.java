package com.iindicar.indicar.b4_account;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.iindicar.indicar.BaseActivity2;
import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.ProfileSuggestBinding;
import com.iindicar.indicar.databinding.ProfileTermBinding;
import com.iindicar.indicar.utils.HttpClient;
import com.iindicar.indicar.utils.LocaleHelper;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import io.fabric.sdk.android.Fabric;


public class ProfileTerm extends BaseActivity2<ProfileTermBinding> {

    private final String TAG = ProfileTerm.class.getSimpleName();
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());

        Context mainContext = LocaleHelper.setLocale(getApplicationContext());
        resources = mainContext.getResources();

        binding.textTermTitle.setText(resources.getString(R.string.strClause));
        binding.textTermContent.setText(resources.getString(R.string.term));

        binding.btnProfileTermOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.profile_term;
    }
}
