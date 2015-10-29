package com.demo.abdallaadelessa.mvvmpatternandroiddemo.ui.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.demo.abdallaadelessa.mvvmpatternandroiddemo.databinding.AdapterPostItemBinding;
import com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.beans.Post;
import com.demo.abdallaadelessa.mvvmpatternandroiddemo.ui.viewmodel.PostItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abdallah on 29/10/15.
 */
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {
    private List<Post> posts;

    public PostsAdapter() {
        posts = new ArrayList<>();
    }

    public PostsAdapter(List<Post> posts) {
        this.posts = posts;
    }

    public void addAll(List<Post> posts) {
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    @Override
    public PostsAdapter.PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final AdapterPostItemBinding postItemBinding = AdapterPostItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PostViewHolder(postItemBinding);
    }

    @Override
    public void onBindViewHolder(PostsAdapter.PostViewHolder holder, int position) {
        final Context context = holder.itemView.getContext();
        holder.getBinding().setPostItemViewModel(new PostItemViewModel(context, posts.get(position)));
    }

    @Override
    public int getItemCount() {
        return posts != null ? posts.size() : 0;
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        private AdapterPostItemBinding binding;

        public PostViewHolder(AdapterPostItemBinding binding) {
            super(binding.itemview);
            this.binding = binding;
        }

        public AdapterPostItemBinding getBinding() {
            return binding;
        }
    }
}
