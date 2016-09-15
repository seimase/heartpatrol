package com.unifam.heartpatrol.register.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.register.RegisterActivity;

/**
 * Created by Unifam on 9/15/2016.
 */
public class Frag_Register_With extends Fragment {
    TextView textRegister;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the view from fragmenttab1.xml

        View view = inflater.inflate(R.layout.frag_register_with, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        InitControl(view);
    }

    void InitControl(View v){
        textRegister = (TextView)v.findViewById(R.id.register_button);
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RegisterActivity)getActivity()).displayView(1);
            }
        });
    }

}
