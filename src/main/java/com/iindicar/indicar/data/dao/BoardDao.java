package com.iindicar.indicar.data.dao;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iindicar.indicar.R;
import com.iindicar.indicar.data.vo.BoardDetailVO;
import com.iindicar.indicar.utils.HttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by yeseul on 2018-04-13.
 */

public class BoardDao implements BaseDao<BoardDetailVO> {

    private final String TAG = BoardDao.class.getSimpleName();

    @Override
    public void getDataList(RequestParams params, final LoadDataListCallBack callBack) {
        final String URL = "/community/selectBoardArticles";
        HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int index, Header[] headers, byte[] bytes) {
                try {
                    Log.d("ddf",new String(bytes));
                    JsonElement jsonElement = new JsonParser().parse(new String(bytes)).getAsJsonObject();
                    JsonObject rootObj = jsonElement.getAsJsonObject();
                    JsonArray result = (JsonArray) rootObj.get("content");

                    List<BoardDetailVO> boardList = new ArrayList<>();

                    for (int i = 0; i < result.size(); i++) {
                        if (!result.get(i).isJsonObject()) { // 게시물 끝
                            break;
                        }
                        BoardDetailVO vo = new Gson().fromJson(result.get(i), BoardDetailVO.class);
                        boardList.add(vo);
                    }

                    callBack.onDataListLoaded(boardList);
                } catch (Exception e) {

                    callBack.onDataNotAvailable();

                    Log.e(TAG, "getDataList() with URL: " + URL + " " + e.toString());
                    e.printStackTrace();
                    return;
                }

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.e(TAG, "getDataList() with URL: " + URL + " " + bytes.toString());
                throwable.printStackTrace();
                callBack.onDataNotAvailable();
            }
        });
    }

    public void getDataListTrace(String category, RequestParams params, final LoadDataListCallBack callBack) {
        final String URL;
        if (category.equals("like")) {
            URL = "/community/selectUserLikeBoardArticle";
        } else if (category.equals("mine")) {
            URL = "/community/selectUserMyBoardArticle";
        } else {
            URL = "/community/selectUserMyCommentList";
        }
        HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int index, Header[] headers, byte[] bytes) {
                JsonElement result;
                try {
                    result = new JsonParser().parse(new String(bytes));
                } catch (Exception e) {
                    callBack.onDataNotAvailable();
                    Log.e(TAG, "getDataList() with URL: " + URL + " " + R.string.data_not_available);
                    e.printStackTrace();
                    return;
                }
                // 게시물 리스트 존재
                if (result != null && result.isJsonArray()) {
                    JsonArray array = result.getAsJsonArray();

                    List<BoardDetailVO> boardList = new ArrayList<>();
                    for (int i = 0; i < array.size(); i++) {
                        if (!array.get(i).isJsonObject()) { // 게시물 끝
                            break;
                        }
                        BoardDetailVO vo = new Gson().fromJson(array.get(i), BoardDetailVO.class);
                        boardList.add(vo);
                    }

                    callBack.onDataListLoaded(boardList);
                } else {

                    Log.e(TAG, "getDataList() with URL: " + URL + " " + R.string.data_not_available);

                    callBack.onDataNotAvailable();
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
    public void getDataListLike(RequestParams params, final LoadDataListCallBack callBack) {
        final String URL = "/community/selectUserLikeBoardArticles";
        HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int index, Header[] headers, byte[] bytes) {
                JsonElement result;

                try {
                    result = new JsonParser().parse(new String(bytes));
                } catch (Exception e) {

                    callBack.onDataNotAvailable();

                    Log.e(TAG, "getDataList() with URL: " + URL + " " + R.string.data_not_available);
                    e.printStackTrace();
                    return;
                }

                // 게시물 리스트 존재
                if (result != null && result.isJsonArray()) {
                    JsonArray array = result.getAsJsonArray();

                    List<BoardDetailVO> boardList = new ArrayList<>();

                    for (int i = 0; i < array.size(); i++) {
                        if (!array.get(i).isJsonObject()) { // 게시물 끝
                            break;
                        }
                        BoardDetailVO vo = new Gson().fromJson(array.get(i), BoardDetailVO.class);
                        boardList.add(vo);
                    }

                    callBack.onDataListLoaded(boardList);
                } else {

                    Log.e(TAG, "getDataList() with URL: " + URL + " " + R.string.data_not_available);

                    callBack.onDataNotAvailable();
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
    public void getData(RequestParams params, final LoadDataCallBack callBack) {
        final String URL = "/community/selectBoardArticle";

        HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int index, Header[] headers, byte[] bytes) {
                try {
                    BoardDetailVO board = new Gson().fromJson(new String(bytes), BoardDetailVO.class);
                    callBack.onDataLoaded(board);
                } catch (Exception e) {

                    Log.e(TAG, "getData() with URL: " + URL + " " + R.string.data_not_available);
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
        final String URL = "/community/insertBoardArticle";

        HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response;

                try {
                    response = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(response);
                    String jsonResult = jsonObject.getString("result");
                    if (jsonResult.equals("S")) {
                        callBack.onDataLoaded("success");
                    } else {
                        Log.e(TAG, "insertData() with URL: " + URL + " " + "Fail_upload");
                        callBack.onDataNotAvailable();
                    }
                } catch (Exception e) {
                    callBack.onDataNotAvailable();
                    Log.e(TAG, "insertData() with URL: " + URL + " " + e.toString());
                    e.printStackTrace();
                    return;
                }

                // 게시글 등록 정상적으로 처리
                Log.d("ddf", response);


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Log.e(TAG, "insertData() with URL: " + URL + " " + R.string.server_not_available);
                error.printStackTrace();

                callBack.onDataNotAvailable();
            }
        });
    }


    @Override
    public void updateData(RequestParams params, final LoadDataCallBack callBack) {
        final String URL = "/community/updateBoardArticle";

        HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // 요청 성공시 수정된 게시글 정보 리턴
                try {
                    BoardDetailVO board = new Gson().fromJson(new String(responseBody), BoardDetailVO.class);
                    callBack.onDataLoaded(board);
                } catch (Exception e) {

                    Log.e(TAG, "updateData() with URL: " + URL + " " + R.string.data_not_available);
                    e.printStackTrace();

                    callBack.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Log.e(TAG, "updateData() with URL: " + URL + " " + R.string.server_not_available);
                error.printStackTrace();

                callBack.onDataNotAvailable();
            }
        });
    }

    @Override
    public void deleteData(RequestParams params, final LoadDataCallBack callBack) {
        final String URL = "/community/deleteBoardArticle";

        HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = responseBody.toString();

                // 게시글 삭제 정상 처리
                if (response != null) {
                    callBack.onDataLoaded("success");
                } else {

                    Log.e(TAG, "deleteData() with URL: " + URL + " " + R.string.data_not_available);

                    callBack.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Log.e(TAG, "deleteData() with URL: " + URL + " " + R.string.server_not_available);
                error.printStackTrace();

                callBack.onDataNotAvailable();
            }
        });
    }

    @Override
    public void sendReport(RequestParams params, final LoadDataCallBack callBack) {
        final String URL = "/community/insertBbsReport";

        HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                callBack.onDataLoaded("success");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Log.e(TAG, "insertData() with URL: " + URL + " " + R.string.server_not_available);
                error.printStackTrace();

                callBack.onDataNotAvailable();
            }
        });
    }

    public LikeModel getLikeModel() {

        return new LikeModel() {

            @Override
            public void toggleLike(RequestParams params, final LoadDataCallBack callBack) {
                final String URL = "/community/likeBoardArticle";

                HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        callBack.onDataLoaded("success");
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        Log.e(TAG, "toggleLike() with URL: " + URL + " " + R.string.server_not_available);
                        error.printStackTrace();

                        callBack.onDataNotAvailable();
                    }
                });
            }

            @Override
            public void getLikeList(RequestParams params, final LoadDataListCallBack callBack) {
                final String URL = "/community/selectUserLikeBoardArticle";

                HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        JsonElement result;

                        try {
                            result = new JsonParser().parse(new String(responseBody));
                        } catch (Exception e) {

                            callBack.onDataNotAvailable();

                            Log.e(TAG, "getLikeList() with URL: " + URL + " " + R.string.data_not_available);
                            e.printStackTrace();
                            return;
                        }

                        // 결과 존재
                        if (result != null && result.isJsonArray()) {

                            JsonArray array = result.getAsJsonArray();
                            List<BoardDetailVO> boardList = new ArrayList<>();
                            for (int i = 0; i < array.size(); i++) {
                                BoardDetailVO vo = new Gson().fromJson(array.get(i), BoardDetailVO.class);
                                boardList.add(vo);
                            }
                            callBack.onDataListLoaded(boardList);
                        } else {

                            Log.e(TAG, "getLikeList() with URL: " + URL + " " + R.string.data_not_available);

                            callBack.onDataNotAvailable();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        Log.e(TAG, "getLikeList() with URL: " + URL + " " + R.string.server_not_available);
                        error.printStackTrace();

                        callBack.onDataNotAvailable();
                    }
                });
            }
        };
    }

    public interface LikeModel {

        void toggleLike(RequestParams params, LoadDataCallBack callBack);

        void getLikeList(RequestParams params, LoadDataListCallBack callBack);
    }

}
