package com.generallibrary.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by feng on 2016/10/12.
 */
public class CommonPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentsList;
    private List<String> mTitleList;

    public CommonPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public CommonPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titleList) {
        super(fm);
        this.fragmentsList = fragments;
        this.mTitleList = titleList;
    }

    @Override
    public int getCount() {
        return fragmentsList == null ? 0 : fragmentsList.size();
    }

    @Override
    public Fragment getItem(int arg0) {
        return (fragmentsList == null || fragmentsList.size() == 0) ? null : fragmentsList.get(arg0);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (mTitleList.size() > position) ? mTitleList.get(position) : "";
    }
}
