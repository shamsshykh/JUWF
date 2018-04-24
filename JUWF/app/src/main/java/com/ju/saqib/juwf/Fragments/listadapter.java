package com.ju.saqib.juwf.Fragments;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ju.saqib.juwf.R;

/**
 * Created by Mohammad.shams on 12/11/2017.
 */

class  listadapter extends ArrayAdapter {
    String[] name={"Events Karachi","Events Islamabad","Events Hydrabad","Events Kpk","Events Lahor"};
    int [] image={R.drawable.fes,R.drawable.sports,R.drawable.eve,R.drawable.images,R.drawable.download};

    public listadapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        Toast.makeText(getContext(), "aassfx", Toast.LENGTH_SHORT).show();
        v=((Activity) getContext()).getLayoutInflater().inflate(R.layout.customelayout, null);

        TextView txt1 = v.findViewById(R.id.cstextview);
        ImageView img = v.findViewById(R.id.cstimage);

        txt1.setText(name[position]);
        img.setBackgroundResource(image[position]);

        return v;

    }
}
