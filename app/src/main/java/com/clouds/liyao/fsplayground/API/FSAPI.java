package com.clouds.liyao.fsplayground.API;

import com.clouds.liyao.fsplayground.Model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by liyao on 1/12/16.
 */
public interface FSAPI {
    @FormUrlEncoded
    @POST("user/login")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password, @Field("key") String key);
}
