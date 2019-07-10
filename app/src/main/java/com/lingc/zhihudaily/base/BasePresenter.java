package com.lingc.zhihudaily.base;

/**
 * Create by LingC on 2019/7/8 21:12
 */
public class BasePresenter<V extends BaseView, M extends BaseModel> {

    private V view;
    private M model;


    public V getView() {
        return view;
    }

    public M getModel() {
        return model;
    }
}
