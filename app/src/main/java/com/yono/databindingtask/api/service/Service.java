package com.yono.databindingtask.api.service;

import com.yono.databindingtask.api.response.PostResponse;
import com.yono.databindingtask.api.response.UsersResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("api/users")
    Call<UsersResponse> getUsers();

    @GET("posts")
    Call<ArrayList<PostResponse>> getPosts();
}
