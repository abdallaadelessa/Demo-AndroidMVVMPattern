package com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.datasource;

import android.os.Handler;
import android.os.Looper;

import com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.beans.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abdallah on 29/10/15.
 */
public class DataSourceProvider implements IDataProvider {
    @Override
    public void getPosts(final ICallback<List<Post>> callback) {
        final Handler handler = new Handler(Looper.getMainLooper());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
                final List<Post> posts = new ArrayList<>();
                for(int i = 0; i < 10; i++) {
                    posts.add(new Post("Comment " + i, "User " + i, "25/10/2015", "25/10/2015"));
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(posts);
                        // callback.onFailure(new Exception());
                    }
                });
            }
        }).start();
    }
}
