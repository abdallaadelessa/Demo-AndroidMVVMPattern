package com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.datasource;

import com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.beans.Post;

import java.util.List;

/**
 * Created by abdallah on 29/10/15.
 */
public interface IDataProvider {
    void getPosts(ICallback<List<Post>> callback);
}
