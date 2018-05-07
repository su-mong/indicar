package com.iindicar.indicar.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by candykick on 2018. 4. 29..
 */

public class CarDB  extends SQLiteOpenHelper {

    private Context context;

    //흔한 생성자.
    public CarDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    //찾는 DB명이 없을 경우 호출.
    //테이블을 여기서 관리한다.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //String보다 StringBuffer가 Query 만들기 편하다.
        StringBuffer sql = new StringBuffer();
        sql.append("CREATE TABLE carDB (");
        sql.append("specName TEXT,");
        sql.append("level integer(1),");
        sql.append("parentName TEXT,");
        sql.append("useChecker Integer(1))");

        //String보다 StringBuffer가 Query 만들기 편하다.
        StringBuffer sql2 = new StringBuffer();
        sql2.append("CREATE TABLE searchDB (");
        sql2.append("specName TEXT,");
        sql2.append("level integer(1),");
        sql2.append("parentName TEXT,");
        sql2.append("useChecker Integer(1))");
        try {
            db.execSQL(sql.toString());
            db.execSQL(sql2.toString());
        } catch(Exception e) {
            Toast.makeText(context,"IDVC ERROR: "+e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    //db명은 있지만 버전이 다른 경우 호출.
    //새 테이블 추가와 같은 작업은 여기서 관리한다.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    //자동차 데이터를 db에 넣는 함수
    public void addCar(String specName, int level, String parentName, int useChecker) {
        //1. 쓸 수 있는 DB객체를 가져온다.
        SQLiteDatabase db = getWritableDatabase();

        //2. 데이터를 넣는 쿼리문 작성. 단 ID는 자동으로 증가하기 때문에 넣지 않는다.
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO carDB ");
        sql.append("(specName, level, parentName, useChecker) VALUES (");
        sql.append("'"+specName+"',");
        sql.append(Integer.toString(level)+",");
        sql.append("'"+parentName+"',");
        sql.append(Integer.toString(useChecker)+");");

        //3. 쿼리문 실행
        db.execSQL(sql.toString());
    }

    //테이블을 다 날리는 함수
    public void deleteTable() {
        //1. 쓸 수 있는 DB객체를 가져온다.
        SQLiteDatabase db = getWritableDatabase();

        //2. carDB 테이블이 존재하는지 확인.
        StringBuffer sql1 = new StringBuffer();
        sql1.append("SELECT name FROM sqlite_master WHERE type='table' AND name ='carDB'");
        Cursor c = db.rawQuery(sql1.toString(),null);
        if(c.moveToFirst()) {
            //3. 데이터를 날리는 쿼리문 작성.
            StringBuffer sql2 = new StringBuffer();
            sql2.append("delete from carDB");
            db.execSQL(sql2.toString());
        }
    }

    //차량 검색 결과를 반영하는 함수
    public void searchResult(ArrayList<String> parent, ArrayList<ArrayList<String>> child) {
        //1. searchDB 내용을 지운다.
        SQLiteDatabase db = getWritableDatabase();

        StringBuffer sql1 = new StringBuffer();
        sql1.append("SELECT name FROM sqlite_master WHERE type='table' AND name ='searchDB'");
        Cursor c = db.rawQuery(sql1.toString(),null);
        if(c.moveToFirst()) {
            StringBuffer sql2 = new StringBuffer();
            sql2.append("delete from searchDB");
            db.execSQL(sql2.toString());
        }

        //2.데이터를 새로 넣는다.
        for(int i=0;i<parent.size();i++) {
            StringBuffer sql3 = new StringBuffer();
            sql3.append("INSERT INTO searchDB");
            sql3.append("(specName, level, parentName, useChecker) VALUES (");
            sql3.append("'" + parent.get(i) + "',");
            sql3.append(Integer.toString(2) + ",");
            sql3.append("'" + null + "',");
            sql3.append(Integer.toString(0) + ");");
            db.execSQL(sql3.toString());

            for(int j=0;j<child.size();j++) {
                StringBuffer sql4 = new StringBuffer();
                sql4.append("INSERT INTO searchDB");
                sql4.append("(specName, level, parentName, useChecker) VALUES (");
                sql4.append("'" + child.get(i).get(j) + "',");
                sql4.append(Integer.toString(3) + ",");
                sql4.append("'" + parent.get(i) + "',");
                sql4.append(Integer.toString(0) + ");");
                db.execSQL(sql4.toString());
            }
        }
    }
}
