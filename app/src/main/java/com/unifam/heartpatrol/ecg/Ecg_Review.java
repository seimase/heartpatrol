package com.unifam.heartpatrol.ecg;

import android.app.Dialog;
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
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.ecg.adapter.AdapterEcgReview;
import com.unifam.heartpatrol.model.Model_ecg_review;
import com.unifam.heartpatrol.ecg.adapter.AdapterEcgResult;

import java.util.ArrayList;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecg_review);

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

                        break;
                    case 1: //Delete
                        int iDelete = 0;
                        for (Model_ecg_review dat: Arymodel_ecg_review){
                            if (dat.getAtrCheck1()) iDelete += 1;
                        }
                        doDialog(Integer.toString(iDelete));
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
        for(int i = 1; i < 10 ; i++){
            model_ecg_review = new Model_ecg_review();
            model_ecg_review.setAtr1("02 / 01 / 2015");
            model_ecg_review.setAtr2("9:37 PM");
            model_ecg_review.setAtr3("Abnormality detected");
            model_ecg_review.setAtr4("1");
            if ((i%3)  == 0){
                model_ecg_review.setAtr3("No abnormality detected");
                model_ecg_review.setAtr4("");
            }

            Arymodel_ecg_review.add(model_ecg_review);
        }

        mAdapter = new AdapterEcgReview(this, Arymodel_ecg_review);
        // set the adapter object to the Recyclerview
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
