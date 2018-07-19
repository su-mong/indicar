package com.iindicar.indicar.data.dao;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.iindicar.indicar.data.vo.BoardCommentVO;
import com.iindicar.indicar.data.vo.BoardDetailVO;
import com.iindicar.indicar.utils.HttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by yeseul on 2018-04-13.
 */

public class BoardCommentDao implements BaseDao<BoardCommentVO> {

    @Override
    public void getDataList(RequestParams params, final LoadDataListCallBack callBack) {
        final String URL = "/community/selectCommentList";

        HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JsonElement jsonElement = new JsonParser().parse(new String(responseBody)).getAsJsonObject();
                JsonObject rootObj = jsonElement.getAsJsonObject();
                JsonElement result2 = (JsonElement) rootObj.get("result");
                List<BoardDetailVO> boardList = new ArrayList<>();


                // 댓글 존재
                if (result2.getAsString().equals("S")) {
                    JsonArray result = (JsonArray) rootObj.get("content");
                    Log.d("ddf getCommentList",new String(responseBody));
                    Type listType = new TypeToken<List<BoardCommentVO>>() {
                    }.getType();

                    List<BoardCommentVO> commentList = new Gson().fromJson(result, listType);

                    callBack.onDataListLoaded(commentList);
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
    public void updateData(RequestParams params, final LoadDataCallBack callBack) {
        final String URL = "/community/updateComment";

        HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                callBack.onDataLoaded("success");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                callBack.onDataNotAvailable();
            }
        });
    }

    @Override
    public void deleteData(RequestParams params, final LoadDataCallBack callBack) {
        final String URL = "/community/deleteComment";

        HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                callBack.onDataLoaded("success");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                callBack.onDataNotAvailable();
            }
        });
    }

    @Override
    public void sendReport(RequestParams params, LoadDataCallBack callBack) {

    }

    @Override
    public void insertData(RequestParams params, final LoadDataCallBack callBack) {
        final String URL = "/community/insertComment";
        HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("ddf insertComment", new String(responseBody));
                callBack.onDataLoaded("success");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                callBack.onDataNotAvailable();
            }
        });
    }

}
