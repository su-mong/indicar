package com.iindicar.indicar.b2_community;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.util.Log;

import com.iindicar.indicar.BaseActivity;
import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.CarFilterActivityBinding;
import com.iindicar.indicar.utils.CarDB;

public class CarFilterActivity extends BaseActivity<CarFilterActivityBinding> {

    CarDB carDB;
    SQLiteDatabase db;

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
        String searchWord = "LS";


        ListViewAdapter firstAdapter = new ListViewAdapter();
        binding.listView.setAdapter(firstAdapter);
        String sql1 = "SELECT specName,parentName FROM carDB WHERE level=1";
        String sql2 = "SELECT specName,parentName FROM carDB WHERE level=2";
        String sql3 = "SELECT specName,parentName FROM carDB WHERE level=3 AND specName='"+searchWord+"'";
        Cursor cursor1 = db.rawQuery(sql1, null);
        Cursor cursor2 = db.rawQuery(sql2, null);
        Cursor cursor3 = db.rawQuery(sql3, null);
        String tmp1;
        String tmp2;
        String tmp3;
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
                            firstAdapter.addItem(tmp1, tmp2, tmp3);
                            Log.d("ddf db", tmp1 + " " + tmp2 + " " + tmp3);
                        }
                    }
                    cursor3.moveToFirst();
                }
            }
            cursor2.moveToFirst();
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
