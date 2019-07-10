package com.lingc.zhihudaily.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lingc.zhihudaily.R;
import com.lingc.zhihudaily.bean.NewMessage;
import com.lingc.zhihudaily.contract.NewContract;
import com.lingc.zhihudaily.presenter.NewPresenter;
import com.lingc.zhihudaily.view.adapter.NewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by LingC on 2019/7/3 17:49
 */
public class NewFragemnt extends Fragment implements NewContract.INewView {

    private FragmentActivity fragmentActivity;

    private NewPresenter newPresenter;

    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    private List<NewMessage> messageList = new ArrayList<>();
    private int listPointer = 0;
    private boolean isLoading = false;

    private int lastOffset;
    private int lastPosition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new, container, false);
        fragmentActivity = getActivity();

        recyclerView = view.findViewById(R.id.new_recycler_view);
        layoutManager = new LinearLayoutManager(fragmentActivity);
        recyclerView.setLayoutManager(layoutManager);
        if (newPresenter == null) {
            newPresenter = new NewPresenter(this);
            newPresenter.getNews();
        } else {
            onResult(new ArrayList<NewMessage>());
        }
        return view;
    }

    @Override
    public void onResult(final List<NewMessage> newMessages) {
        isLoading = false;
        messageList.addAll(newMessages);

        // listPointer 指针总是指向 list 末尾数据
        listPointer = messageList.size() - 1;
        fragmentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final NewAdapter newAdapter = new NewAdapter(messageList);
                recyclerView.setAdapter(newAdapter);
                layoutManager.scrollToPositionWithOffset(lastPosition, lastOffset);
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        // 如果 RecyclerView 停止滑动
                        if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                                layoutManager.findLastVisibleItemPosition() == newAdapter.getItemCount() - 1) {
                            if (isLoading) {
                                return;
                            }
                            // 滑到底啦
                            isLoading = true;
                            Snackbar.make(view, "加载中...", Snackbar.LENGTH_SHORT).show();
                            getLastOfValues();
                            Log.d(fragmentActivity.getLocalClassName(), "Start to loading last group of messages");
                            int date = messageList.get(listPointer).getDate();
                            loadPostFromDate(date);;
                        }
                    }
                });
            }
        });

    }

    private void getLastOfValues() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        View topView = layoutManager.getChildAt(0); //获取可视的第一个view
        try {
            lastOffset = topView.getTop(); //获取与该view的顶部的偏移量
        } catch (NullPointerException e) {
            e.printStackTrace();
            lastOffset = 0;
        }
        lastPosition = layoutManager.getPosition(topView);  //得到该View的数组位置

    }

    private void loadPostFromDate(int date) {
        newPresenter.getLastNews(date);
    }

}
