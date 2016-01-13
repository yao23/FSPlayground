package com.clouds.liyao.fsplayground.API;


import com.clouds.liyao.fsplayground.Model.GitModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by liyao on 1/12/16.
 */
public interface GitAPI {
    @GET("/users/{username}")
    Call<GitModel> getUser(@Path("username") String username);
}
