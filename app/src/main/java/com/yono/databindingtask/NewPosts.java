package com.yono.databindingtask;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.yono.databindingtask.api.models.MainViewModelsPosts;
import com.yono.databindingtask.api.response.PostResponse;

import java.util.ArrayList;

public class NewPosts extends AppCompatActivity implements LifecycleOwner {

    MainViewModelsPosts viewModelsPosts;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_posts);

        viewModelsPosts = ViewModelProviders.of(this).get(MainViewModelsPosts.class);
        viewModelsPosts.getPostLiveData().observe(this, postListUpdateObserve);
    }

    Observer<ArrayList<PostResponse>> postListUpdateObserve = new Observer<ArrayList<PostResponse>>() {
        @Override
        public void onChanged(ArrayList<PostResponse> postResponses) {
            Log.d("postResponse", "posts "+postResponses);
        }
    };
}
