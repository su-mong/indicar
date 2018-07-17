package com.iindicar.indicar.data.dao;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.iindicar.indicar.R;
import com.iindicar.indicar.data.vo.BoardDetailVO;
import com.iindicar.indicar.data.vo.BoardFileVO;
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

public class BoardFileDao implements BaseDao<BoardFileVO> {

    private static final String TAG = BoardFileDao.class.getSimpleName();

    @Override
    public void getDataList(RequestParams params, final LoadDataListCallBack callBack) {
        final String URL = "/community/selectFileInfs";

        HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int index, Header[] headers, byte[] bytes) {
                try {
                    Type listType = new TypeToken<List<BoardFileVO>>() {}.getType();
                    List<BoardFileVO> fileList = new Gson().fromJson(new String(bytes), listType);
                    callBack.onDataListLoaded(fileList);
                } catch (Exception e){

                    callBack.onDataNotAvailable();

                    Log.e(TAG, "getDataList() with URL: " + URL + " " + R.string.data_not_available);
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                Log.e(TAG, "getDataList() with URL: " + URL + " " + R.string.server_not_available);
                throwable.printStackTrace();

                callBack.onDataNotAvailable();
            }
        });
    }


    @Override
    public void getData(RequestParams params, final LoadDataCallBack callBack){
        final String URL = "/community/selectFileInfs";

        HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int index, Header[] headers, byte[] bytes) {
                try {
//                    Type listType = new TypeToken<List<BoardFileVO>>() {}.getType();
                    JsonElement jsonElement = new JsonParser().parse(new String(bytes)).getAsJsonObject();
                    JsonObject rootObj = jsonElement.getAsJsonObject();
                    JsonArray result = (JsonArray) rootObj.get("content");


                    List<BoardFileVO> array = new ArrayList<>();
                    BoardFileVO vo = new Gson().fromJson(result.get(0), BoardFileVO.class);
                    array.add(vo);

                    callBack.onDataLoaded(array.get(0));
                } catch (Exception e){

                    Log.e(TAG, "getData() with URL: " + URL + " " + e.toString());
                    e.printStackTrace();

                    callBack.onDataNotAvailable();
                }
            }
            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                Log.e(TAG, "getData() with URL: " + URL + " " + R.string.server_not_available);
                throwable.printStackTrace();

                callBack.onDataNotAvailable();
            }
        });
    }

    @Override
    public void insertData(RequestParams params, final LoadDataCallBack callBack) {
        final String URL = "/community/insertFiles";

        HttpClient.uploadFiles(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response;

                try {
                    response = new String(responseBody);
                    JsonParser parser = new JsonParser();
                    JsonElement rootObejct = parser.parse(response)
                            .getAsJsonObject().get("content");
                    response=rootObejct.getAsString();
                } catch (Exception e){

                    Log.e(TAG, "getData() with URL: " + URL + " " + R.string.data_not_available);
                    e.printStackTrace();

                    callBack.onDataNotAvailable();
                    return;
                }

                // 파일 업로드 후 file_acth_id 리턴
                callBack.onDataLoaded(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Log.e(TAG, "getData() with URL: " + URL + " " + R.string.server_not_available);
                error.printStackTrace();

                callBack.onDataNotAvailable();
            }
        });
    }

    @Override
    public void updateData(RequestParams params, final LoadDataCallBack callBack) {
        final String URL = "/community/updateFile";

        HttpClient.uploadFiles(URL, params, new AsyncHttpResponseHandler() {
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
        final String URL = "/community/deleteFile";

        HttpClient.uploadFiles(URL, params, new AsyncHttpResponseHandler() {
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
}
