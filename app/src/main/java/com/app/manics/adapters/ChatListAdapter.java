package com.app.manics.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.manics.R;
import com.app.manics.models.Chat;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChatListAdapter extends BaseAdapter {

    private Context context;
    private List<Chat> items;

    public ChatListAdapter(Context context, List<Chat> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Chat currentChat = (Chat) getItem(position);
        viewHolder.chatTitle.setText(currentChat.getTitle());

        //todo define the best size
        if (currentChat.isTitleImage()) {
            Picasso.with(context)
                    .load(currentChat.getImage200())
                    .into(viewHolder.chatImage);
        } else {
            //todo magic behavior if not set image
           viewHolder.chatImage.setImageResource(R.drawable.material);
        }

        return convertView;
    }


    private class ViewHolder {
        TextView chatTitle;
        ImageView chatImage;

        public ViewHolder(View view) {
            chatTitle = (TextView) view.findViewById(R.id.chatTitle);
            chatImage = (ImageView) view.findViewById(R.id.chatImage);
        }
    }
}
