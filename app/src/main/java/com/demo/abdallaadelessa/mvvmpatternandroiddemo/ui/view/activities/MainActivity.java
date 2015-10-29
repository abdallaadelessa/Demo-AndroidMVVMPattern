package com.demo.abdallaadelessa.mvvmpatternandroiddemo.ui.view.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.demo.abdallaadelessa.mvvmpatternandroiddemo.R;
import com.demo.abdallaadelessa.mvvmpatternandroiddemo.databinding.ActivityMainBinding;
import com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.beans.Post;
import com.demo.abdallaadelessa.mvvmpatternandroiddemo.ui.view.adapters.PostsAdapter;
import com.demo.abdallaadelessa.mvvmpatternandroiddemo.ui.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainViewModel.DataListener {
    private ActivityMainBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initUI(dataBinding);
        dataBinding.setMainViewModel(new MainViewModel(this, this));

    }

    private void initUI(ActivityMainBinding dataBinding) {
        setSupportActionBar(dataBinding.toolbar);
        dataBinding.rvList.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.rvList.setAdapter(new PostsAdapter());
    }

    @Override
    public void onPostsChanged(List<Post> posts) {
        PostsAdapter adapter = (PostsAdapter) dataBinding.rvList.getAdapter();
        adapter.addAll(posts);
    }
}
