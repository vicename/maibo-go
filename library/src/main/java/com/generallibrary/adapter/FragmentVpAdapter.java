package com.generallibrary.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by FengZhongxia on 2016/10/17.
 */

public class FragmentVpAdapter extends FragmentPagerAdapter {

    //    这个是viewpager的填充视图
    private List<Fragment> mViews;
    //    这个是table导航条里面的内容填充
    private List<String> mTabstrs;

    public FragmentVpAdapter(FragmentManager fm, List<String> title, List<Fragment> views){
        super(fm);
        this.mViews = views;
        this.mTabstrs = title;
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mViews.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 这个是和tablelayout相关的
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabstrs.get(position);
    }
}
