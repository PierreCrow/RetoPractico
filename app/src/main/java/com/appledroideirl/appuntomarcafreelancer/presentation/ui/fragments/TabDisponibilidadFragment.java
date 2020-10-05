package com.appledroideirl.appuntomarcafreelancer.presentation.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Constants;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TabDisponibilidadFragment extends BaseFragment


{

    ImageView ivDiary;
    //  @BindView(R.id.tabs)
    public static TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.ivClose)
    ImageView ivClose;




    public static int int_items = 2;

    boolean backToAccountFragment;
    boolean backToFavoritesFragment;

    public static String FRAGMENTTT = "";

    boolean alreadySync = true;


    public static boolean alreadyShowUpdateContent;

    public static Context generalContext;

    TabLayout tabs;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View x = inflater.inflate(R.layout.appu_tu_disponibilidad_fragment, null);
        injectView(x);
        initUI(x);
        setViewPagerAndTabs();
        //  selectPage();
        //  setPositionViewPager();
        return x;
    }



    private void initUI(View x) {
        tabLayout=(TabLayout)x.findViewById(R.id.tabsappunto);
        generalContext=getContext();




        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();

            }
        });

        //   setupViewPager(viewPager);

        //   tabLayout.setupWithViewPager(viewPager);

        //  setupTabIcons();
    }





    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    //------------------------------------------------------------------

    private void setViewPagerAndTabs() {
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                // tabLayout.getTabAt(Constants.FRAGMENTS_TABS.HOME).setIcon(tabIconsSelected[0]);
                //  tabLayout.getTabAt(Constants.FRAGMENTS_TABS.FAVORITES).setIcon(tabIconsUnSelected[1]);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                /*
                switch (tab.getPosition()) {
                    case Constants.FRAGMENTS_TABS.HOME: {
                        if (tabLayout.getSelectedTabPosition() == Constants.FRAGMENTS_TABS.HOME) {
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.HOME).setIcon(tabIconsSelected[0]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.FAVORITES).setIcon(tabIconsUnSelected[1]);

                        }
                    }
                    case Constants.FRAGMENTS_TABS.FAVORITES: {
                        if (tabLayout.getSelectedTabPosition() == Constants.FRAGMENTS_TABS.FAVORITES) {
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.HOME).setIcon(tabIconsUnSelected[0]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.FAVORITES).setIcon(tabIconsSelected[1]);
                        }
                    }
                    case Constants.FRAGMENTS_TABS.EVENTS: {
                        if (tabLayout.getSelectedTabPosition() == Constants.FRAGMENTS_TABS.EVENTS) {
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.HOME).setIcon(tabIconsUnSelected[0]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.FAVORITES).setIcon(tabIconsUnSelected[1]);
                        }
                    }
                    case Constants.FRAGMENTS_TABS.ACCOUNT: {
                        if (tabLayout.getSelectedTabPosition() == Constants.FRAGMENTS_TABS.ACCOUNT) {
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.HOME).setIcon(tabIconsUnSelected[0]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.FAVORITES).setIcon(tabIconsUnSelected[1]);
                        }
                    }
                }
                */
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }


    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case Constants.FRAGMENTS_TABS.HOME: {
                    return new TabDisponibilidadADomicilioFragment();
                }
                case Constants.FRAGMENTS_TABS.FAVORITES: {
                    return new TabDisponibilidadEnLocalFragment();
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return int_items;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0 :
                    return "A domicilio";
                case 1 :
                    return "En local";
            }
            return null;
        }
    }
}
