package com.unifam.heartpatrol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.fitness.data.Application;
import com.unifam.heartpatrol.model.Register;

/**
 * Created by Unifam on 9/16/2016.
 */
public class SettingsActivity extends AppCompatActivity {
    ImageView imgBack;
    TextView txtLabel, txtLogOut;
    RadioButton rbo1, rbo6, rboMetric, rboImperial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        InitControl();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register register = null;
                AppConstant.bExit = true;
                AppController.getInstance().getSessionManager().setUserAccount(register);
                int pid = android.os.Process.myPid();
                android.os.Process.killProcess(pid);
            }
        });

        rbo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbo1.isChecked()) rbo6.setChecked(false);
                if (!rbo1.isChecked()) rbo6.setChecked(true);
            }
        });

        rbo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbo6.isChecked()) rbo1.setChecked(false);
                if (!rbo6.isChecked()) rbo1.setChecked(true);
            }
        });

        rboMetric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rboMetric.isChecked()) rboImperial.setChecked(false);
                if (!rboMetric.isChecked()) rboImperial.setChecked(true);
            }
        });

        rboImperial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rboImperial.isChecked()) rboMetric.setChecked(false);
                if (!rboImperial.isChecked()) rboMetric.setChecked(true);
            }
        });

    }

    void InitControl(){
        imgBack = (ImageView)findViewById(R.id.arrow_back);
        txtLabel = (TextView)findViewById(R.id.textLabel);
        txtLogOut  = (TextView)findViewById(R.id.txtLogOut);


        txtLabel.setText(getResources().getText(R.string.Settings));
        rbo1 = (RadioButton)findViewById(R.id.rbo1);
        rbo6 = (RadioButton)findViewById(R.id.rbo6);
        rboMetric = (RadioButton)findViewById(R.id.rboMetric);
        rboImperial = (RadioButton)findViewById(R.id.rboImperial);
    }
}
