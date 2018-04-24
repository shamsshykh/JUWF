package com.ju.saqib.juwf.Fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ju.saqib.juwf.MainActivity;
import com.ju.saqib.juwf.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingForm extends Fragment implements AdapterView.OnItemSelectedListener {
    Spinner event,date;
    ArrayAdapter adapter,dapter;
    EditText name,email,batch,enrolment,phone,msg;
    Button button;
    static  String ev;
    static  String year;
    public static  String  REGISTER_URL="http://zeenatkhanniazai.com/services/booking.php";

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    public BookingForm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_booking_form, container, false);
        event = view.findViewById(R.id.event);
        date = view.findViewById(R.id.year);

        adapter=ArrayAdapter.createFromResource(this.getActivity(), R.array.Event,android.R.layout.simple_spinner_item);
        dapter=ArrayAdapter.createFromResource(this.getActivity(), R.array.Year,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        event.setAdapter(adapter);
        date.setAdapter(dapter);
        name=view.findViewById(R.id.name);
        email=view.findViewById(R.id.email);
        batch=view.findViewById(R.id.batch);
        enrolment=view.findViewById(R.id.enrolment);
        phone=view.findViewById(R.id.number);
        button=view.findViewById(R.id.btnevent);
        msg=view.findViewById(R.id.msg);


        event.setOnItemSelectedListener(this);
        date.setOnItemSelectedListener(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!name.getText().toString().equals(null) && !name.getText().equals("") && !batch.getText().toString().equals("") &&
                        !email.getText().toString().equals("") ) {

                    SharedPreferences preferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("name", name.getText().toString());
                    if (email.getText().toString().matches(emailPattern)){
                        editor.putString("email", email.getText().toString());
                    }else {
                        email.setError("Please Input Valid Email");
                        return;
                    }
                    editor.putString("batch", batch.getText().toString());
                    editor.putString("enreolment", enrolment.getText().toString());
                    editor.putString("phone", phone.getText().toString());
                    editor.putString("year", year);
                    editor.putString("event", ev);
                    editor.commit();
                    startActivity(new Intent(getContext(), MainActivity.class));
                    Fragment fragment = null;
                    fragment = new Dashboard();

                    if (fragment != null) {
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, fragment);
                        ft.commit();
                    }
                }else {
                    Toast.makeText(getActivity(), "Please Fill The Fieled", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spinner = (Spinner) parent;
        if (spinner.getId()==R.id.event){
            ev = parent.getItemAtPosition(position).toString();
            if (ev.equalsIgnoreCase("Please Select Event")) {
                Toast.makeText(getContext(), "Please Select Event", Toast.LENGTH_SHORT).show();

            }
        }
        if (spinner.getId()==R.id.year){
            year = parent.getItemAtPosition(position).toString();

        }






    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getContext(), "Please Select Event", Toast.LENGTH_SHORT).show();

    }


}
