package com.yono.databindingtask;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.yono.databindingtask.api.adapter.PostAdapter;
import com.yono.databindingtask.api.models.MainViewModelsPosts;
import com.yono.databindingtask.api.response.PostResponse;

import java.util.ArrayList;

public class NewPosts extends AppCompatActivity implements LifecycleOwner {

    MainViewModelsPosts viewModelsPosts;
    RecyclerView recyclerView;
    PostAdapter postAdapter;

    ProgressBar progressBar;
    EditText editTextSearch;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_posts);
        recyclerView = findViewById(R.id.rvPosts);
        progressBar = findViewById(R.id.pbLoadingPosts);
        editTextSearch = findViewById(R.id.etSearchPost);

        showLoading(true);

        viewModelsPosts = ViewModelProviders.of(this).get(MainViewModelsPosts.class);
        viewModelsPosts.getPostLiveData().observe(this, postListUpdateObserve);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    Observer<ArrayList<PostResponse>> postListUpdateObserve = new Observer<ArrayList<PostResponse>>() {
        @Override
        public void onChanged(ArrayList<PostResponse> postResponses) {
            postAdapter = new PostAdapter();
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
            postAdapter.setPostAdapter(NewPosts.this, postResponses);
            recyclerView.setLayoutManager(staggeredGridLayoutManager);
            recyclerView.setAdapter(postAdapter);
            showLoading(false);

        }
    };

    public void showLoading(boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
