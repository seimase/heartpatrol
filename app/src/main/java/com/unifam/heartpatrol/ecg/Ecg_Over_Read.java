package com.unifam.heartpatrol.ecg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.unifam.heartpatrol.AppConstant;
import com.unifam.heartpatrol.AppController;
import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.ecg.adapter.AdapterEcgOverRead;
import com.unifam.heartpatrol.estore.EstoreActivity;
import com.unifam.heartpatrol.model.Ecg_Result_Model;
import com.unifam.heartpatrol.model.ListData;
import com.unifam.heartpatrol.model.net.NetworkManager;
import com.unifam.heartpatrol.transaction.Adapter.AdapterTransaction;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    Ecg_Result_Model ecgResultModel;
    RelativeLayout layoutLoading;

    boolean bConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecg_over_read);
        bConfirm = false;
        InitControl();
        setSupportActionBar(toolbar);
        FillGrid();
    }

    void InitControl() {
        layoutLoading = (RelativeLayout)findViewById(R.id.layout_loading);
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


        txtConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bConfirm){
                    finish();
                    vConfirm();
                }
            }
        });

        txtBuyCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent mIntent = new Intent(getBaseContext(), EstoreActivity.class);
                startActivity(mIntent);
            }
        });
    }

    void vConfirm(){
        try{
            Call<Ecg_Result_Model> call = NetworkManager.getNetworkService(this).getEcgOverReadConfirm(AppConstant.AUTH_USERNAME,AppConstant.ECG_LIST);
            call.enqueue(new Callback<Ecg_Result_Model>() {
                @Override
                public void onResponse(Call<Ecg_Result_Model> call, Response<Ecg_Result_Model> response) {
                    int code = response.code();
                    if (code == 200){

                    }
                }

                @Override
                public void onFailure(Call<Ecg_Result_Model> call, Throwable t) {

                }
            });
        }catch (Exception e){

        }
    }

    void FillGrid(){
       /* AryListData = new ArrayList<>();

        listData = new ListData();
        long lCredit = 0;
        for(int i = 1; i < 8 ; i++){
            listData = new ListData();
            listData.setAtr1("Report 09/02/2015 9:37 PM");
            listData.setAtr2("10");
            lCredit += 10;
            AryListData.add(listData);
        }

        txtCredit.setText(Long.toString(lCredit));*/
        layoutLoading.setVisibility(View.VISIBLE);
        try{
            Call<Ecg_Result_Model> call = NetworkManager.getNetworkService(this).getEcgOverRead(AppConstant.AUTH_USERNAME, AppConstant.ECG_LIST);
            call.enqueue(new Callback<Ecg_Result_Model>() {
                @Override
                public void onResponse(Call<Ecg_Result_Model> call, Response<Ecg_Result_Model> response) {
                    layoutLoading.setVisibility(View.GONE);
                    if (response.code() == 200){
                        ecgResultModel = response.body();
                        if (!ecgResultModel.error){
                            int iTotalResultCredit = ecgResultModel.data.size() * 10;
                            txtYourCredits.setText(Long.toString(ecgResultModel.credits));
                            txtCredit.setText(Long.toString(ecgResultModel.data.size() * 10));

                            bConfirm = true;
                            if (iTotalResultCredit > ecgResultModel.credits){
                                bConfirm = false;
                                txtConfirm.setBackground(getResources().getDrawable(R.drawable.btn_shape_grey));
                            }

                            mAdapter = new AdapterEcgOverRead(getBaseContext(), ecgResultModel);
                            mRecyclerView.setAdapter(mAdapter);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Ecg_Result_Model> call, Throwable t) {
                    layoutLoading.setVisibility(View.GONE);
                }
            });
        }catch (Exception e){
            layoutLoading.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case R.id.action_home:
                //NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
