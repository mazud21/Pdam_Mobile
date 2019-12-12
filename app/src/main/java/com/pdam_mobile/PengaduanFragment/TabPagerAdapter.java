package com.pdam_mobile.PengaduanFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {

    int tabCount;

    public TabPagerAdapter(FragmentManager fm, int numberOftabs) {
        super(fm);
        this.tabCount = numberOftabs;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                return new Pengaduan_Frag();
            case 1:
                return new Monitor_Frag();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
