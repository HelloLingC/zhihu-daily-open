package com.lingc.zhihudaily.contract;

import com.lingc.zhihudaily.bean.NewMessage;
import com.lingc.zhihudaily.bean.SectionMessage;

import java.util.List;

/**
 * Create by LingC on 2019/7/9 18:51
 */
public interface SectionContract {

    /**
     * Create by LingC on 2019/7/7 07:15
     */
    interface ISectionModel extends BaseContract.IBaseModel<List<SectionMessage>> {

        @Override
        List<SectionMessage> getResult();

        List<NewMessage> getNews(int id);
    }

    /**
     * Create by LingC on 2019/7/7 06:56
     */
    interface ISectionView<T> extends BaseContract.IBaseView<List<T>> {

        @Override
        void onResult(List<T> sectionMessages);

    }

}
