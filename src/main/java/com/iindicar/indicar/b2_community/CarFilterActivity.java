package com.iindicar.indicar.b2_community;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.ObservableInt;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.crashlytics.android.Crashlytics;
import com.iindicar.indicar.BaseActivity;
import com.iindicar.indicar.ListViewItem;
import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.CarFilterActivityBinding;
import com.iindicar.indicar.utils.CarDB;
import com.iindicar.indicar.utils.LocaleHelper;

import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;

public class CarFilterActivity extends BaseActivity<CarFilterActivityBinding> {

    CarDB carDB;
    SQLiteDatabase db;
    ArrayList<String> name1;
    ArrayList<String> name2;
    ArrayList<String> name3;
    Cursor cursor1;
    Cursor cursor2;
    Cursor cursor3;
    ListViewAdapter firstAdapter;
    Handler mHandler = null;
    private ProgressDialog dialog;

    Resources resources;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());

        Context carfilterContext = LocaleHelper.setLocale(getApplicationContext());
        resources = carfilterContext.getResources();

        //언어 설정을 확인한다.
        String language = LocaleHelper.getLanguage(getApplicationContext());
        Boolean isKoreaLanguage;
        Log.d("Indicar Tuning language",language);
        if(language.equals("ko"))
            isKoreaLanguage = true;
        else
            isKoreaLanguage = false;

        Drawable titleDrawable = resources.getDrawable(R.drawable.text_write_category_car);
        binding.textCarfilterTitle.setImageDrawable(titleDrawable);

        dialog = new ProgressDialog(this);
        dialog.setMessage(resources.getString(R.string.makingList));
//        dialog.show();
        carDB = new CarDB(getApplicationContext(), null, 1);
        db = carDB.getReadableDatabase();

        actionBarBinding.buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        name1 = new ArrayList<>();
        name2 = new ArrayList<>();
        name3 = new ArrayList<>();
        mHandler = new Handler();
        firstAdapter = new ListViewAdapter();
        binding.searchText.setText(resources.getString(R.string.makingList));
        binding.searchText.setHint(resources.getString(R.string.tuning2Hint));
        binding.searchText.setEnabled(false);
        binding.searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
                String filterText = edit.toString();
                ((ListViewAdapter) binding.listView.getAdapter()).getFilter().filter(filterText);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        binding.listView.setAdapter(firstAdapter);
        binding.listView.setEnabled(false);



        String sql1, sql2, sql3;
        if(isKoreaLanguage == true) {
            sql1 = "SELECT specName,parentName FROM carDBkor WHERE level=1";
            sql2 = "SELECT specName,parentName FROM carDBkor WHERE level=2";
            sql3 = "SELECT specName,parentName FROM carDBkor WHERE level=3";
        } else {
            sql1 = "SELECT specName,parentName FROM carDBeng WHERE level=1";
            sql2 = "SELECT specName,parentName FROM carDBeng WHERE level=2";
            sql3 = "SELECT specName,parentName FROM carDBeng WHERE level=3";
        }
        cursor1 = db.rawQuery(sql1, null);
        cursor2 = db.rawQuery(sql2, null);
        cursor3 = db.rawQuery(sql3, null);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                String tmp1 = null;
                String tmp2 = null;
                String tmp3 = null;
                while (cursor1.moveToNext()) {

                    tmp1 = cursor1.getString(0);
                    while (cursor2.moveToNext()) {
                        if (tmp1.equals(cursor2.getString(1))) {
                            tmp2 = cursor2.getString(0);
                            while (cursor3.moveToNext()) {
                                if (tmp2.equals(cursor3.getString(1))) {
                                    tmp3 = cursor3.getString(0);
                                    final String finalTmp1 = tmp1;
                                    final String finalTmp2 = tmp2;
                                    final String finalTmp3 = tmp3;

                                    mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            firstAdapter.addItem(finalTmp1, finalTmp2, finalTmp3);
                                            firstAdapter.notifyDataSetChanged();
                                        }
                                    });
                                }
                            }
                            cursor3.moveToFirst();
                        }
                    }
                    cursor2.moveToFirst();
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.searchText.setText("");
                        binding.searchText.setEnabled(true);
                        binding.listView.setEnabled(true);
                        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                try {
                                    ListViewItem carName = (ListViewItem) parent.getAdapter().getItem(position);
                                    String tmp = carName.get3();
                                    Intent data = new Intent();
                                    data.putExtra("car_name", tmp);
                                    setResult(RESULT_OK, data);
                                    finish();
                                } catch (Exception e) {

                                }

                            }
                        });
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }
                });

            }
        });

        t.start();


    }

    class MyAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {


            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            for (int i = 0; i < name1.size(); i++) {
                firstAdapter.addItem(name1.get(i), name2.get(i), name3.get(i));
            }
            firstAdapter.notifyDataSetChanged();
        }

    }


    @Override
    protected int getLayoutId() {
        return R.layout.car_filter_activity;
    }

    @Override
    protected void setActionBarImage(ObservableInt centerImageId, ObservableInt leftImageId) {
        centerImageId.set(R.drawable.logo_community);
        leftImageId.set(R.drawable.btn_back);
    }

}
