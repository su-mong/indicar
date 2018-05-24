package com.iindicar.indicar.b2_community;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.ObservableInt;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;

import com.iindicar.indicar.BaseActivity;
import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.CarFilterActivityBinding;
import com.iindicar.indicar.utils.CarDB;

import java.util.ArrayList;

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        carDB = new CarDB(getApplicationContext(), "carDB", null, 1);
        db = carDB.getReadableDatabase();


        name1 = new ArrayList<>();
        name2 = new ArrayList<>();
        name3 = new ArrayList<>();
        mHandler = new Handler();
        firstAdapter = new ListViewAdapter();
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
//        binding.searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    binding.listView.setFilterText(binding.searchText.getText().toString());
//                    return true;
//                }
//                return false;
//            }
//        });


        String sql1 = "SELECT specName,parentName FROM carDB WHERE level=1";
        String sql2 = "SELECT specName,parentName FROM carDB WHERE level=2";
        String sql3 = "SELECT specName,parentName FROM carDB WHERE level=3";
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
                    Log.d("ddf cursor1", tmp1);
                    while (cursor2.moveToNext()) {
                        if (tmp1.equals(cursor2.getString(1))) {
                            Log.d("ddf cursor2", cursor2.getString(0));
                            tmp2 = cursor2.getString(0);
                            while (cursor3.moveToNext()) {
                                if (tmp2.equals(cursor3.getString(1))) {
                                    Log.d("ddf cursor3", cursor3.getString(0));
                                    tmp3 = cursor3.getString(0);
                                    final String finalTmp1 = tmp1;
                                    final String finalTmp2 = tmp2;
                                    final String finalTmp3 = tmp3;
                                    mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            firstAdapter.addItem(finalTmp1, finalTmp2, finalTmp3);
                                            Log.d("ddf adapter", finalTmp1 + finalTmp2 + finalTmp3);
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
                        binding.searchText.setEnabled(true);
                        binding.searchText.setText("");
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
