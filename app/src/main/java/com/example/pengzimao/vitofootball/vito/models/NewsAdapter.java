package com.example.pengzimao.vitofootball.vito.models;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.pengzimao.vitofootball.R;
import com.example.pengzimao.vitofootball.vito.viewModels.VitoNewsViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by leopeng on 01/02/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<NewsInfo> mNewsInfoList;
    private Context mContext;
    public NewsAdapter(Context context, List<NewsInfo> newsInfoList) {
        mContext = context;
        mNewsInfoList = newsInfoList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titleTextView;
        public ImageView coverImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.newsTitle);
            coverImageView = (ImageView) itemView.findViewById(R.id.coverImage);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                // set read status
                List<NewsInfo> newsList = NewsRepository.getFootballNewsList(mContext);
                if (position < newsList.size()) {
                    NewsInfo info = newsList.get(position);
                    info.hasRead = true;
                    // update data
                    if (mContext instanceof FragmentActivity) {
                        ViewModelProviders.of((FragmentActivity) mContext).get(VitoNewsViewModel.class).getNewsInfoList().setValue(newsList);
                    }
                }

                NewsInfo info = mNewsInfoList.get(position);
                Uri uri = Uri.parse(info.mUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(intent);
            }

        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsInfo newsInfo = mNewsInfoList.get(position);
        if (newsInfo != null) {
            holder.titleTextView.setText(newsInfo.mTitle);
            Picasso.with(mContext).load(newsInfo.mCoverImageUrl).into(holder.coverImageView);

            // set read status
            if (newsInfo.hasRead) {
                holder.titleTextView.setTextColor(mContext.getResources().getColor(R.color.colorTitleRead));
            } else {
                holder.titleTextView.setTextColor(mContext.getResources().getColor(R.color.colorTitleDefault));
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.item_news, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return mNewsInfoList != null ? mNewsInfoList.size() : 0;
    }

    public void setData(List<NewsInfo> list) {
        if (list != null && list.size() > 0) {
            mNewsInfoList.clear();
            mNewsInfoList.addAll(list);
            notifyDataSetChanged();
        }
    }
}
