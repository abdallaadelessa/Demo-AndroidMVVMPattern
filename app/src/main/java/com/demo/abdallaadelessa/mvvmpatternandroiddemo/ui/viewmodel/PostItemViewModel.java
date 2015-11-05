package com.demo.abdallaadelessa.mvvmpatternandroiddemo.ui.viewmodel;

import android.content.Context;

import com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.beans.Post;

/**
 * Created by abdallah on 29/10/15.
 */
public class PostItemViewModel {
    private Context context;
    private Post post;

    public PostItemViewModel(Context context, Post post) {
        this.context = context;
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
