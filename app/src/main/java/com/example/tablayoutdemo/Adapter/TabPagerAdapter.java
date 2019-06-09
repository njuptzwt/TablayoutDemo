package com.example.tablayoutdemo.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * tabPagerAdapter中的pager元素是Fragment
 *
 * ViewPager和RecyclerView是一样性质的组件
 * 都需要使用Adapter去做适配来使用
 * Recycler还需要使用ViewHolder来做
 */
public class TabPagerAdapter extends FragmentPagerAdapter {

    String title[] = {"热门活动", "好优惠", "活动精选"};
    private List<Fragment> tabFragments;

    public void setTabFragments(List<Fragment> tabFragments) {
        this.tabFragments = tabFragments;
    }

    // 通过绑定适配器，FragmentManager管理Fragment,托管到Activity中
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return tabFragments.get(i);
    }

    @Override
    public int getCount() {
        return tabFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
