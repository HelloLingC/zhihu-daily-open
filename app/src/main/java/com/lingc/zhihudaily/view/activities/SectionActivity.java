package com.lingc.zhihudaily.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.lingc.zhihudaily.R;
import com.lingc.zhihudaily.bean.NewMessage;
import com.lingc.zhihudaily.contract.SectionContract;
import com.lingc.zhihudaily.presenter.SectionPresenter;
import com.lingc.zhihudaily.view.adapter.NewAdapter;

import java.util.List;

public class SectionActivity extends AppCompatActivity implements SectionContract.ISectionView<NewMessage> {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);
        Toolbar toolbar = findViewById(R.id.section_toolbar);
        recyclerView = findViewById(R.id.section_recyclerview);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");
        toolbar.setTitle(name);
        SectionPresenter sectionPresenter = new SectionPresenter(this);
        sectionPresenter.getNews(id);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onResult(final List<NewMessage> sectionMessages) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                NewAdapter newAdapter = new NewAdapter(sectionMessages);
                recyclerView.setAdapter(newAdapter);
            }
        });

    }
}
