package com.example.pengzimao.vitofootball.vito.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.pengzimao.vitofootball.R;
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public Button clickButton;

        public ViewHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.newsTitle);
            clickButton = (Button) itemView.findViewById(R.id.click);

            clickButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NewsInfo.addOneItem(mNewsInfoList);
                    notifyItemChanged(mNewsInfoList.size() - 1);
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsInfo newsInfo = mNewsInfoList.get(position);
        holder.titleTextView.setText(newsInfo.mTitle);
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
