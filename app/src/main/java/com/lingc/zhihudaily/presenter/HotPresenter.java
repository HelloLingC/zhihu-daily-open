package com.lingc.zhihudaily.presenter;

import com.lingc.zhihudaily.bean.NewMessage;
import com.lingc.zhihudaily.contract.HotContract;
import com.lingc.zhihudaily.model.HotModel;

import java.util.List;

/**
 * Create by LingC on 2019/7/6 19:06
 */
public class HotPresenter {

    private HotContract.IHotView hotView;
    private HotContract.IHotModel hotModel;

    public HotPresenter(HotContract.IHotView hotView) {
        this.hotView = hotView;
        hotModel = new HotModel();
    }

    public void getHotNews() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<NewMessage> newMessageList = hotModel.getResult();
                hotView.onResult(newMessageList);
            }
        }).start();
    }

}
