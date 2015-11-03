package com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.beans;

import android.databinding.BaseObservable;
import android.databinding.BindingConversion;

/**
 * Created by abdallah on 03/11/15.
 */
public class BindableString extends BaseObservable {
    private String value;

    public BindableString() {
    }

    public BindableString(String value) {
        this.value = value;
    }

    public String get() {
        return value != null ? value : "";
    }

    public void set(String value) {
        if(!this.value.equals(value)) {
            this.value = value;
            notifyChange();
        }
    }

    public boolean isEmpty() {
        return value == null || value.isEmpty();
    }

    @BindingConversion
    public static String convertBindableToString(BindableString bindableString) {
        return bindableString.get();
    }
}
