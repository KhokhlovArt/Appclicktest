package com.appclick_test.khokhlovart.appclicktest.Api;

import com.appclick_test.khokhlovart.appclicktest.Results.AdInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Dom on 22.02.2018.
 */

public interface IApi {
    @Headers({
            "CONTENT-TYPE: application/x-www-form-urlencoded",
            "Cache-Control: no-cache",
    })

    @FormUrlEncoded
    @POST("adviator/index.php")
    Call<AdInfo> getAd(@Field("id") String id);
}
