package com.ju.saqib.juwf.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ju.saqib.juwf.R;

public class upcomming_event extends Fragment {
    String[] name = {"Events Karachi", "Events Islamabad", "Events Hydrabad", "Events Kpk", "Events Lahor"};
    int[] image = {R.drawable.fes, R.drawable.sports, R.drawable.eve, R.drawable.images, R.drawable.download};
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcomming_event, container, false);
    listView=view.findViewById(R.id.listvieww);
    listView.setAdapter(new PasswordAdapter(getActivity(),R.layout.customelayout,name));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("UpComming Event");

    }

    class PasswordAdapter extends ArrayAdapter {

        public PasswordAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            v = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.customelayout, null);

            TextView txt1 = v.findViewById(R.id.cstextview);
            ImageView img = v.findViewById(R.id.cstimage);

            txt1.setText(name[position]);
            img.setBackgroundResource(image[position]);

            return v;

        }
    }
}
