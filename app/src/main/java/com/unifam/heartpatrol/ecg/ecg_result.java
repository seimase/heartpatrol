package com.unifam.heartpatrol.ecg;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.unifam.heartpatrol.AppConstant;
import com.unifam.heartpatrol.AppController;
import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.ecg.adapter.AdapterEcgResult_Dummy;
import com.unifam.heartpatrol.model.Ecg_Result_Model;
import com.unifam.heartpatrol.model.model_ecg_result;
import com.unifam.heartpatrol.ecg.adapter.AdapterEcgResult;
import com.unifam.heartpatrol.model.net.NetworkManager;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    com.unifam.heartpatrol.model.model_ecg_result model_ecg_result;
    ArrayList<com.unifam.heartpatrol.model.model_ecg_result> Arymodel_ecg_result;

    Ecg_Result_Model ecgResultModel;
    Ecg_Result_Model resultModel;
    RelativeLayout layoutLoading;

    List<String> listEcg;
    boolean isLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecg_result);
        isLoading = false;
        InitControl();
        //FillGrid();
        FillDummy();

    }

    void InitControl(){
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        imgBack = (ImageView)findViewById(R.id.arrow_back);
        txtLabel = (TextView)findViewById(R.id.textLabel);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        layoutLoading = (RelativeLayout)findViewById(R.id.layout_loading);

        txtLabel.setText(getResources().getText(R.string.ecg_result));
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

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("History ECG", R.drawable.ic_email_2, R.color.colorAccent);
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
                        /*try{
                            int iOverRead = 0;
                            listEcg = new ArrayList<String>();
                            if (!isLoading){
                                for (Ecg_Result_Model.Data dat: ecgResultModel.data){
                                    if (dat.flag){
                                        listEcg.add(dat.ecg_id);
                                        iOverRead += 1;
                                    }
                                }

                                if (listEcg.size() > 0){
                                    doDialogResult(Integer.toString(iOverRead));
                                }else{
                                    AppController.getInstance().CustomeDialog(ecg_result.this, "Please select ECG Result, Try Again!");
                                }

                            }
                        }catch (Exception e){
                            AppController.getInstance().CustomeDialog(ecg_result.this, "Please select ECG Result, Try Again!");
                        }*/

                        mIntent = new Intent(getBaseContext(), Ecg_Over_Read.class);
                        startActivity(mIntent);

                        break;
                    case 2: //Delete
                        int iDelete = 0;
                        for (model_ecg_result dat: Arymodel_ecg_result){
                            if (dat.getAtrCheck1()){
                                iDelete += 1;
                            }
                        }
                        if (listEcg.size() > 0){
                            doDialog(Integer.toString(iDelete));
                        }else{
                            AppController.getInstance().CustomeDialog(ecg_result.this, "Please select ECG Result, Try Again!");
                        }

                        /*try{
                            int iDelete = 0;
                            listEcg = new ArrayList<String>();
                            if (!isLoading){
                                for (Ecg_Result_Model.Data dat: ecgResultModel.data){
                                    if (dat.flag){
                                        listEcg.add(dat.ecg_id);
                                        iDelete += 1;
                                    }
                                }
                                if (listEcg.size() > 0){
                                    doDialog(Integer.toString(iDelete));
                                }else{
                                    AppController.getInstance().CustomeDialog(ecg_result.this, "Please select ECG Result, Try Again!");
                                }
                            }
                        }catch (Exception e){
                            AppController.getInstance().CustomeDialog(ecg_result.this, "Please select ECG Result, Try Again!");
                        }*/
                        //CustomAlertDialogBuilder builder = new CustomAlertDialogBuilder(getActivity(), getResources().getColor(R.color.green_xxl));
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

    private void doDialog(String sValue) {
        final Dialog dialog = new Dialog(ecg_result.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_ecg_result_delete);
        //dialog.setTitle("Title...");
        // set the custom dialog components - text, image and button
        TextView txtMessege = (TextView) dialog.findViewById(R.id.message);
        TextView txtYes = (TextView) dialog.findViewById(R.id.positive_button);
        TextView txtNo = (TextView) dialog.findViewById(R.id.negative_button);
        txtMessege.setText(getResources().getText(R.string.Delete_ecg_report).toString().replace("[2]",sValue));

        txtYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //DeleteEcgResult(listEcg);
            }
        });

        txtNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void doDialogResult(final String sValue) {
        final Dialog dialog = new Dialog(ecg_result.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_ecg_result_delete);
        //dialog.setTitle("Title...");
        // set the custom dialog components - text, image and button
        TextView txtMessege = (TextView) dialog.findViewById(R.id.message);
        TextView txtYes = (TextView) dialog.findViewById(R.id.positive_button);
        TextView txtNo = (TextView) dialog.findViewById(R.id.negative_button);
        txtMessege.setText(getResources().getText(R.string.Over_read_ecg_report).toString().replace("[2]",sValue));

        txtYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                AppConstant.ECG_LIST = listEcg;
                Intent mIntent = new Intent(ecg_result.this, Ecg_Over_Read.class);
                startActivity(mIntent);
            }
        });

        txtNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    void FillGrid(){
        isLoading = true;
        layoutLoading.setVisibility(View.VISIBLE);
        ecgResultModel = null;
        try{
            Call<Ecg_Result_Model> call = NetworkManager.getNetworkService(this).getEcgResult(AppConstant.AUTH_USERNAME);
            call.enqueue(new Callback<Ecg_Result_Model>() {
                @Override
                public void onResponse(Call<Ecg_Result_Model> call, Response<Ecg_Result_Model> response) {
                    int code = response.code();
                    isLoading = false;
                    layoutLoading.setVisibility(View.GONE);
                    if (code == 200){
                        ecgResultModel = response.body();
                        if (!ecgResultModel.error){
                            if (ecgResultModel.data.size() > 0){
                                FillAdapter();
                            }
                        }else{
                            FillAdapter();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Ecg_Result_Model> call, Throwable t) {
                    isLoading = false;
                    layoutLoading.setVisibility(View.GONE);
                }
            });
        }catch (Exception e){
            isLoading = false;
            layoutLoading.setVisibility(View.GONE);
        }
    }

    void DeleteEcgResult(List<String> ecgList){
        final ProgressDialog progress;
        progress = ProgressDialog.show(this, "Information",
                "Delete data", true);
        layoutLoading.setVisibility(View.VISIBLE);
        try{
            Call<Ecg_Result_Model> call = NetworkManager.getNetworkService(this).postEcgResultDelete(AppConstant.AUTH_USERNAME,
                    ecgList);
            call.enqueue(new Callback<Ecg_Result_Model>() {
                @Override
                public void onResponse(Call<Ecg_Result_Model> call, Response<Ecg_Result_Model> response) {
                    int code = response.code();
                    progress.dismiss();
                    if (code == 200){
                        resultModel = response.body();
                        if(!resultModel.error){
                            FillGrid();
                        }
                    }
                }
                @Override
                public void onFailure(Call<Ecg_Result_Model> call, Throwable t) {
                    progress.dismiss();
                }
            });
        }catch (Exception e){
            progress.dismiss();
        }
    }

    void FillAdapter(){
        mAdapter = new AdapterEcgResult(getBaseContext(), ecgResultModel, new AdapterEcgResult.OnDownloadClicked() {
            @Override
            public void OnDownloadClicked(String sUrl, boolean bStatus) {
                Intent mIntent = new Intent(getBaseContext(), Ecg_Review_PDF.class);
                startActivity(mIntent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    void FillDummy(){
        Arymodel_ecg_result = new ArrayList<>();
        for(int i = 1; i < 10 ; i++){
            model_ecg_result = new model_ecg_result();
            model_ecg_result.setAtr1("02 / 01 / 2015");
            model_ecg_result.setAtr2("9:37 PM");
            model_ecg_result.setAtr3("No abnormality Detected");
            model_ecg_result.setAtr4("1");
            model_ecg_result.setAtrCheck1(false);
            if ((i%3)  == 0){
                model_ecg_result.setAtr3("Abnormality Detected");
                model_ecg_result.setAtr4("");
            }

            Arymodel_ecg_result.add(model_ecg_result);

        }

        mAdapter = new AdapterEcgResult_Dummy(getBaseContext(), Arymodel_ecg_result, new AdapterEcgResult_Dummy.OnDownloadClicked() {
            @Override
            public void OnDownloadClicked(String sUrl, boolean bStatus) {
                Intent mIntent = new Intent(getBaseContext(), Ecg_Review_PDF.class);
                startActivity(mIntent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //FillGrid();
        layoutLoading.setVisibility(View.GONE);
        FillDummy();
    }
}
