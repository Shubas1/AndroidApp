package com.clone.demo.shyp;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface ShipmentsService {

    @GET("/apiGetShipments/{id}")
    void shipmentsById(@Path("id") int id , Callback<Object> callback);

}