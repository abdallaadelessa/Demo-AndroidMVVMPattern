package com.demo.abdallaadelessa.mvvmpatternandroiddemo.ui.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.beans.Post;
import com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.datasource.DataSourceProvider;
import com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.datasource.ICallback;

import java.util.List;

/**
 * Created by abdallah on 29/10/15.
 */
public class MainViewModel extends BaseObservable {
    // Title
    public ObservableField<String> title;
    // Progress
    public ObservableInt progressVisibility;
    // Place Holder
    public ObservableField<String> placeHolderText;
    public ObservableInt placeHolderVisibility;
    // Context
    private Context context;
    // Listener
    private DataListener dataListener;

    // ---------------------->

    public MainViewModel(Context context, DataListener dataListener) {
        this.context = context;
        this.dataListener = dataListener;
        progressVisibility = new ObservableInt(View.GONE);
        placeHolderVisibility = new ObservableInt(View.GONE);
        placeHolderText = new ObservableField<>("");
        title = new ObservableField<>("Main Activity");
        loadPosts();
    }

    public void loadPosts() {
        progressVisibility.set(View.VISIBLE);
        placeHolderVisibility.set(View.GONE);
        new DataSourceProvider().getPosts(new ICallback<List<Post>>() {
            @Override
            public void onSuccess(List<Post> posts) {
                progressVisibility.set(View.GONE);
                placeHolderVisibility.set(View.GONE);
                if(dataListener != null) dataListener.onPostsChanged(posts);
            }

            @Override
            public void onFailure(Exception e) {
                progressVisibility.set(View.GONE);
                placeHolderVisibility.set(View.VISIBLE);
                placeHolderText.set("error please try again");
            }
        });
    }

    // ---------------------->

    public interface DataListener {
        void onPostsChanged(List<Post> posts);
    }
}
