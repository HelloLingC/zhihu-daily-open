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
import com.lingc.zhihudaily.bean.NewMessage;
import com.lingc.zhihudaily.contract.HotContract;
import com.lingc.zhihudaily.presenter.HotPresenter;
import com.lingc.zhihudaily.view.adapter.HotAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment implements HotContract.IHotView {

    private Activity activity;

    private HotPresenter hotPresenter;

    RecyclerView recyclerView;

    private List<NewMessage> newMessageList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        recyclerView = view.findViewById(R.id.hot_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        activity = getActivity();

        recyclerView.setLayoutManager(layoutManager);
        if (hotPresenter == null) {
            hotPresenter = new HotPresenter(this);
            hotPresenter.getHotNews();
        } else {
            onResult(newMessageList);
        }
        return view;
    }

    @Override
    public void onResult(final List<NewMessage> messageList) {
        newMessageList = messageList;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                HotAdapter hotAdapter = new HotAdapter(messageList);
                recyclerView.setAdapter(hotAdapter);
            }
        });
    }
}
