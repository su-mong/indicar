package com.iindicar.indicar.b4_account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.iindicar.indicar.BaseActivity;
import com.iindicar.indicar.R;
import com.iindicar.indicar.b2_community.boardList.BoardListPagerAdapter;
import com.iindicar.indicar.databinding.ActivityTraceBinding;
import com.iindicar.indicar.utils.ConstClass;

import java.util.Observable;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TraceActivity extends BaseActivity<ActivityTraceBinding> {
    public final ObservableField<String> textSearch = new ObservableField<>();
    public final ObservableBoolean isSearchBarOpen = new ObservableBoolean(false);
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
Log.d("ddf","traceAct");

        binding.setActivity(this);
        //viewPager 어댑터 설정
        binding.viewPagerTrace.setAdapter(new BoardListPagerAdapter(getApplicationContext(), getSupportFragmentManager()));

        SharedPreferences prefLogin = getApplicationContext().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        id = prefLogin.getString("_id",null);
        Log.d("idTag",id);

        Intent intent = getIntent();
        int trace = intent.getIntExtra("trace",1);
        switch(trace) {
            case 1:
                setLikeActivity();
                break;
            case 2:
                setWritingActivity();
                break;
            case 3:
                setCommentActivity();
                break;
            default:
                setLikeActivity();
                break;
        }

        //버튼 클릭 이벤트 설정
        binding.btnALike2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLikeActivity();
            }
        });
        binding.btnAWriting2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWritingActivity();
            }
        });
        binding.btnAComment2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCommentActivity();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_trace;
    }

    @Override
    protected void setActionBarImage(ObservableInt centerImageId, ObservableInt leftImageId) {
        centerImageId.set(R.drawable.logo_tuning);
        leftImageId.set(R.drawable.btn_back);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
    }

    private void setLikeActivity() {
        binding.ivALikeOn.setImageResource(R.drawable.btna_on);
        binding.ivAWritingOn.setImageResource(0);
        binding.ivACommentOn.setImageResource(0);
        binding.viewPagerTrace.setCurrentItem(2);

        /*try {

        } catch (Exception e) {

        }*/
    }

    private void setWritingActivity() {
        binding.ivALikeOn.setImageResource(0);
        binding.ivAWritingOn.setImageResource(R.drawable.btna_on);
        binding.ivACommentOn.setImageResource(0);
        binding.viewPagerTrace.setCurrentItem(3);

        /*try {

        } catch (Exception e) {

        }*/
    }

    private void setCommentActivity() {
        binding.ivALikeOn.setImageResource(0);
        binding.ivAWritingOn.setImageResource(0);
        binding.ivACommentOn.setImageResource(R.drawable.btna_on);
    }

    private class userLikeAsync extends AsyncTask<String, Void, String> {
        String json = new String();

        @Override
        protected String doInBackground(String... params) {
            if(id != null) {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody body = new FormBody.Builder()
                            .add("_id", id)
                            .build();
                    Request request = new Request.Builder()
                            .url(ConstClass.traceLikeResult)
                            .post(body)
                            .build();
                    Response response = client.newCall(request).execute();
                    json = response.body().string();
                    response.body().close();
                    return json;
                } catch (Exception e) {
                    json = "AsyncTask Fail: " + e.toString();
                    return json;
                }
            } else {
                return "NullError";
            }
        }
    }

    private class userWriting extends AsyncTask<String, Void, String> {
        String json = new String();

        @Override
        protected String doInBackground(String... params) {
            if(id != null) {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody body = new FormBody.Builder()
                            .add("ntcr_id", id)
                            .build();
                    Request request = new Request.Builder()
                            .url(ConstClass.traceWritingResult)
                            .post(body)
                            .build();
                    Response response = client.newCall(request).execute();
                    json = response.body().string();
                    response.body().close();
                    return json;
                } catch (Exception e) {
                    json = "AsyncTask Fail: " + e.toString();
                    return json;
                }
            } else {
                return "NullError";
            }
        }
    }
}
