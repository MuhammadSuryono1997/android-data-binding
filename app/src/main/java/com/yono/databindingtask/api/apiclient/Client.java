package com.yono.databindingtask.api.apiclient;

import android.util.Log;

import com.yono.databindingtask.api.ApiHelper;
import com.yono.databindingtask.api.response.UsersResponse;

public class Client {
    public <T> T Client(Class<T> service, String basUrl){
        return ApiHelper.client(basUrl).create(service);
    }

    public <T> UsersResponse getUsers(Class<UsersResponse> service, String baseUrl){
        return Client(service, baseUrl);
    }

}
