package com.app.manics.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.app.manics.R;
import com.app.manics.adapters.ChatListAdapter;
import com.app.manics.models.Chat;
import com.app.manics.networks.ManicsApi;
import com.app.manics.networks.ManicsApiClient;
import com.vk.sdk.VKAccessToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatsActivity extends AppCompatActivity {

    private static final String TAG = ChatsActivity.class.getSimpleName();

    private List<Chat> chats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView itemsListView  = (ListView)findViewById(R.id.list_view_items);

        ManicsApi manicsApi = ManicsApiClient.getClient().create(ManicsApi.class);
        Call<List<Chat>> call = manicsApi.getChats(VKAccessToken.currentToken().accessToken);

        call.enqueue(new Callback<List<Chat>>() {
            @Override
            public void onResponse(Call<List<Chat>>call, Response<List<Chat>> response) {
                //todo add progress
                chats = response.body();
                itemsListView.setAdapter(new ChatListAdapter(getApplicationContext(), chats));
            }

            @Override
            public void onFailure(Call<List<Chat>>call, Throwable t) {
                //todo add toast
            }
        });
    }
}
