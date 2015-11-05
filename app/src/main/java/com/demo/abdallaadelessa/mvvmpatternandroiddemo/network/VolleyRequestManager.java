package com.demo.abdallaadelessa.mvvmpatternandroiddemo.network;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by abdulla on 8/12/15.
 */
public class VolleyRequestManager<T> {
    // ----> File Upload Parameters
    private static final int UPLOAD_FILE_SOCKET_TIMEOUT = 20000;
    private static final RetryPolicy FILE_UPLOAD_RETRY_POLICY = new DefaultRetryPolicy(UPLOAD_FILE_SOCKET_TIMEOUT, 0, 0);
    // ----> Default Parameters
    private static final int DEFAULT_SOCKET_TIMEOUT = 5000;
    private static final int DEFAULT_MAX_RETRIES = 2;
    private static final float DEFAULT_BACKOFF_MULT = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    private static final DefaultRetryPolicy DEFAULT_RETRY_POLICY = new DefaultRetryPolicy(DEFAULT_SOCKET_TIMEOUT, DEFAULT_MAX_RETRIES, DEFAULT_BACKOFF_MULT);
    // ---->

    // Main Function
    public Observable<T> doMakeRequest(final int method, final String url, final Map<String, String> params, final Type classType, final RequestAttributes attributes) {
        final RequestQueue queue = VolleySingleton.getInstance().getRequestQueue();
        Observable<T> observable = Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(final Subscriber<? super T> subscriber) {
                subscriber.onStart();
                // Cancel All By Tag
                checkRequestIsRunning(queue, attributes);
                StringRequest jsObjRequest = new StringRequest(method, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        VolleySingleton.log(String.format("Request Tag: %s , Response Result: %s", attributes.tag, json));
                        try {
                            T t = parseResponse(json, classType);
                            subscriber.onNext(t);
                        }
                        catch(JSONException e) {
                            subscriber.onError(e);
                            VolleySingleton.reportError(new Exception(String.format("JSONException , Class Type : %s , Url : %s , Method : %s , Json : %s", classType, url, method, json)), true);
                        }
                        subscriber.onCompleted();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError e) {
                        VolleySingleton.log("Volley Error: " + VolleyErrorHandler.volleyErrorToString(e));
                        subscriber.onError(e);
                        subscriber.onCompleted();
                        if(VolleyErrorHandler.isServerProblem(e)) {
                            VolleySingleton.reportError(e, true);
                        }
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        if(params == null) {
                            return new HashMap<>();
                        }
                        else {
                            return params;
                        }
                    }
                };
                addRequestAttributes(jsObjRequest, attributes);
                queue.add(jsObjRequest);
            }
        });
        return observable;
    }

    // Retry Policy depends on ("@isUploadingFile") parameter
    public Observable<T> doMakeRequest(int method, String url, final Map<String, String> params, final Type classType, String tag, boolean isUploadingFile) {
        if(isUploadingFile) VolleySingleton.log("Uploading File Request");
        else VolleySingleton.log("Normal Data Request");
        return doMakeRequest(method, url, params, classType, new RequestAttributes(tag, isUploadingFile ? FILE_UPLOAD_RETRY_POLICY : DEFAULT_RETRY_POLICY));
    }

    // Default Retry Policy
    public Observable<T> doMakeRequest(int method, String url, final Map<String, String> params, Type classType, String tag) {
        return doMakeRequest(method, url, params, classType, tag, false);
    }

    // Without parameters
    public Observable<T> doMakeRequest(int method, String url, final Type classType, String tag) {
        return doMakeRequest(method, url, null, classType, tag, false);
    }

    // -------> Helper Methods

    private void checkRequestIsRunning(final RequestQueue requestQueue, final RequestAttributes attributes) {
        if(attributes.cancelAllByTagIfRunning && attributes.tag != null) {
            requestQueue.cancelAll(new RequestQueue.RequestFilter() {
                @Override
                public boolean apply(Request<?> request) {
                    return request != null && attributes.tag.equals(request.getTag());
                }
            });
        }
    }

    private T parseResponse(String json, Type classType) throws JSONException {
        T t = null;
        if(classType == String.class) {
            t = (T) json;
        }
        else {
            Object parsedData = new JSONTokener(json).nextValue();
            if(parsedData instanceof JSONObject) {
                JSONObject response = new JSONObject(json);
                if(classType == JSONObject.class) {
                    t = (T) response;
                }
                else {
                    t = (T) new Gson().fromJson(json, classType);
                }
            }
            else if(parsedData instanceof JSONArray) {
                t = (T) new Gson().fromJson(json, classType);
            }
        }
        return t;
    }

    private void addRequestAttributes(StringRequest jsObjRequest, RequestAttributes attributes) {
        jsObjRequest.setRetryPolicy(attributes.retryPolicy);
        if(attributes.tag != null) {
            jsObjRequest.setTag(attributes.tag);
        }
        jsObjRequest.setShouldCache(false);
    }

    private static class RequestAttributes {
        public String tag;
        public RetryPolicy retryPolicy;
        public boolean cancelAllByTagIfRunning;

        public RequestAttributes(String tag, RetryPolicy retryPolicy, boolean cancelAllByTagIfRunning) {
            this.tag = tag;
            this.retryPolicy = retryPolicy;
            this.cancelAllByTagIfRunning = cancelAllByTagIfRunning;
        }

        public RequestAttributes(String tag, RetryPolicy retryPolicy) {
            this(tag, retryPolicy, true);
        }

        public RequestAttributes(String tag) {
            this(tag, DEFAULT_RETRY_POLICY, true);
        }

        public RequestAttributes() {
            this(null, DEFAULT_RETRY_POLICY, true);
        }
    }
}
