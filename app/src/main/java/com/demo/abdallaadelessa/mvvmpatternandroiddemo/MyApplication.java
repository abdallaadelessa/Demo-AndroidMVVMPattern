package com.demo.abdallaadelessa.mvvmpatternandroiddemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by abdallah on 05/11/15.
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getAppContext() {
        return context;
    }
}
