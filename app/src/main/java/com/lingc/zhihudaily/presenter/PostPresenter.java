package com.lingc.zhihudaily.presenter;

import com.lingc.zhihudaily.bean.Post;
import com.lingc.zhihudaily.contract.NewPostContract;
import com.lingc.zhihudaily.model.PostModel;

/**
 * Create by LingC on 2019/7/4 09:24
 */
public class PostPresenter {

    private NewPostContract.IPostView postView;

    private NewPostContract.IPostModel postModel;

    public PostPresenter(NewPostContract.IPostView postView) {
        this.postView = postView;
        postModel = new PostModel();
    }

    public void getPost(final int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Post post = postModel.getPost(id);
                if (post == null) {
                    return;
                }
                postView.onResult(post);
            }
        }).start();
    }

}
