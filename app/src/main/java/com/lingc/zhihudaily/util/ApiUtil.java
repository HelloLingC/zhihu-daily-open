package com.lingc.zhihudaily.util;

/**
 * Create by LingC on 2019/7/5 23:30
 */
public class ApiUtil {

    /* 获取最火文章 */
    public final static String NEWS_HOT_API = "https://news-at.zhihu.com/api/3/news/hot";
    /* 获取最新文章 */
    public final static String NEWS_LATEST_API = "https://news-at.zhihu.com/api/4/news/latest";
    /* 传入时间获取在此之前的文章 */
    public final static String NEWS_BEFORE_API = "https://news-at.zhihu.com/api/4/news/before/";
    /* 传入文章ID获取文章信息 */
    public final static String POST_API = "https://news-at.zhihu.com/api/4/news/";
    /* 获取全部栏目 */
    public final static String SECTION_ALL_API = "https://news-at.zhihu.com/api/3/sections";
    /* 传入栏目ID获取栏目信息 */
    public final static String SECTION_NEWS_API = "https://news-at.zhihu.com/api/3/section/";

}
