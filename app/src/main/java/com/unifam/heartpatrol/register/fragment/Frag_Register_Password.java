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
public class Frag_Register_Password extends Fragment {
    TextView textNext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the view from fragmenttab1.xml

        View view = inflater.inflate(R.layout.frag_register_password, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        InitControl(view);
    }

    void InitControl(View v){
        textNext = (TextView)v.findViewById(R.id.btn_next);
        textNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RegisterActivity)getActivity()).displayView(2);
            }
        });
    }
}
