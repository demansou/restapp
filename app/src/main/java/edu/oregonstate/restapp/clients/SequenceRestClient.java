package edu.oregonstate.restapp.clients;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class SequenceRestClient {
    /* amazon aws server hosting REST API */
    private static final String BASE_URL = "http://54.71.203.108:8080/";

    /* initialize async conneciton object */
    private static AsyncHttpClient client = new AsyncHttpClient();

    /* HTTP GET request */
    public static void get(Context context, String url, Header[] headers, RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {
        client.get(context, getAbsoluteUrl(url), headers, params, responseHandler);
    }

    /* HTTP POST request
    public static void post(Context context, String url, Header[] headers, RequestParams params,
                            AsyncHttpResponseHandler responseHandler) {
        client.post(context, getAbsoluteUrl(url), headers, params, responseHandler);
    }
    */

    /* Converts relative urls to absolute urls */
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
