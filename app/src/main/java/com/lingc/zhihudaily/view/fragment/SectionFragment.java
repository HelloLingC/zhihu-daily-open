package com.lingc.zhihudaily.view.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lingc.zhihudaily.R;
import com.lingc.zhihudaily.bean.SectionMessage;
import com.lingc.zhihudaily.contract.SectionContract;
import com.lingc.zhihudaily.presenter.SectionPresenter;
import com.lingc.zhihudaily.view.adapter.SectionAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Created by LingC in 2019.7.7
 */
public class SectionFragment extends Fragment implements SectionContract.ISectionView<SectionMessage> {

    private Activity activity;

    private SectionPresenter sectionPresenter;

    private RecyclerView recyclerView;

    private List<SectionMessage> sectionMessageList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_section, container, false);
        recyclerView = view.findViewById(R.id.section_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        activity = getActivity();

        if (sectionPresenter == null) {
            sectionPresenter = new SectionPresenter(this);
            sectionPresenter.getResult();
        } else {
            onResult(sectionMessageList);
        }

        return view;
    }

    @Override
    public void onResult(final List<SectionMessage> sectionMessages) {
        sectionMessageList = sectionMessages;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SectionAdapter sectionAdapter = new SectionAdapter(sectionMessages);
                recyclerView.setAdapter(sectionAdapter);
            }
        });

    }
}
