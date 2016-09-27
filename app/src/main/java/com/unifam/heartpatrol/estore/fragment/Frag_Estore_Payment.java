package com.unifam.heartpatrol.estore.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.estore.EstoreActivity;

/**
 * Created by Unifam on 9/26/2016.
 */
public class Frag_Estore_Payment extends Fragment {
    RadioButton rboBankTransfer, rboCreditCard;
    TextView txtPay, txtAmount;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_estore_payment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        InitControl(view);
    }

    void InitControl(View v){
        rboBankTransfer = (RadioButton)v.findViewById(R.id.rboBankTransfer);
        rboCreditCard = (RadioButton)v.findViewById(R.id.rboCreditCard);
        txtPay = (TextView)v.findViewById(R.id.btn_pay);
        txtAmount = (TextView)v.findViewById(R.id.text_amount);

        txtPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EstoreActivity)getActivity()).nextStep();
            }
        });
    }
}
