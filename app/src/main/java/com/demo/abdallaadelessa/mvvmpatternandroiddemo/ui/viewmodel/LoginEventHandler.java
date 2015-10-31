package com.demo.abdallaadelessa.mvvmpatternandroiddemo.ui.viewmodel;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.EditText;

/**
 * Created by abdulla on 10/31/15.
 */
public class LoginEventHandler {
    private ILoginEventHandler iLoginEventHandler;

    public LoginEventHandler(ILoginEventHandler iLoginEventHandler) {
        this.iLoginEventHandler = iLoginEventHandler;
    }

    // ------------------------>

    public void onLoginClicked(View view) {
        iLoginEventHandler.navigate();
    }

    public void onPrintClicked(View view) {
        iLoginEventHandler.print();
    }

    @BindingAdapter("binding")
    public static void bindEditText(EditText editText, CharSequence value) {
        if (!editText.getText().toString().equals(value.toString())) {
            editText.setText(value);
        }
    }

    // ------------------------>

    public interface ILoginEventHandler {
        void navigate();

        void print();
    }
}