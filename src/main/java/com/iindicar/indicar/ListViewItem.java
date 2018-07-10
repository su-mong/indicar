package com.iindicar.indicar;

import java.util.ArrayList;

public class ListViewItem {
    private ArrayList<String> tvs;
    private String tv1;
    private String tv2;
    private String tv3;

    public void setArray(String tv1, String tv2, String tv3) {
        tvs.add(tv1);
        tvs.add(tv2);
        tvs.add(tv3);
    }

    public ArrayList<String> getTvs() {
        return tvs;
    }

    public void set1(String tv1) {
        this.tv1 = tv1;
    }

    public void set2(String tv2) {
        this.tv2 = tv2;
    }

    public void set3(String tv3) {
        this.tv3 = tv3;
    }

    public String get1() {
        return this.tv1;
    }

    public String get2() {
        return this.tv2;
    }

    public String get3() {
        return this.tv3;
    }
}


