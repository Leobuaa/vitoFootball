package com.example.pengzimao.vitofootball.vito.models;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by leopeng on 01/02/2018.
 */

public class NewsRepository {

    private static int lastNewsId = 0;
    private static ArrayList<NewsInfo> newsListCache;

    public static ArrayList<NewsInfo> createNewsList(int numContacts) {
        ArrayList<NewsInfo> newsList = new ArrayList<NewsInfo>();

        for (int i = 1; i <= numContacts; i++) {
            String title;
            if (i % 2 == 0) {
                title = "Long Long Long Long Long Long Long Long Long Long Long Long Long Long Long Long Long Long Long Long Long Long ";
            } else {
                title = "NewsInfo title: " + ++lastNewsId;
            }
            newsList.add(new NewsInfo(title, "NewsInfo url: " + lastNewsId));
        }

        return newsList;
    }

    public static ArrayList<NewsInfo> getFootballNewsList(Context context) {
        if (newsListCache != null && newsListCache.size() > 10) {
            return newsListCache;
        }
        newsListCache = createFootballNewsList(context);
        return newsListCache;
    }

    private static ArrayList<NewsInfo> createFootballNewsList(Context context) {
        ArrayList<NewsInfo> arrayList = new ArrayList<NewsInfo>();
        try {
            JSONArray jsonArray = new JSONArray(loadJSONFromAssets(context));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                if (item != null) {
                    NewsInfo info = new NewsInfo();
                    info.mTitle = item.getString("title");
                    info.mUrl = item.getString("link");
                    info.mCoverImageUrl = item.getString("img_url");
                    arrayList.add(info);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    private static String loadJSONFromAssets(Context context) {
        String json = null;

        try {
            InputStream inputStream = context.getAssets().open("football.json");
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }
}
