package com.app.manics.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.app.manics.R;
import com.app.manics.adapters.ChatListAdapter;
import com.app.manics.models.Chat;

import java.util.ArrayList;

public class ChatsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView itemsListView  = (ListView)findViewById(R.id.list_view_items);

        ChatListAdapter adapter = new ChatListAdapter(this, new ArrayList<Chat>());
        itemsListView.setAdapter(adapter);
    }
}
