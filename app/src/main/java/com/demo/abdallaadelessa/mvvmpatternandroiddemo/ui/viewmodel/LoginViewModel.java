package com.demo.abdallaadelessa.mvvmpatternandroiddemo.ui.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.beans.BindableString;
import com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.beans.Comments;
import com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.beans.User;
import com.demo.abdallaadelessa.mvvmpatternandroiddemo.network.VolleyRequestManager;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by abdulla on 10/31/15.
 */
public class LoginViewModel {
    public static final int KEY = 1;
    private Context context;
    private User user;
    private Observable<List<Comments>> comments;
    private Subscriber<List<Comments>> subscriber;

    public LoginViewModel(Context context, User user) {
        this.context = context;
        this.user = user;
        subscriber = new Subscriber<List<Comments>>() {
            @Override
            public void onCompleted() {
                Log.e("DEBUG", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("DEBUG", e.getMessage());
            }

            @Override
            public void onNext(List<Comments> comments) {
                Log.e("DEBUG", "onNext");
            }
        };
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

    public void onLoginClicked(final View view) {
        getUser().setUsername(new BindableString("abdalla"));
        getUser().setPassword(new BindableString("123456"));
        Handler handler =new Handler() ;
        String url = "http://jsonplaceholder.typicode.com/comments";
        TypeToken<List<Comments>> typeToken = new TypeToken<List<Comments>>(){};
        comments = new VolleyRequestManager<List<Comments>>().doMakeRequest(Request.Method.GET, url,typeToken.getType(), "Comments");
        comments.subscribeOn(Schedulers.newThread());
        comments.observeOn(Schedulers.newThread());
        comments.subscribe(subscriber);
    }

    public void onPrintClicked(View view) {
        Log.e("DEBUG", getUser().toString());
    }

    public void onStop() {
        comments.unsafeSubscribe(subscriber);
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
