package com.nguyenhuong.worldcup.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.nguyenhuong.worldcup.fragment.PlayerFragment;
import com.nguyenhuong.worldcup.fragment.ScheduleFragment;

public class FootballPagerAdapter extends FragmentPagerAdapter {
    private String[] titles = new String[]{"Lịch đấu", "Cầu thủ"};

    public FootballPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ScheduleFragment.getInstance();
            case 1:
                return PlayerFragment.getInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
