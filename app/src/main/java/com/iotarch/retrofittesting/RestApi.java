package com.iotarch.retrofittesting;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by JamesHo on 2018/1/30.
 */

public interface RestApi {


    @GET("/api/ticker")
    Call<BitStampBtcUsd> getBtcUsd();

}
