package com.lingc.zhihudaily.contract;

/**
 * Create by LingC on 2019/7/9 18:56
 */
public interface BaseContract {

    /**
     * Create by LingC on 2019/7/6 19:20
     */
    interface IBaseModel<T> {

        T getResult();

    }

    /**
     * Create by LingC on 2019/7/6 19:11
     */
    interface IBaseView <T> {

        void onResult(T t);

    }

}
