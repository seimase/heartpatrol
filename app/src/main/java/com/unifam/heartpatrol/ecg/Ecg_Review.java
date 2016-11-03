package com.unifam.heartpatrol.ecg;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
import com.unifam.heartpatrol.ecg.adapter.AdapterEcgReview;
import com.unifam.heartpatrol.model.Ecg_Result_Model;
import com.unifam.heartpatrol.model.Model_ecg_review;
import com.unifam.heartpatrol.model.net.NetworkManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Unifam on 9/20/2016.
 */
public class Ecg_Review extends AppCompatActivity{
    AHBottomNavigation bottomNavigation;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    ImageView imgBack;
    TextView txtLabel;
    Toolbar toolbar;
    Model_ecg_review model_ecg_review;
    ArrayList<Model_ecg_review> Arymodel_ecg_review;

    Ecg_Result_Model ecgResultModel;
    Ecg_Result_Model resultModel;
    RelativeLayout layoutLoading;
    boolean isLoading;

    List<String> listEcg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecg_review);
        isLoading = false;
        InitControl();
        setSupportActionBar(toolbar);
        FillGrid();
    }

    void InitControl(){
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        imgBack = (ImageView)findViewById(R.id.arrow_back);
        txtLabel = (TextView)findViewById(R.id.textLabel);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        layoutLoading = (RelativeLayout)findViewById(R.id.layout_loading);
        txtLabel.setText(getResources().getText(R.string.ecg_review));
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
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Delete", R.drawable.gns_delete, R.color.colorAccent);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);

        bottomNavigation.setForceTitlesDisplay(true);
        bottomNavigation.setSelected(false);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                Intent mIntent;
                switch (position){

                    case 0: //Email ECG Result
                        int iResult = 0;

                        break;
                    case 1: //Delete
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
                                AppController.getInstance().CustomeDialog(Ecg_Review.this, "Please select ECG Review, Try Again!");
                            }

                        }
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

    private void doDialogResult(String sValue) {
        final Dialog dialog = new Dialog(Ecg_Review.this);
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
                DeleteEcgReview(listEcg);
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

    void DeleteEcgReview(List<String> ecgList){
        final ProgressDialog progress;
        progress = ProgressDialog.show(this, "Information",
                "Delete data", true);
        layoutLoading.setVisibility(View.VISIBLE);
        try{
            Call<Ecg_Result_Model> call = NetworkManager.getNetworkService(this).postEcgReviewDelete(AppConstant.AUTH_USERNAME,
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

    private void doDialog(String sValue) {
        final Dialog dialog = new Dialog(Ecg_Review.this);
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
        Arymodel_ecg_review = new ArrayList<>();
/*        for(int i = 1; i < 10 ; i++){
            model_ecg_review = new Model_ecg_review();
            model_ecg_review.setAtr1("02 / 01 / 2015");
            model_ecg_review.setAtr2("9:37 PM");
            model_ecg_review.setAtr3("Submitted");
            model_ecg_review.setAtr4("1");
            if ((i%3)  == 0){
                model_ecg_review.setAtr3("Reviewed");
                model_ecg_review.setAtr4("");
            }

            Arymodel_ecg_review.add(model_ecg_review);
        }*/

        isLoading = true;
        layoutLoading.setVisibility(View.VISIBLE);
        ecgResultModel = null;
        try{
            Call<Ecg_Result_Model> call = NetworkManager.getNetworkService(this).getEcgReview(AppConstant.AUTH_USERNAME);
            call.enqueue(new Callback<Ecg_Result_Model>() {
                @Override
                public void onResponse(Call<Ecg_Result_Model> call, Response<Ecg_Result_Model> response) {
                    int code = response.code();
                    isLoading = false;
                    layoutLoading.setVisibility(View.GONE);
                    if (code == 200){
                        ecgResultModel = response.body();
                        if (!ecgResultModel.error){
                            FillAdapter();
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

    void FillAdapter(){
        mAdapter = new AdapterEcgReview(getBaseContext(), ecgResultModel, new AdapterEcgReview.OnDownloadClicked() {
            @Override
            public void OnDownloadClicked(String sUrl, boolean bStatus) {
                Intent mIntent = new Intent(getBaseContext(), Ecg_Review_PDF.class);
                startActivity(mIntent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case R.id.action_menu:
                //NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
