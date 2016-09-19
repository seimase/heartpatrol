package com.unifam.heartpatrol.ecg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexzh.circleimageview.CircleImageView;
import com.alexzh.circleimageview.ItemSelectedListener;
import com.unifam.heartpatrol.R;

/**
 * Created by Unifam on 9/19/2016.
 */
public class ecg_recording extends AppCompatActivity {
    ImageView imgBack;
    TextView txtLabel;
    CircleImageView imgRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecg_recording);

        InitControl();
    }

    void InitControl(){
        imgBack = (ImageView)findViewById(R.id.arrow_back);
        txtLabel = (TextView)findViewById(R.id.textLabel);

        txtLabel.setText(getResources().getText(R.string.ecg_recording));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgRecord = (CircleImageView)findViewById(R.id.imageView7);

        imgRecord.setOnItemSelectedClickListener(new ItemSelectedListener() {
            @Override
            public void onSelected(View view) {

            }

            @Override
            public void onUnselected(View view) {

            }
        });
    }


}

