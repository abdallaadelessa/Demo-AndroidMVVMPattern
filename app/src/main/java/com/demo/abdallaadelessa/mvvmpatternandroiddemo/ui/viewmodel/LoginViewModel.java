package com.demo.abdallaadelessa.mvvmpatternandroiddemo.ui.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.beans.BindableString;
import com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.beans.User;

/**
 * Created by abdulla on 10/31/15.
 */
public class LoginViewModel {
    public static final int KEY = 1;
    private Context context;
    private User user;

    public LoginViewModel(Context context, User user) {
        this.context = context;
        this.user = user;
    }

    // ------------------------>

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // ------------------------>
    @BindingAdapter({"app:binding"})
    public static void bindEditText(EditText view, final BindableString bindableString) {
        if(view.getTag() == null) {
            view.setTag(true);
            view.addTextChangedListener(new TextWatcherAdapter() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    bindableString.set(s.toString());
                }
            });
        }
        String newValue = bindableString.get();
        if(!view.getText().toString().equals(newValue)) {
            view.setText(newValue);
        }
    }

    // ------------------------>

    public void onLoginClicked(View view) {
        getUser().setUsername(new BindableString("abdalla"));
        getUser().setPassword(new BindableString("123456"));
    }

    public void onPrintClicked(View view) {
        Log.e("DEBUG", getUser().toString());
    }


    private static class TextWatcherAdapter implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
