package com.clone.demo.shyp;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface AddShipmentService {

    @POST("/apiSetShipment")
    void addShipment(@Body Shipments shipments, Callback<Object> cb);

}