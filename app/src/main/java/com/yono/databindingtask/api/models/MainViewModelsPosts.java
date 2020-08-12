package com.yono.databindingtask.api.models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.yono.databindingtask.api.ApiHelper;
import com.yono.databindingtask.api.apiclient.Client;
import com.yono.databindingtask.api.response.PostResponse;
import com.yono.databindingtask.api.service.Service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModelsPosts extends ViewModel {

    MutableLiveData<ArrayList<PostResponse>> postLiveData;
    ArrayList<PostResponse> postResponseArrayList;

    public MainViewModelsPosts(){
        postLiveData = new MutableLiveData<>();

        Log.d("Data Masuk", "Init");
        init();
    }

    public MutableLiveData<ArrayList<PostResponse>> getPostLiveData(){

        return postLiveData;
    }

    private void init() {
        populateList();
    }

    private void populateList() {
        Client client = new Client();
        Service service = client.Client(Service.class, ApiHelper.BASE_URL_NEW);

        service.getPosts().enqueue(new Callback<ArrayList<PostResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<PostResponse>> call, Response<ArrayList<PostResponse>> response) {
                postLiveData.setValue(response.body());

                String sg = new Gson().toJson(response.body());
                Log.d("Data Posts", sg);
            }

            @Override
            public void onFailure(Call<ArrayList<PostResponse>> call, Throwable t) {

            }
        });
    }
}
