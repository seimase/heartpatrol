package com.unifam.heartpatrol.register.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unifam.heartpatrol.MainMenu;
import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.register.RegisterActivity;

/**
 * Created by Unifam on 9/15/2016.
 */
public class Frag_Register_Done extends Fragment {
    TextView txtDone;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_register_done, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        InitControl(view);
    }

    void InitControl(View v){
        txtDone = (TextView)v.findViewById(R.id.btn_done);
        txtDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().finish();
                Intent intent = new Intent(getActivity(), MainMenu.class);
                startActivity(intent);
            }
        });
    }
}
