package com.app.manics.networks;


import com.app.manics.models.Chat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ManicsApi {

    //todo change to POST
    @GET("chats.json")
    Call<List<Chat>> getChats(@Query("token") String token);
}
