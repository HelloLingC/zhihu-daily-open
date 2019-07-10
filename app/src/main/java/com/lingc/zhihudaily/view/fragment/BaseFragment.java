package com.lingc.zhihudaily.view.fragment;

import com.lingc.zhihudaily.base.BaseModel;
import com.lingc.zhihudaily.base.BasePresenter;
import com.lingc.zhihudaily.base.BaseView;

/**
 * Create by LingC on 2019/7/8 23:01
 */
public class BaseFragment<V extends BaseView, M extends BaseModel, P extends BasePresenter> {
    protected BasePresenter<V, M> presenter;



}
