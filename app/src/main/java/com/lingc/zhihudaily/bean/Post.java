package com.lingc.zhihudaily.bean;

/**
 * Create by LingC on 2019/7/3 17:54
 */
// 正文部分
public class Post {

    private int newId;

    private String title;

    private String body; // Html新闻

    private String shareUrl;

    private String imageUrl;

    private String imageSource; // 图片提供方

    private SectionMessage sectionMessage; // 文章栏目信息

    public int getNewId() {
        return newId;
    }

    public void setNewId(int newId) {
        this.newId = newId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public SectionMessage getSectionMessage() {
        return sectionMessage;
    }

    public void setSectionMessage(SectionMessage sectionMessage) {
        this.sectionMessage = sectionMessage;
    }

}
