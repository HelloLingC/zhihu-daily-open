package com.lingc.zhihudaily.model;

import com.lingc.zhihudaily.bean.NewMessage;
import com.lingc.zhihudaily.bean.SectionMessage;
import com.lingc.zhihudaily.contract.SectionContract;
import com.lingc.zhihudaily.util.ApiUtil;
import com.lingc.zhihudaily.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by LingC on 2019/7/7 07:13
 */
public class SectionModel implements SectionContract.ISectionModel {

    @Override
    public List<SectionMessage> getResult() {
        String body = HttpUtil.requestUrl(ApiUtil.SECTION_ALL_API);
        return getResultFromJson(body);
    }

    @Override
    public List<NewMessage> getNews(int id) {
        String body = HttpUtil.requestUrl(ApiUtil.SECTION_NEWS_API + id);
        return getNewsFromJson(body);
    }

    private List<SectionMessage> getResultFromJson(String body) {
        List<SectionMessage> sectionMessageList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONObject(body).getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                SectionMessage sectionMessage = new SectionMessage();
                sectionMessage.setId(jsonObject.getInt("id"));
                sectionMessage.setName(jsonObject.getString("name"));
                sectionMessage.setDescription(jsonObject.getString("description"));
                sectionMessage.setImgurl(jsonObject.getString("thumbnail"));
                sectionMessageList.add(sectionMessage);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sectionMessageList;
    }

    private List<NewMessage> getNewsFromJson(String body) {
        List<NewMessage> newMessageList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONObject(body).getJSONArray("stories");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                NewMessage newMessage = new NewMessage();
                newMessage.setNewId(jsonObject.getInt("id"));
                newMessage.setImageUrl(jsonObject.getString("images").replace("[\"", "").replace("\"]", "").replace("\\", ""));
                newMessage.setTitle(jsonObject.getString("title"));
                newMessage.setDate(jsonObject.getInt("date"));
                newMessageList.add(newMessage);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newMessageList;
    }

}
