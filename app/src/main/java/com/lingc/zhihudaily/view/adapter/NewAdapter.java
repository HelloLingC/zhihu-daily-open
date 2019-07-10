package com.lingc.zhihudaily.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingc.zhihudaily.R;
import com.lingc.zhihudaily.bean.NewMessage;
import com.lingc.zhihudaily.bean.Post;
import com.lingc.zhihudaily.view.activities.PostActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by LingC on 2019/7/3 23:10
 */
public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder> {

    private List<NewMessage> messageList;

    private int lastDate = 19700101;
    private int lastItem = -1;
    private int lastUpDate = 19700101;

    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView titleText;
        private TextView dateText;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.item_new_img);
            titleText = view.findViewById(R.id.item_new_text);
            dateText = view.findViewById(R.id.item_date_text);
        }
    }

    public NewAdapter(List<NewMessage> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_new, viewGroup, false);
        context = viewGroup.getContext();
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int postition = viewHolder.getAdapterPosition();
                NewMessage newMessage = messageList.get(postition);
                Intent intent = new Intent(context, PostActivity.class);
                intent.putExtra("postId", newMessage.getNewId());
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NewMessage newMessage = messageList.get(i);

        // 记录下i的时间，然后上滑时重现
        // 向下滑动，且当前消息头日期与上一个消息头日期不同
//        Log.d("Down", lastItem + "/" + i);
//        if (lastItem < i) {
//            //Log.d("Down", lastItem + "/" + i);
//            viewHolder.dateText.setVisibility(View.GONE);
//            if (newMessage.getDate() != lastDate) {
//                //Log.d(newMessage.getTitle(), newMessage.getDate() + "");
//                viewHolder.dateText.setVisibility(View.VISIBLE);
//                viewHolder.dateText.setText(newMessage.getDate() + "");
//                lastDate = newMessage.getDate();
//                lastItem = i;
//            }
//        }
//        if (lastItem > i) {
//            Log.d("Up", newMessage.getDate() + "/" + lastUpDate);
//            if (lastUpDate != newMessage.getDate() && lastUpDate != 19700101) {
//                Log.d(newMessage.getTitle(), newMessage.getDate() + "");
//                viewHolder.dateText.setVisibility(View.VISIBLE);
//                viewHolder.dateText.setText(newMessage.getDate() + "");
//            }
//            lastUpDate = newMessage.getDate();
//        }
        //Log.d("Loading", newMessage.getTitle() + "//" + newMessage.getDate() + "//" + lastDate);
        if (newMessage.getIsHeader() == 0) {
            viewHolder.dateText.setVisibility(View.VISIBLE);
            String date = newMessage.getDate() + "";
            String showDate = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
            viewHolder.dateText.setText(showDate);
        } else {
            viewHolder.dateText.setVisibility(View.GONE);
        }
        Picasso.with(context).load(newMessage.getImageUrl()).into(viewHolder.imageView);
        viewHolder.titleText.setText(newMessage.getTitle());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
