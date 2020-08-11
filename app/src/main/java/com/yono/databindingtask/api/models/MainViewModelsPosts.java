package com.yono.databindingtask.api.models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yono.databindingtask.api.ApiHelper;
import com.yono.databindingtask.api.apiclient.Client;
import com.yono.databindingtask.api.response.PostResponse;
import com.yono.databindingtask.api.service.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModelsPosts extends ViewModel {

    MutableLiveData<ArrayList<PostResponse>> postLiveData;
    ArrayList<PostResponse> postResponseArrayList;

    public MainViewModelsPosts(){
        postLiveData = new MutableLiveData<>();

        init();
    }

    public MutableLiveData<ArrayList<PostResponse>> getPostLiveData(){
        return postLiveData;
    }

    private void init() {
        populateList();
        postLiveData.setValue(postResponseArrayList);
    }

    private void populateList() {
        Client client = new Client();
        Service service = client.Client(Service.class, ApiHelper.BASE_URL_NEW);

        service.getPosts().enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                postResponseArrayList = new ArrayList<>();
                postResponseArrayList.add(response.body());
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
