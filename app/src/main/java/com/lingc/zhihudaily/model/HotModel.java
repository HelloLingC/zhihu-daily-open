package com.lingc.zhihudaily.model;

import com.lingc.zhihudaily.bean.NewMessage;
import com.lingc.zhihudaily.contract.HotContract;
import com.lingc.zhihudaily.util.ApiUtil;
import com.lingc.zhihudaily.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by LingC on 2019/7/6 19:22
 */
public class HotModel implements HotContract.IHotModel {

    @Override
    public List<NewMessage> getResult() {
        String body = HttpUtil.requestUrl(ApiUtil.NEWS_HOT_API);
        return getResultFromJson(body);
    }

    private List<NewMessage> getResultFromJson(String body) {
        List<NewMessage> newMessageList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONObject(body).getJSONArray("recent");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                NewMessage newMessage = new NewMessage();
                newMessage.setTitle(jsonObject.getString("title"));
                newMessage.setNewId(jsonObject.getInt("news_id"));
                newMessage.setImageUrl(jsonObject.getString("thumbnail"));
                newMessageList.add(newMessage);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newMessageList;
    }

}
