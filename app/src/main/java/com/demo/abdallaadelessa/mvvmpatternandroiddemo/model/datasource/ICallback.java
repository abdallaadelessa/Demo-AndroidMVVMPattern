package com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.datasource;

/**
 * Created by abdallah on 29/10/15.
 */
public interface ICallback<T> {
    public void onSuccess(T t);
    public void onFailure(Exception e);
}
