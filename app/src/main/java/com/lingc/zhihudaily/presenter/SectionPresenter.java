package com.lingc.zhihudaily.presenter;

import com.lingc.zhihudaily.contract.SectionContract;
import com.lingc.zhihudaily.model.SectionModel;

/**
 * Create by LingC on 2019/7/7 16:12
 */
public class SectionPresenter {
    private SectionContract.ISectionView sectionView;
    private SectionContract.ISectionModel sectionModel;

    public SectionPresenter(SectionContract.ISectionView sectionView) {
        this.sectionView = sectionView;
        sectionModel = new SectionModel();
    }

    public void getResult() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                sectionView.onResult(sectionModel.getResult());
            }
        }).start();
    }

    public void getNews(final int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                sectionView.onResult(sectionModel.getNews(id));
            }
        }).start();
    }

}
