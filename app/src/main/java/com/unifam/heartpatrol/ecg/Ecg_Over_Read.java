package com.unifam.heartpatrol.ecg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.unifam.heartpatrol.AppController;
import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.ecg.adapter.AdapterEcgOverRead;
import com.unifam.heartpatrol.model.ListData;
import com.unifam.heartpatrol.transaction.Adapter.AdapterTransaction;

import java.util.ArrayList;

/**
 * Created by Unifam on 9/20/2016.
 */
public class Ecg_Over_Read extends AppCompatActivity {
    ImageView imgBack;
    TextView txtLabel,
            txtCredit,
            txtYourCredits
    ;
    Toolbar toolbar;

    TextView txtBuyCredits,
            txtConfirm;

    ListData listData;
    ArrayList<ListData> AryListData;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecg_over_read);
        InitControl();
        FillGrid();
    }

    void InitControl() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        imgBack = (ImageView) findViewById(R.id.arrow_back);
        txtLabel = (TextView) findViewById(R.id.textLabel);
        txtBuyCredits = (TextView) findViewById(R.id.btn_buy);
        txtConfirm = (TextView) findViewById(R.id.btn_Confirm);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        txtCredit = (TextView) findViewById(R.id.text_credits);
        txtYourCredits = (TextView) findViewById(R.id.text_your_credits);

        txtCredit.setText("80");

        txtLabel.setText(getResources().getText(R.string.ecg_over_read));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    void FillGrid(){
        AryListData = new ArrayList<>();

        listData = new ListData();
        long lCredit = 0;
        for(int i = 1; i < 8 ; i++){
            listData = new ListData();
            listData.setAtr1("Report 09/02/2015 9:37 PM");
            listData.setAtr2("10");
            lCredit += 10;
            AryListData.add(listData);
        }

        txtCredit.setText(Long.toString(lCredit));

        mAdapter = new AdapterEcgOverRead(this, AryListData);
        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);
    }
}
