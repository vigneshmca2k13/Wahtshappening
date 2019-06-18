package com.personal.wahtshappening.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.personal.wahtshappening.R;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */


public class SectionsPagerAdapter extends FragmentPagerAdapter {


    private String[] mCategories;
    private final Context mContext;


    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        mCategories = mContext.getResources().getStringArray(R.array.categories);

    }


    @Override
    public Fragment getItem(int position) {
        return PlaceholderFragment.newInstance(mCategories[position]);
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mCategories[position];
    }


    @Override
    public int getCount() {
        return mCategories.length;
    }
}