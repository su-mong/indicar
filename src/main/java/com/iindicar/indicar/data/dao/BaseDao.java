package com.iindicar.indicar.data.dao;

import com.loopj.android.http.RequestParams;

import java.util.List;

/**
 * Created by yeseul on 2018-04-13.
 * <p>
 * TODO (2018.05.03) interface 파라미터 <T> 체크하기
 */

public interface BaseDao<T> {

    void getDataList(RequestParams params, LoadDataListCallBack callBack);

    void getData(RequestParams params, LoadDataCallBack callBack);

    void insertData(RequestParams params, LoadDataCallBack callBack);

    void updateData(RequestParams params, LoadDataCallBack callBack);

    void deleteData(RequestParams params, LoadDataCallBack callBack);

    void sendReport(RequestParams params, LoadDataCallBack callBack);

    interface LoadDataListCallBack<T> {

        void onDataListLoaded(List<T> list);

        void onDataNotAvailable();
    }

    interface LoadDataCallBack<T> {

        void onDataLoaded(T data);

        void onDataNotAvailable();
    }

}
