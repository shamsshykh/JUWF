package com.ju.saqib.juwf.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ju.saqib.juwf.R;
import com.ju.saqib.juwf.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Dashboard extends Fragment {

    ViewPager viewPager;
    RelativeLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    ListView listView;
    ArrayList<String> ttitle;
    String[] name={"Events Karachi","Events Islamabad","Events Hydrabad","Events Kpk","Events Lahor"};
    int [] image={R.drawable.fes,R.drawable.sports,R.drawable.eve,R.drawable.images,R.drawable.download};
    String[] decs={"These example sentences are selected automatically from various online news sources to reflect current usage of the word",
            "These example sentences are selected automatically from various online news sources to reflect current usage of the word",
            "These example sentences are selected automatically from various online news sources to reflect current usage of the word",
            "These example sentences are selected automatically from various online news sources to reflect current usage of the word",
            "These example sentences are selected automatically from various online news sources to reflect current usage of the word",
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dashboard, container,false);
        listView=   view.findViewById(R.id.listview);
        listView.setAdapter(new PasswordAdapter(getActivity(),R.layout.customelayout,name));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Gallery");
    }
    class PasswordAdapter extends ArrayAdapter {

        public PasswordAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
        }
        class viewHolder{
            TextView txt1,dec;
            ImageView img;
            public viewHolder(View view){
                txt1 = view.findViewById(R.id.cstextview);
                img = view.findViewById(R.id.cstimage);
                dec = view.findViewById(R.id.dec);
            }

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v=convertView;
            viewHolder holder=null;
            if (v==null) {
                v = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.customelayout, null);
                holder=new viewHolder(v);
                v.setTag(holder);
                Log.e("tag","first time created");

            }else
            {
               holder= (viewHolder) v.getTag();
                Log.e("tag","recyling ");
            }
            holder.txt1.setText(name[position]);
            holder.img.setBackgroundResource(image[position]);
            holder.dec.setText(decs[position]);
            return v;
            }
        }
        // listview.setadapter(this,custome recourse,classname);

    }

