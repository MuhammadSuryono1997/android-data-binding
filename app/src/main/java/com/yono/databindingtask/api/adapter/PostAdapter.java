package com.yono.databindingtask.api.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.yono.databindingtask.R;
import com.yono.databindingtask.api.response.PostResponse;
import com.yono.databindingtask.databinding.NewItemPostsBinding;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    Context context;
    private ArrayList<PostResponse> postResponseArrayList = new ArrayList<>();

    public void setPostAdapter(Context context, final ArrayList<PostResponse> postResponsesList){
        this.context = context;
        this.postResponseArrayList = postResponsesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewItemPostsBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.new_item_posts,
                parent,
                false
        );

        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        holder.binData(postResponseArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return postResponseArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        NewItemPostsBinding binding;
        public ViewHolder(@NonNull NewItemPostsBinding binding) {

            super(binding.getRoot());
            this.binding = binding;
        }

        public void binData(PostResponse postResponse){
            binding.setPost(postResponse);
        }
    }
}
