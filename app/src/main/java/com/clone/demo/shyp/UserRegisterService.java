package com.clone.demo.shyp;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface UserRegisterService {

    @POST("/apiRegister")
    void registerUser(@Body UserRegister userRegister, Callback<Object> cb);
}