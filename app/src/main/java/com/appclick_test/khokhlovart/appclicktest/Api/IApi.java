package com.appclick_test.khokhlovart.appclicktest.Api;

import com.appclick_test.khokhlovart.appclicktest.Results.AdInfo;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Dom on 22.02.2018.
 */

public interface IApi {
    @Headers({
            //"CONTENT-TYPE: application/json",
            "CONTENT-TYPE: application/x-www-form-urlencoded",
            "Cache-Control: no-cache",
    })

    @FormUrlEncoded
    @POST("adviator/index.php")
    Call<AdInfo> getAd(@Field("id") String id);
    //Call<AdInfo> getAd(@Query("id") String id);
}
