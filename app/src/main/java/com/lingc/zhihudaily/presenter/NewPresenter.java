package com.lingc.zhihudaily.presenter;

import com.lingc.zhihudaily.base.BasePresenter;
import com.lingc.zhihudaily.bean.NewMessage;
import com.lingc.zhihudaily.contract.NewContract;
import com.lingc.zhihudaily.model.NewModel;

import java.util.List;

/**
 * Create by LingC on 2019/7/3 17:53
 */
public class NewPresenter extends BasePresenter {
    private NewContract.INewView newView;
    private NewContract.INewModel newModel;

    public NewPresenter(NewContract.INewView newView) {
        this.newView = newView;
        newModel = new NewModel();
    }

    public void getNews() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<NewMessage> messageList = newModel.getNews();
                newView.onResult(messageList);
            }
        }).start();
    }

    public void getLastNews(final int date) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<NewMessage> lastMessageList = newModel.getNewsFromDate(date);
                newView.onResult(lastMessageList);
            }
        }).start();
    }

}
