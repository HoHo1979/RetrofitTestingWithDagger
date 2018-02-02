package com.iotarch.retrofittesting;

import com.iotarch.retrofittesting.entity.BitStampAuth;
import com.iotarch.retrofittesting.entity.BitStampBalance;
import com.iotarch.retrofittesting.entity.BitStampBtcUsd;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by JamesHo on 2018/1/30.
 */

public interface RestApi {


    @GET("ticker")
    Call<BitStampBtcUsd> getBtcUsd();

    @FormUrlEncoded
    @POST("v2/balance/")
    Call<BitStampBalance> getBalance(//@Path("currency_pair") String currency_pair,
                                     @Field(value = "key",encoded = true) String key,
                                     @Field(value = "signature", encoded = true) String signature,
                                     @Field(value = "nonce",encoded = true) Integer nonce
                                    );

}
