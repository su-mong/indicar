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
import com.iindicar.indicar.utils.HttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;


public class ProfileSuggest extends Activity {

    ProfileSuggestBinding binding;
    private final String TAG = ProfileSuggest.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.profile_suggest);
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

    private void sendReport() {
        SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String loginId = prefLogin.getString("_id", "");
        RequestParams params = new RequestParams();
        params.put("writer_id", loginId);
        params.put("content", binding.editProfileSuggest.getText().toString());

        final String URL = "/insertReport";

        HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response;

                try {
                    response = new String(responseBody);
                    Toast.makeText(ProfileSuggest.this,"보내기 성공",Toast.LENGTH_SHORT).show();
                    finish();

                } catch (Exception e) {

                    Log.e(TAG, "insertData() with URL: " + URL + " " + R.string.data_not_available);
                    e.printStackTrace();
                    return;
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Log.e(TAG, "insertData() with URL: " + URL + " " + R.string.server_not_available);
                error.printStackTrace();
            }
        });
    }



}
