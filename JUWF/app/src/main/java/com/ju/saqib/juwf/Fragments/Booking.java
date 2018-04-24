package com.ju.saqib.juwf.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ju.saqib.juwf.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Booking extends Fragment {
    TextView name,batch,enrolment,phonenumber,email,event,year;


    public Booking() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vi= inflater.inflate(R.layout.fragment_booking, container, false);
        name=vi.findViewById(R.id.fname);
        batch=vi.findViewById(R.id.fbatch);
        enrolment=vi.findViewById(R.id.fenrolment);
        phonenumber=vi.findViewById(R.id.fphone);
        email=vi.findViewById(R.id.femail);
        event=vi.findViewById(R.id.feventnames);
        year=vi.findViewById(R.id.fdatee);



        SharedPreferences preferences= getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        name.setText(preferences.getString("name","No Name Found"));
        batch.setText(preferences.getString("batch","No batch Found"));
        enrolment.setText(preferences.getString("enreolment","No Enrolment Found"));
        phonenumber.setText(preferences.getString("phone","No Number Found"));
        email.setText(preferences.getString("email","No Email Found"));
        event.setText(preferences.getString("event","No Event Found"));
        year.setText(preferences.getString("year","No Event Found"));

        return vi;
    }

}
