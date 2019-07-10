package com.lingc.zhihudaily.contract;

import com.lingc.zhihudaily.bean.NewMessage;

import java.util.List;

/**
 * Create by LingC on 2019/7/9 18:54
 */
public interface NewContract {

    /**
     * Create by LingC on 2019/7/3 17:57
     */
    interface INewModel {

        List<NewMessage> getNews();

        List<NewMessage> getNewsFromDate(int date);

    }


    /**
     * Create by LingC on 2019/7/3 17:52
     */
    interface INewView extends BaseContract.IBaseView<List<NewMessage>> {

        @Override
        void onResult(List<NewMessage> messageList);

    }


}
