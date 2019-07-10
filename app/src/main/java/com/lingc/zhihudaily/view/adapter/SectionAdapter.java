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
import com.lingc.zhihudaily.bean.SectionMessage;
import com.lingc.zhihudaily.view.activities.SectionActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Create by LingC on 2019/7/8 14:05
 */
public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {
    private List<SectionMessage> sectionMessageList;

    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView titleText;
        private TextView descText;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.item_section_img);
            titleText = view.findViewById(R.id.item_sectiontitle_text);
            descText = view.findViewById(R.id.item_sectiondesc_text);
        }

    }

    public SectionAdapter(List<SectionMessage> sectionMessageList) {
        this.sectionMessageList = sectionMessageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_section, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionMessage sectionMessage = sectionMessageList.get(viewHolder.getAdapterPosition());
                Intent intent = new Intent(context, SectionActivity.class);
                intent.putExtra("id", sectionMessage.getId());
                intent.putExtra("name", sectionMessage.getName());
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SectionMessage sectionMessage = sectionMessageList.get(i);
        Picasso.with(context).load(sectionMessage.getImgurl()).into(viewHolder.imageView);
        viewHolder.titleText.setText(sectionMessage.getName());
        viewHolder.descText.setText(sectionMessage.getDescription());
    }

    @Override
    public int getItemCount() {
        return sectionMessageList.size();
    }
}
