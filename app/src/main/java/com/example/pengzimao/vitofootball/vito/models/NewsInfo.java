package com.example.pengzimao.vitofootball.vito.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leopeng on 01/02/2018.
 */

public class NewsInfo {
    public String mTitle;
    public String mUrl;

    public NewsInfo() {

    }

    public NewsInfo(String title, String url) {
        this.mTitle = title;
        this.mUrl = url;
    }

    private static int lastNewsId = 0;
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

    public static void addOneItem(List<NewsInfo> list) {
        if (list != null) {
            list.add(new NewsInfo("NewsInfo title: " + ++lastNewsId, "NewsInfo url: " + lastNewsId));
        }
    }
}
