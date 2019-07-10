package com.lingc.zhihudaily.model;

import com.lingc.zhihudaily.bean.NewMessage;
import com.lingc.zhihudaily.contract.NewContract;
import com.lingc.zhihudaily.util.ApiUtil;
import com.lingc.zhihudaily.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Create by LingC on 2019/7/3 17:58
 */
public class NewModel implements NewContract.INewModel {

    private boolean isFristDo = true;

    @Override
    public List<NewMessage> getNews() {
        String body = HttpUtil.requestUrl(ApiUtil.NEWS_LATEST_API);
        return getNewsFromJson(body);
    }

    @Override
    public List<NewMessage> getNewsFromDate(int date) {
        // 此时date是今天的日期，我想要获取昨天的日期
        // int lastDate = getLastDate(date);
        String url = ApiUtil.NEWS_BEFORE_API + date;
        String body = HttpUtil.requestUrl(url);
        return getNewsFromJson(body);
    }

    /**
     * 此方法无用，留作纪念，这是个坑，以示后人
     * @param date
     * @return
     */
    private int getLastDate(int date) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date1 = null;
        try {
            date1 = simpleDateFormat.parse(date + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date1);
        calendar.add(Calendar.DATE, -1);
        date1 = calendar.getTime();
        int lastDate = Integer.parseInt(simpleDateFormat.format(date1));
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.DAY_OF_MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        int lastDate = Integer.parseInt(String.valueOf(year) + String.valueOf(month) + String.valueOf(day));
        return lastDate;
    }

    private List<NewMessage> getNewsFromJson(String body) {
        List<NewMessage> messageList = new ArrayList<>();
        isFristDo = true;
        try {
            JSONObject jsonObject = new JSONObject(body);
            int date = jsonObject.getInt("date");
            JSONArray jsonArray = jsonObject.getJSONArray("stories");
            // 遍历 JSONArray
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                NewMessage newMessage = new NewMessage();
                // 如果该对象是所获取对象列表中的第一个对象
                if (isFristDo) {
                    newMessage.setIsHeader(0);
                    isFristDo = false;
                } else {
                    newMessage.setIsHeader(1);
                }
                newMessage.setNewId(object.getInt("id"));
                newMessage.setTitle(object.getString("title"));
                newMessage.setImageUrl(object.getString("images").replace("[\"", "").replace("\"]", "")
                .replace("\\", ""));
                newMessage.setDate(date);
                messageList.add(newMessage);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return messageList;
    }
}
