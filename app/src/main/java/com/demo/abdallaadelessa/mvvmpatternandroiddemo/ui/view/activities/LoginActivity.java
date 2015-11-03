package com.demo.abdallaadelessa.mvvmpatternandroiddemo.ui.view.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.demo.abdallaadelessa.mvvmpatternandroiddemo.R;
import com.demo.abdallaadelessa.mvvmpatternandroiddemo.databinding.ActivityLoginBinding;
import com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.beans.BindableString;
import com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.beans.User;
import com.demo.abdallaadelessa.mvvmpatternandroiddemo.ui.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        User user = new User(new BindableString("test"), new BindableString("test"));
        LoginViewModel loginViewModel = new LoginViewModel(this, user);
        loginBinding.setViewModel(loginViewModel);
    }
}
