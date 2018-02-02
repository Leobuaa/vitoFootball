package com.example.pengzimao.vitofootball.vito.viewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;

import com.example.pengzimao.vitofootball.vito.models.NewsInfo;
import com.example.pengzimao.vitofootball.vito.models.NewsRepository;

import java.util.List;

/**
 * Created by leopeng on 01/02/2018.
 */

public class VitoNewsViewModel extends ViewModel {
    private MutableLiveData<List<NewsInfo>> mNewsInfoList;
    private NewsRepository mRepository;

    public MutableLiveData<List<NewsInfo>> getNewsInfoList() {
        if (mNewsInfoList == null) {
            mNewsInfoList = new MutableLiveData<List<NewsInfo>>();
        }
        return mNewsInfoList;
    }

    public void loadData() {
        // todo get data in background and use postValue
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mNewsInfoList.setValue(NewsInfo.createNewsList(20));
            }
        }, 2000);
    }
}
