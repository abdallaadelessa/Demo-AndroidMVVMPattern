package com.demo.abdallaadelessa.mvvmpatternandroiddemo.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.cache.DiskLruBasedCache;
import com.android.volley.cache.plus.SimpleImageLoader;
import com.android.volley.toolbox.Volley;
import com.demo.abdallaadelessa.mvvmpatternandroiddemo.MyApplication;

public class VolleySingleton {
    public static final String ERROR_RESPONSE_TAG = "message";
    //---------------------->
    public static String ERROR_TIME_OUT;
    public static String ERROR_INTERNET_CONNECTION;
    public static String ERROR_SERVER_ERROR;
    public static String ERROR_UNKNOWN_ERROR;
    //---------------------->
    private static VolleySingleton mInstance = null;
    private RequestQueue mRequestQueue;
    private SimpleImageLoader mImageLoader;

    //---------------------->
    private VolleySingleton() {
        VolleyLog.DEBUG = isTestMode();
        Context appContext = getApplicationContent();
        initErrorStrings(appContext);
        mRequestQueue = Volley.newRequestQueue(appContext);
        DiskLruBasedCache.ImageCacheParams cacheParams = new DiskLruBasedCache.ImageCacheParams(appContext, "CacheDirectory");
        cacheParams.setMemCacheSizePercent(0.5f);
        mImageLoader = new SimpleImageLoader(appContext, cacheParams);
    }

    private void initErrorStrings(Context context) {
        ERROR_TIME_OUT = "Time out";//context.getResources().getString(R.string.error_timeout);
        ERROR_INTERNET_CONNECTION = "No Internet Connection";// context.getResources().getString(R.string.error_timeout);
        ERROR_SERVER_ERROR = "Server Error";//context.getResources().getString(R.string.error_timeout);
        ERROR_UNKNOWN_ERROR = "Unknown Error";//context.getResources().getString(R.string.error_timeout);
    }

    //---------------------->

    private static Context getApplicationContent() {
        return MyApplication.getAppContext();
    }

    private static boolean isTestMode() {
        return true;
    }

    public static void log(String text) {

    }

    public static void logError(Exception e) {

    }

    public static void reportError(Exception e, boolean fatal) {

    }

    public static void showToast(Context cxt, String message) {

    }

    //---------------------->

    public static VolleySingleton getInstance() {
        if(mInstance == null) {
            mInstance = new VolleySingleton();
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return this.mRequestQueue;
    }

    public SimpleImageLoader getImageLoader() {
        return this.mImageLoader;
    }
}