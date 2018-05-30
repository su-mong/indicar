package com.iindicar.indicar.data.dao;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.iindicar.indicar.data.vo.BoardCommentVO;
import com.iindicar.indicar.utils.HttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.lang.reflect.Type;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by yeseul on 2018-04-13.
 */

public class BoardCommentDao implements BaseDao<BoardCommentVO> {

    @Override
    public void getDataList(RequestParams params, final LoadDataListCallBack callBack) {
        final String URL = "/selectCommentList";

        HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JsonElement result;
                try {
                    Log.d("ddf json1",new String(responseBody));
                    result = new JsonParser().parse(new String(responseBody));
                } catch (Exception e) {
                    e.printStackTrace();
                    callBack.onDataNotAvailable();
                    return;
                }

                // 댓글 존재
                if (result.isJsonArray()) {
                    Log.d("ddf json",result.toString());
                    String tempStr=result.toString().replace(",null","");
                    JsonArray array = result.getAsJsonArray();
                    JsonArray temp = new JsonArray();
                    for (int i = 0; i < array.size(); i++) {
                        if (!array.get(i).toString().equals("null"))
                            temp.add(array.get(i).toString());
                    }
                    JsonParser parser = new JsonParser();
                    JsonElement o = parser.parse(tempStr);

                    Type listType = new TypeToken<List<BoardCommentVO>>() {
                    }.getType();
                    List<BoardCommentVO> commentList = new Gson().fromJson(o, listType);
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
        final String URL = "/updateComment";

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
        final String URL = "/deleteComment";

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
        final String URL = "/insertComment";
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

}
