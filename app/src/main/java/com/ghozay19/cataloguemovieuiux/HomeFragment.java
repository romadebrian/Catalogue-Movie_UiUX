package com.ghozay19.cataloguemovieuiux;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    Toolbar mToolbar;
    ViewPager mViewPager;
    TabLayout mTablayout;
//    TabPageAdapter mAdapter;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false);
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mViewPager.setAdapter(new sliderAdapter(getChildFragmentManager()));

        mTablayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mTablayout.setupWithViewPager(mViewPager);

        mTablayout.post(new Runnable() {
            @Override
            public void run() {
                mTablayout.setupWithViewPager(mViewPager);
            }
        });

        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    private class sliderAdapter extends FragmentPagerAdapter {

        String now_playing = getResources().getString(R.string.now_playing);
        String upcoming = getResources().getString(R.string.up_coming);
        final String tabs[] = {now_playing, upcoming};

        public sliderAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new NowPlayingFragment();
                case 1:
                    return new UpComingFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabs.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }


//        int PAGE_COUNT = 2;
//
//        private String judulTab[] = new String[]{"KESATU","KE DUA"};
//
//        @Override
//        public Fragment getItem(int position) {
//            switch (position){
//                case 0:
//                    return new NowPlayingFragment();
//                case 1:
//                    return new UpcomingFragment();
//            }
//            return null;
//        }
//
//        @Override
//        public int getCount() {
//            return PAGE_COUNT;
//        }
////
//        @Override
//        public CharSequence getPageTitle(int position){
//            return judulTab[position];
//        }
//    }
}

