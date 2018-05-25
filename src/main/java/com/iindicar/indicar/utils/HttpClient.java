package com.iindicar.indicar.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by yeseul on 2018-02-23.
 */

public class HttpClient {
    final int DEFAULT_TIMEOUT = 20 * 1000;
    private static final String BASE_URL = "http://13.125.173.118:8080";
    private static AsyncHttpClient client = new AsyncHttpClient();
    private static AsyncHttpClient multipartClient = new AsyncHttpClient();


    private HttpClient(){
        client.setTimeout(DEFAULT_TIMEOUT);
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteURL(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteURL(url), params, responseHandler);
    }

    private static String getAbsoluteURL(String url){
        return BASE_URL + url;
    }

    public static void uploadFiles(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        params.setForceMultipartEntityContentType(true);
//        client.setURLEncodingEnabled(false);
        client.post(getAbsoluteURL(url), params, responseHandler);
    }

}
