package com.unifam.heartpatrol.register.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.unifam.heartpatrol.AppConstant;
import com.unifam.heartpatrol.AppController;
import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.register.RegisterActivity;

/**
 * Created by Unifam on 9/15/2016.
 */
public class Frag_Register_Password extends Fragment {
    TextView textNext;
    EditText edtPassword, edtPasswordConfirm;
    ProgressDialog progress;
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
        edtPassword = (EditText)v.findViewById(R.id.edt_password);
        edtPasswordConfirm = (EditText)v.findViewById(R.id.edt_password_confirm);
        textNext = (TextView)v.findViewById(R.id.btn_next);
        textNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sPassword, sPasswordConfirm;
                sPassword = edtPassword.getText().toString();
                sPasswordConfirm = edtPasswordConfirm.getText().toString();

                if (sPassword.length() < 8){
                    AppController.getInstance().CustomeDialog(getActivity(),"minimum 8 character, Try Again !");
                }else{
                    if (!sPassword.equals(sPasswordConfirm)){
                        AppController.getInstance().CustomeDialog(getActivity(),"Incorrect Password, Try Again !");
                    }else {
                        AppConstant.AUTH_PASSWORD = sPassword;
                        ((RegisterActivity)getActivity()).displayView(2);
                    }
                }
            }
        });
    }

 }