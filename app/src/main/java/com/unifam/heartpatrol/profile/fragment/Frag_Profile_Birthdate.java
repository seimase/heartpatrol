package com.unifam.heartpatrol.profile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.unifam.heartpatrol.AppConstant;
import com.unifam.heartpatrol.AppController;
import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.profile.ProfileActivity;
import com.unifam.heartpatrol.register.RegisterActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

/**
 * Created by User on 9/16/2016.
 */
public class Frag_Profile_Birthdate extends Fragment implements com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener{
    TextView textNext;
    EditText txtDate;
    RadioButton rboMale, rboFemale;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the view from fragmenttab1.xml

        View view = inflater.inflate(R.layout.frag_profile_birthgender, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        InitControl(view);
    }

    void InitControl(View v){
        textNext = (TextView)v.findViewById(R.id.btn_next);
        txtDate = (EditText)v.findViewById(R.id.edt_birthdate);
        rboMale = (RadioButton) v.findViewById(R.id.rboMale);
        rboFemale = (RadioButton) v.findViewById(R.id.rboFemale);
        textNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validasi()){
                    if(rboMale.isChecked()) AppConstant.PROFILE_GENDER = "Male";
                    if(rboFemale.isChecked()) AppConstant.PROFILE_GENDER = "Female";
                    AppConstant.PROFILE_BIRTH_DATE = txtDate.getText().toString().trim();

                    ((ProfileActivity)getActivity()).displayView(1);
                }
            }
        });

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialodDatePick();
            }
        });

    }

    void showDialodDatePick(){
        Calendar now = Calendar.getInstance();
        com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                Frag_Profile_Birthdate.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
    }

    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String sMonth = String.valueOf((monthOfYear+1));
        if (sMonth.length() < 2) sMonth = "0" + sMonth;

        String sDay = String.valueOf(dayOfMonth);
        if (sDay.length() < 2) sDay = "0" + sDay;

        String strDate = year + "-" + sMonth + "-" + sDay;
        txtDate.setText(strDate);
    }

    boolean Validasi(){
        boolean bDone = true;
        String sDate = txtDate.getText().toString().trim();

        if (sDate.equals("")){
            AppController.getInstance().CustomeDialog(getActivity(), "Please input birth date first, Try Again !");
            return false;
        }

        if (!rboMale.isChecked() && !rboFemale.isChecked()){
            AppController.getInstance().CustomeDialog(getActivity(), "Please select gender, Try Again !");
            return false;
        }
        return bDone;
    }
}
