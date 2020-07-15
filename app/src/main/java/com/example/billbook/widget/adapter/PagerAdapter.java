package com.example.billbook.widget.adapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    private String[] titles={"记支出","记收入"};
    private List<Fragment> fragmentList;

    public PagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentLists) {
        super(fm);
        this.fragmentList=fragmentLists;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return this.fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return this.fragmentList.size();
    }
}
