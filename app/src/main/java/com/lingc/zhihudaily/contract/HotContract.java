package com.lingc.zhihudaily.contract;

import com.lingc.zhihudaily.bean.NewMessage;

import java.util.List;

/**
 * Create by LingC on 2019/7/9 18:49
 */
public interface HotContract {

    /**
     * Create by LingC on 2019/7/6 19:19
     */
    interface IHotModel extends BaseContract.IBaseModel<List<NewMessage>> {

        @Override
        List<NewMessage> getResult();

    }

    /**
     * Create by LingC on 2019/7/6 19:17
     */
    interface IHotView extends BaseContract.IBaseView<List<NewMessage>> {

        @Override
        void onResult(List<NewMessage> messageList);
    }

}
