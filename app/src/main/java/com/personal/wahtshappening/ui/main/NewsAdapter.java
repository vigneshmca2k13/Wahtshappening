package com.personal.wahtshappening.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.personal.wahtshappening.R;
import com.personal.wahtshappening.ui.main.jdo.NewsJDO;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by ${Vignesh} on 2019-06-18.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<NewsJDO> newsJDOList;
    private Context mContext;

    public NewsAdapter(Context context, List<NewsJDO> feedItemList) {
        this.newsJDOList = feedItemList;
        this.mContext = context;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.newsitem, null);
        NewsViewHolder viewHolder = new NewsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder customViewHolder, int i) {
        NewsJDO each = newsJDOList.get(i);

        //Render image using Picasso library
        if (!TextUtils.isEmpty(each.getImageUrl())) {
            Picasso.get().load(each.getImageUrl()).into(customViewHolder.imageView);
        }

        //Setting text view title
        customViewHolder.title.setText(Html.fromHtml(each.getTitle()));
        customViewHolder.desc.setText(Html.fromHtml(each.getDescription()));

        int lTimeInMins = 0, lTimeInSec =0;
        try {
            lTimeInMins = (Math.round(((new Date().getTime() - each.getPublishedAt().getTime() )/60000)));
            lTimeInSec  = (Math.round( ((new Date().getTime() - each.getPublishedAt().getTime()) /1000)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(lTimeInMins >= 1440){
            customViewHolder.time.setText((Math.round(lTimeInMins/1440) + " days ago"));
        }else if(lTimeInMins > 60){
            int lMin =  lTimeInMins % 60;
            customViewHolder.time.setText((Math.round(lTimeInMins/60)) + " hrs " +((lMin > 0) ? (lMin + " mins") : ""));
        }else if(lTimeInMins > 0){
            customViewHolder.time.setText(lTimeInMins + " min");
        } else if(lTimeInSec < 30){
            customViewHolder.time.setText("Just now");
        } else {
            customViewHolder.time.setText("few seconds ago");
        }

    }

    @Override
    public int getItemCount() {
        return (null != newsJDOList ? newsJDOList.size() : 0);
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView title, desc, time;

        public NewsViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.title = (TextView) view.findViewById(R.id.title);
            this.desc = (TextView) view.findViewById(R.id.description);
            this.time = (TextView) view.findViewById(R.id.timeTxt);
        }
    }
}
