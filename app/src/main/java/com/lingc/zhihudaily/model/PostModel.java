package com.lingc.zhihudaily.model;

import android.util.Log;

import com.lingc.zhihudaily.bean.Post;
import com.lingc.zhihudaily.bean.SectionMessage;
import com.lingc.zhihudaily.contract.NewPostContract;
import com.lingc.zhihudaily.util.ApiUtil;
import com.lingc.zhihudaily.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Create by LingC on 2019/7/4 09:25
 */
public class PostModel implements NewPostContract.IPostModel {

    @Override
    public Post getPost(int id) {
        String url = ApiUtil.POST_API + id;
        String body = HttpUtil.requestUrl(url);
        return parsePost(body);
    }

    private Post parsePost(String body) {
        try {
            JSONObject jsonObject = new JSONObject(body);
            Post post = new Post();
            String postTitle = jsonObject.getString("title");
            String postBody = jsonObject.getString("body");
            String imageUrl = jsonObject.getString("image");
            String imageSource = jsonObject.getString("image_source");
            String shareUrl = jsonObject.getString("share_url");
            String cssUrl = jsonObject.getString("css").replace("[\"", "").replace("\"]", "").replace("\\", "");
            String linkCss = "<link rel=\"stylesheet\" href=\"" + cssUrl + "\" type=\"text/css\">";
            SectionMessage sectionMessage = getSectionMessage(jsonObject);

            String content = "<html><header>" + linkCss + "</header><body>" + postBody
                    + "</body></html>";

            post.setTitle(postTitle);
            post.setBody(content);
            post.setImageUrl(imageUrl);
            post.setImageSource(imageSource);
            post.setShareUrl(shareUrl);
            post.setSectionMessage(sectionMessage);
            return post;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private SectionMessage getSectionMessage(JSONObject jsonObject) {
        SectionMessage sectionMessage = new SectionMessage();
        try {
            JSONObject sectionObject = jsonObject.getJSONObject("section");
            sectionMessage.setId(sectionObject.getInt("id"));
            sectionMessage.setName(sectionObject.getString("name"));
            sectionMessage.setImgurl(sectionObject.getString("thumbnail"));
        } catch (JSONException e) {
            // 文章没有栏目

        }
        return sectionMessage;
    }

    @Override
    public Post getResult() {
        return null;
    }
}
