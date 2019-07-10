package com.lingc.zhihudaily.bean;

/**
 * Create by LingC on 2019/7/7 07:16
 */
public class SectionMessage {

    private int id;

    private String name;

    private String description;

    private String imgurl;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getImgurl() {
        return imgurl;
    }
}
