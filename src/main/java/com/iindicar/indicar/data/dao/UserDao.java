package com.iindicar.indicar.data.dao;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iindicar.indicar.data.vo.UserVO;
import com.iindicar.indicar.utils.HttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

/**
 * Created by yeseul on 2018-04-13.
 */

public class UserDao implements BaseDao<UserVO> {

    @Override
    public void getData(RequestParams params, final LoadDataCallBack callBack) {
        final String URL = "/user/search_user3";

        HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JsonElement result;

                try {
                    result = new JsonParser().parse(new String(responseBody));
                } catch (Exception e){
                    e.printStackTrace();
                    callBack.onDataNotAvailable();
                    return;
                }

                // 유저 정보 존재
                if(result.isJsonObject()){
                    JsonObject json = result.getAsJsonObject();
                    UserVO userVO = new Gson().fromJson(json, UserVO.class);
                    callBack.onDataLoaded(userVO);
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
    public void insertData(RequestParams params, LoadDataCallBack callBack) {

    }

    @Override
    public void updateData(RequestParams params, final LoadDataCallBack callBack) {
        final String URL = "/user/updt_user";

        HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                callBack.onDataLoaded(null);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                callBack.onDataNotAvailable();
            }
        });
    }

    @Override
    public void deleteData(RequestParams params, LoadDataCallBack callBack) {

    }

    @Override
    public void sendReport(RequestParams params, LoadDataCallBack callBack) {

    }

    @Override
    public void getDataList(RequestParams params, LoadDataListCallBack callBack) {

    }

    @Override
    public void getDataListLike(RequestParams params, LoadDataListCallBack callBack) {

    }


}
