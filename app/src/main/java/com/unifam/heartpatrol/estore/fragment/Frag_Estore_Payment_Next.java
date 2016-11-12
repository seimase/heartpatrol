package com.unifam.heartpatrol.estore.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.estore.EstoreActivity;

/**
 * Created by User on 9/26/2016.
 */
public class Frag_Estore_Payment_Next extends Fragment {

    TextView txtTime,
        txtAmount,
        btnConfirm
    ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_estore_payment_next, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        InitControl(view);
    }

    void InitControl(View v){
        txtTime = (TextView)v.findViewById(R.id.text_time);
        txtAmount = (TextView)v.findViewById(R.id.text_amount);
        btnConfirm = (TextView)v.findViewById(R.id.btn_Confirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EstoreActivity)getActivity()).nextStep();
            }
        });
    }
}
