package com.clone.demo.shyp;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface UserAuthService {

    @POST("/apiLogin")
    void authUser(@Body User user, Callback<Object> cb);
}