package com.example.pengzimao.vitofootball.vito.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pengzimao.vitofootball.R;
import com.example.pengzimao.vitofootball.vito.models.NewsAdapter;
import com.example.pengzimao.vitofootball.vito.models.NewsInfo;
import com.example.pengzimao.vitofootball.vito.viewModels.VitoNewsViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leopeng on 01/02/2018.
 */

public class VitoRecycleViewFragment extends Fragment {
    private RecyclerView mRecycleView;
    private VitoNewsViewModel mViewModel;
    private NewsAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recyclerview_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // init recyclerView and adapter
        mRecycleView = getActivity().findViewById(R.id.vito_recycler_view);
        mAdapter = new NewsAdapter(getActivity(), new ArrayList<NewsInfo>());
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));

        // init view model and load data
        mViewModel = ViewModelProviders.of(getActivity()).get(VitoNewsViewModel.class);
        mViewModel.getNewsInfoList().observe(this, mObserver);
        mViewModel.loadData();
    }

    private Observer<List<NewsInfo>> mObserver = new Observer<List<NewsInfo>>() {
        @Override
        public void onChanged(@Nullable List<NewsInfo> newsInfoList) {
            mAdapter.setData(newsInfoList);
        }
    };
}
