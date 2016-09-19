package com.unifam.heartpatrol.ecg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.model.Model_ecg_result;
import com.unifam.heartpatrol.model.adapter.AdapterEcgResult;


import java.util.ArrayList;

/**
 * Created by Unifam on 9/19/2016.
 */
public class ecg_result extends AppCompatActivity {
    AHBottomNavigation bottomNavigation;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    ImageView imgBack;
    TextView txtLabel;
    Toolbar toolbar;
    Model_ecg_result model_ecg_result;
    ArrayList<Model_ecg_result> Arymodel_ecg_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecg_result);

        InitControl();
        FillGrid();
    }

    void InitControl(){
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        imgBack = (ImageView)findViewById(R.id.arrow_back);
        txtLabel = (TextView)findViewById(R.id.textLabel);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        txtLabel.setText(getResources().getText(R.string.Transaction));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Email ECG Result", R.drawable.ic_email_2, R.color.colorAccent);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Get ECG Over-Read", R.drawable.uff_ecg_review, R.color.colorAccent);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Delete", R.drawable.gns_delete, R.color.colorAccent);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);



        bottomNavigation.setForceTitlesDisplay(true);
        bottomNavigation.setSelected(false);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                Intent mIntent;
                switch (position){

                    case 0: //Email ECG Result

                        break;
                    case 1: //Get ECG Over-Read

                        break;
                    case 2: //Delete
                        break;
                }
                return true;
            }
        });
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override public void onPositionChange(int y) {
                // Manage the new y position
            }
        });
    }

    void FillGrid(){
        Arymodel_ecg_result = new ArrayList<>();
        for(int i = 1; i < 10 ; i++){
            model_ecg_result = new Model_ecg_result();
            model_ecg_result.setAtr1("02 / 01 / 2015");
            model_ecg_result.setAtr2("9:37 PM");
            model_ecg_result.setAtr3("Abnormality detected");
            model_ecg_result.setAtr4("1");
            if ((i%3)  == 0){
                model_ecg_result.setAtr3("No abnormality detected");
                model_ecg_result.setAtr4("");
            }

            Arymodel_ecg_result.add(model_ecg_result);
        }

        mAdapter = new AdapterEcgResult(this, Arymodel_ecg_result);
        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);
    }
}
