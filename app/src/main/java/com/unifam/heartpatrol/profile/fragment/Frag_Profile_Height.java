package com.unifam.heartpatrol.profile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unifam.heartpatrol.AppConstant;
import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.profile.ProfileActivity;

import ui.widget.RulerView;

/**
 * Created by User on 9/30/2016.
 */
public class Frag_Profile_Height extends Fragment {

    RulerView rulerView;
    TextView txtHeight, btnNext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_profile_height, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        InitControl(view);
    }

    void InitControl(View v){
        txtHeight = (TextView)v.findViewById(R.id.text_height);
        rulerView = (RulerView)v.findViewById(R.id.ruler_view);
        btnNext = (TextView)v.findViewById(R.id.btn_next);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ProfileActivity)getActivity()).displayView(2);
            }
        });

        rulerView.setOnScaleListener(new RulerView.OnScaleListener() {
            @Override
            public void onScaleChanged(int scale) {
                txtHeight.setText(Integer.toString(scale));
                AppConstant.PROFILE_HEIGHT = scale;
            }
        });
    }
}
