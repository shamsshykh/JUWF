package com.ju.saqib.juwf;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ju.saqib.juwf.Fragments.Booking;
import com.ju.saqib.juwf.Fragments.BookingForm;

//first make 2 fragment then add TabLayout Mainpage below TabLayout add viewpager
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[]{"Booking", "Events"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position) {
            case 0:
                        return  new BookingForm();
            case 1:
                return  new Booking();
        }
        return null;
    }
    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    } }
    //now cast tab abd view pager in mainpage
    //pagerAdapter=new ViewPagerAdapter(getChildFragmentManager());
    //viewPager.setAdapter(pagerAdapter);
    //tabLayout.setupWithViewPager(viewPager);
