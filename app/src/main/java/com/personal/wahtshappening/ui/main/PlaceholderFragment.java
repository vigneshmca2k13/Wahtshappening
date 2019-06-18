package com.personal.wahtshappening.ui.main;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.widget.Toast;

import com.personal.wahtshappening.R;
import com.personal.wahtshappening.ui.main.jdo.NewsJDO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by ${Vignesh} on 2019-06-18.
 */
public class PlaceholderFragment extends Fragment {


    private static final String CATEGORY = "selectedCategory";

    private SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView mRecyclerView;
    Context mContext;
    List<NewsJDO>  lNewsList = new ArrayList<>();
    String category;

    public static PlaceholderFragment newInstance(String category) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CATEGORY, category);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(CATEGORY);
        }

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mContext = getActivity();
        mSwipeRefreshLayout         = (SwipeRefreshLayout) root.findViewById(R.id.swipeRefreshLayout);
        mRecyclerView               = (RecyclerView) root.findViewById(R.id.itemsRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(!Helper.isOnline(mContext)) {
                    mSwipeRefreshLayout.setRefreshing(false);
                    return;
                }

                new Task(category).execute();
            }
        });


        new Task(category).execute();
        return root;
    }

    private class Task extends AsyncTask<String, Void, List<NewsJDO>> {

        String category;
        String lResponse = null;
        Helper helper;

        public Task(String category) {
            this.category = category;
            helper = new Helper();
        }


        @Override
        protected List<NewsJDO> doInBackground(String... params) {
            try {
                return helper.parseJSONData(helper.fetchNews(category));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<NewsJDO> list) {
            super.onPostExecute(list);
            if(list == null) {
                mSwipeRefreshLayout.setRefreshing(false);
                Toast.makeText(mContext,"Please try again later...",Toast.LENGTH_SHORT).show();
                return;
            }

            lNewsList.clear();
            lNewsList.addAll(list);
            setAdpater(lNewsList);
        }
    }



    private void setAdpater(List<NewsJDO>  list){
        Log.d(getClass().getName() + "setAdpater ", list.toString());
        mRecyclerView.setAdapter(new NewsAdapter(mContext, list ));
    }
}