package com.iindicar.indicar.data.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.iindicar.indicar.data.vo.CarDataVO;
import com.iindicar.indicar.utils.HttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by candykick on 2018. 7. 9..
 */

public class CarDataDao implements BaseDao<CarDataVO>{
    @Override
    public void getDataList(RequestParams params, final LoadDataListCallBack callBack) {
        final String URL = "/car/get_car";

        HttpClient.get(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JsonElement result;

                try {
                    result = new JsonParser().parse(new String(responseBody));
                } catch (Exception e) {
                    callBack.onDataNotAvailable();
                    e.printStackTrace();
                    return;
                }

                // 차량 리스트 존재
                if (result != null && result.isJsonArray()) {
                    JsonArray array = result.getAsJsonArray();

                    List<CarDataVO> carList = new ArrayList<>();

                    for (int i = 0; i < array.size(); i++) {
                        CarDataVO vo = new Gson().fromJson(array.get(i), CarDataVO.class);
                        carList.add(vo);
                    }
                    callBack.onDataListLoaded(carList);
                } else {
                    callBack.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                callBack.onDataNotAvailable();
            }
        });
    }



    @Override
    public void getData(RequestParams params, LoadDataCallBack callBack) {

    }

    @Override
    public void insertData(RequestParams params, LoadDataCallBack callBack) {

    }

    @Override
    public void updateData(RequestParams params, LoadDataCallBack callBack) {

    }

    @Override
    public void deleteData(RequestParams params, LoadDataCallBack callBack) {

    }

    @Override
    public void sendReport(RequestParams params, LoadDataCallBack callBack) {

    }
}
