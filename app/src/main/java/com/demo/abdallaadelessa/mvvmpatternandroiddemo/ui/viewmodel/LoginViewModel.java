package com.demo.abdallaadelessa.mvvmpatternandroiddemo.ui.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.util.Log;

import com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.beans.User;

/**
 * Created by abdulla on 10/31/15.
 */
public class LoginViewModel extends BaseObservable implements LoginEventHandler.ILoginEventHandler {

    private Context context;
    private User user;
    private ObservableField<String> username;
    private ObservableField<String> password;

    public LoginViewModel(Context context, User user) {
        this.context = context;
        this.user = user;
        this.username = new ObservableField<>("test");
        this.password = new ObservableField<>("test");
    }

    // ------------------------>

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ObservableField<String> getUsername() {
        return username;
    }

    public void setUsername(ObservableField<String> username) {
        this.username = username;
    }

    public ObservableField<String> getPassword() {
        return password;
    }

    public void setPassword(ObservableField<String> password) {
        this.password = password;
    }

    // ------------------------>

    @Override
    public void navigate() {
        getUser().setUsername("abdalla");
        getUser().setPassword("123456");
        getUsername().set("abdalla");
        getPassword().set("123456");
    }

    @Override
    public void print() {
        Log.e("DEBUG", getUser().toString());
        Log.e("DEBUG", "Username : " + getUsername().get() + " Password : " + getPassword().get());
    }

}
