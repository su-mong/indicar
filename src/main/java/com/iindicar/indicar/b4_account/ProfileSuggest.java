package com.iindicar.indicar.b4_account;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.iindicar.indicar.BaseActivity;
import com.iindicar.indicar.BaseActivity2;
import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.ProfileSuggestBinding;
import com.iindicar.indicar.utils.HttpClient;
import com.iindicar.indicar.utils.LocaleHelper;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import io.fabric.sdk.android.Fabric;


public class ProfileSuggest extends BaseActivity2<ProfileSuggestBinding> {

    private final String TAG = ProfileSuggest.class.getSimpleName();

    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());

        Context suggestContext = LocaleHelper.setLocale(getApplicationContext());
        resources = suggestContext.getResources();

        //언어에 맞는 뷰 설정
        binding.imageviewSTitle.setImageDrawable(resources.getDrawable(R.drawable.profile_suggest_title));
        binding.btnProfileSuggestSend.setImageDrawable(resources.getDrawable(R.drawable.profile_suggest_btn));

//        binding = DataBindingUtil.setContentView(this, R.layout.profile_suggest);
        binding.btnProfileSuggestX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.btnProfileSuggestSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendReport();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.profile_suggest;
    }




    private void sendReport() {
        SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String loginId = prefLogin.getString("id", "");
        RequestParams params = new RequestParams();
        params.put("ntcr_id", loginId);
        params.put("report_cn", binding.editProfileSuggest.getText().toString());

        final String URL = "/community/insertReport";

        HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response;

                try {
                    response = new String(responseBody);
                    Toast.makeText(ProfileSuggest.this,getString(R.string.SuggestSuccess),Toast.LENGTH_SHORT).show();
                    finish();

                } catch (Exception e) {
                    Log.e(TAG, "insertData() with URL: " + URL + " " + R.string.data_not_available);
                    Toast.makeText(ProfileSuggest.this,getString(R.string.strErrwithCode)+e.toString(),Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    return;
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e(TAG, "insertData() with URL: " + URL + " " + R.string.server_not_available);
                Toast.makeText(ProfileSuggest.this,getString(R.string.strErrwithCode)+error.toString(),Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
    }

}
