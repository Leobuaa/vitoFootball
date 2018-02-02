package com.example.pengzimao.vitofootball.vito.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.example.pengzimao.vitofootball.vito.models.NewsInfo;
import com.example.pengzimao.vitofootball.vito.models.NewsRepository;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * Created by leopeng on 01/02/2018.
 */

public class VitoNewsViewModel extends AndroidViewModel {
    private MutableLiveData<List<NewsInfo>> mNewsInfoList;
    private NewsRepository mRepository;

    public VitoNewsViewModel(Application application) {
        super(application);
    }

    public MutableLiveData<List<NewsInfo>> getNewsInfoList() {
        if (mNewsInfoList == null) {
            mNewsInfoList = new MutableLiveData<List<NewsInfo>>();
        }
        return mNewsInfoList;
    }

    public void loadData() {
        // todo get data in background and use postValue
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                mNewsInfoList.postValue(NewsRepository.getFootballNewsList(getApplication()));
            }
        });
    }
}
