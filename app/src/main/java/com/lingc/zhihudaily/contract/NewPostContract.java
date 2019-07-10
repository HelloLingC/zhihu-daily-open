package com.lingc.zhihudaily.contract;

import com.lingc.zhihudaily.bean.Post;

/**
 * Create by LingC on 2019/7/8 21:11
 */
public interface NewPostContract {

    /**
     * Create by LingC on 2019/7/4 09:25
     */
    interface IPostModel extends BaseContract.IBaseModel<Post> {

        Post getPost(int id);

        @Override
        Post getResult();
    }

    /**
     * Create by LingC on 2019/7/4 09:21
     */
    interface IPostView extends BaseContract.IBaseView<Post> {

        @Override
        void onResult(Post post);

    }

}
