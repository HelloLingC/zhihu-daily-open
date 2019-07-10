package com.lingc.zhihudaily.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

import java.util.List;

/**
 * Create by LingC on 2019/7/6 07:09
 */
public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> {
    private List<NewMessage> newMessageList;

    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.item_new_img);
            textView = view.findViewById(R.id.item_new_text);
        }

    }

    public HotAdapter(List<NewMessage> newMessageList) {
        this.newMessageList = newMessageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_new, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewMessage newMessage = newMessageList.get(viewHolder.getAdapterPosition());
                Intent intent = new Intent(context, PostActivity.class);
                intent.putExtra("postId", newMessage.getNewId());
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NewMessage newMessage = newMessageList.get(i);
        Picasso.with(context).load(newMessage.getImageUrl()).into(viewHolder.imageView);
        viewHolder.textView.setText(newMessage.getTitle());
    }

    @Override
    public int getItemCount() {
        return newMessageList.size();
    }
}
