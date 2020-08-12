package com.yono.databindingtask.api.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.yono.databindingtask.R;
import com.yono.databindingtask.api.models.Users;
import com.yono.databindingtask.api.response.PostResponse;
import com.yono.databindingtask.databinding.NewItemPostsBinding;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> implements Filterable {
    Context context;
    private ArrayList<PostResponse> postResponseArrayList = new ArrayList<>();
    private ArrayList<PostResponse> postResponseArrayListFiltered;

    public void setPostAdapter(Context context, final ArrayList<PostResponse> postResponsesList){
        this.context = context;
        this.postResponseArrayList = postResponsesList;
        this.postResponseArrayListFiltered = postResponsesList;
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
        holder.binData(postResponseArrayListFiltered.get(position));
    }

    @Override
    public int getItemCount() {
        return postResponseArrayListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    postResponseArrayListFiltered = postResponseArrayList;
                } else {
                    ArrayList<PostResponse> filteredList = new ArrayList<>();
                    for (PostResponse post : postResponseArrayList) {
                        if (post.getTitlePosts().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(post);
                        }
                    }
                    postResponseArrayListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = postResponseArrayListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                postResponseArrayListFiltered = (ArrayList<PostResponse>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
