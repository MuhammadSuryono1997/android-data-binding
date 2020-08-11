package com.yono.databindingtask.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {
    public static final String BASE_URL_OLD = "https://reqres.in/";
    public static final String BASE_URL_NEW = "http://jsonplaceholder.typicode.com/";

    public static Retrofit client(String base_url){
        Retrofit client = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return client;
    }
}
