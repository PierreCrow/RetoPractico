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
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Helper;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

public class
TabHome extends BaseFragment {


    ImageView ivDiary;
    //  @BindView(R.id.tabs)
    public static TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    public static int int_items = 4;

    public static Context generalContext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View x = inflater.inflate(R.layout.appu_tab_home, null);
        injectView(x);
        initUI(x);
        setViewPagerAndTabs();
        //  selectPage();
        //  setPositionViewPager();
        return x;
    }


    private void initUI(View x) {
        tabLayout = (TabLayout) x.findViewById(R.id.tabsappunto);
        generalContext = getContext();
    }


    private void setViewPagerAndTabs() {
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.getTabAt(Constants.FRAGMENTS_TABS.HOME).setIcon(tabIconsSelected[0]);
                tabLayout.getTabAt(Constants.FRAGMENTS_TABS.FAVORITES).setIcon(tabIconsUnSelected[1]);
                tabLayout.getTabAt(Constants.FRAGMENTS_TABS.EVENTS).setIcon(tabIconsUnSelected[2]);
                tabLayout.getTabAt(Constants.FRAGMENTS_TABS.ACCOUNT).setIcon(tabIconsUnSelected[3]);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case Constants.FRAGMENTS_TABS.HOME: {
                        if (tabLayout.getSelectedTabPosition() == Constants.FRAGMENTS_TABS.HOME) {
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.HOME).setIcon(tabIconsSelected[0]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.FAVORITES).setIcon(tabIconsUnSelected[1]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.EVENTS).setIcon(tabIconsUnSelected[2]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.ACCOUNT).setIcon(tabIconsUnSelected[3]);
                            PedidosFragment.mensajeAceptarDenegar="";
                        }
                    }
                    case Constants.FRAGMENTS_TABS.FAVORITES: {
                        if (tabLayout.getSelectedTabPosition() == Constants.FRAGMENTS_TABS.FAVORITES) {
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.HOME).setIcon(tabIconsUnSelected[0]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.FAVORITES).setIcon(tabIconsSelected[1]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.EVENTS).setIcon(tabIconsUnSelected[2]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.ACCOUNT).setIcon(tabIconsUnSelected[3]);
                        }
                    }
                    case Constants.FRAGMENTS_TABS.EVENTS: {
                        if (tabLayout.getSelectedTabPosition() == Constants.FRAGMENTS_TABS.EVENTS) {
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.HOME).setIcon(tabIconsUnSelected[0]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.FAVORITES).setIcon(tabIconsUnSelected[1]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.EVENTS).setIcon(tabIconsSelected[2]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.ACCOUNT).setIcon(tabIconsUnSelected[3]);
                            PedidosFragment.mensajeAceptarDenegar="";
                        }
                    }
                    case Constants.FRAGMENTS_TABS.ACCOUNT: {
                        if (tabLayout.getSelectedTabPosition() == Constants.FRAGMENTS_TABS.ACCOUNT) {
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.HOME).setIcon(tabIconsUnSelected[0]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.FAVORITES).setIcon(tabIconsUnSelected[1]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.EVENTS).setIcon(tabIconsUnSelected[2]);
                            tabLayout.getTabAt(Constants.FRAGMENTS_TABS.ACCOUNT).setIcon(tabIconsSelected[3]);
                            PedidosFragment.mensajeAceptarDenegar="";
                        }
                    }
                }
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
                    return new HomeFragment();
                }
                case Constants.FRAGMENTS_TABS.FAVORITES: {
                    return new PedidosFragment();
                }
                case Constants.FRAGMENTS_TABS.EVENTS: {
                    return new ReportesFragment();
                }
                case Constants.FRAGMENTS_TABS.ACCOUNT: {
                    return new ProfileFreelancerFragment();

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
            return null;
        }
    }


    private int[] tabIconsUnSelected = {
            R.drawable.appu_ic_home_appunto_off,
            R.drawable.appu_ic_pedidos_off,
            R.drawable.appu_ic_reportes_off,
            R.drawable.appu_ic_perfil_appunto_off
    };

    private int[] tabIconsSelected = {
            R.drawable.appu_ic_home_on,
            R.drawable.appu_ic_pedidos_on,
            R.drawable.appu_ic_reportes_on,
            R.drawable.appu_ic_perfil_appunto_on
    };
}