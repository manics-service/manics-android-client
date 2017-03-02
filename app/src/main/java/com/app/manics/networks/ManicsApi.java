package com.app.manics.networks;


import com.app.manics.models.AuthInfo;
import com.app.manics.models.Chat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ManicsApi {

    //todo change to POST
    @GET("data/chats.json")
    Call<List<Chat>> getChats(@Query("token") String token);

    @Headers("Content-type: application/json")
    @POST("authentication")
    Call<AuthInfo> createUser(@Body AuthInfo authInfo);
}
