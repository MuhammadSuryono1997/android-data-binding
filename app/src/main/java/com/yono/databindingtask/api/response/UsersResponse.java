package com.yono.databindingtask.api.response;

import com.google.gson.annotations.SerializedName;
import com.yono.databindingtask.api.models.Users;

import java.util.List;

public class UsersResponse {
    @SerializedName("data")
    List<Users> data;


    public List<Users> getData() {
        return data;
    }
}
