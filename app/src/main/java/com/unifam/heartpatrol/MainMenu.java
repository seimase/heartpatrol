package com.unifam.heartpatrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexzh.circleimageview.CircleImageView;
import com.alexzh.circleimageview.ItemSelectedListener;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.unifam.heartpatrol.ecg.Ecg_Review;
import com.unifam.heartpatrol.ecg.ecg_recording;
import com.unifam.heartpatrol.ecg.ecg_result;
import com.unifam.heartpatrol.estore.EstoreActivity;
import com.unifam.heartpatrol.model.Register;
import com.unifam.heartpatrol.profile.ProfileActivity;
import com.unifam.heartpatrol.transaction.TransactionActivity;

/**
 * Created by Unifam on 9/15/2016.
 */
public class MainMenu extends AppCompatActivity {
    AHBottomNavigation bottomNavigation;
    ImageView imgSetting;
    ImageView imgRecord;
    TextView txtName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        InitControl();

        Register register = AppController.getInstance().getSessionManager().getUserProfile();

        if (register !=null){
            txtName.setText("Hi, " + register.first_name);
        }
    }

    void InitControl(){
        imgSetting = (ImageView)findViewById(R.id.img_setting);
        imgRecord = (ImageView)findViewById(R.id.imageView7);
        txtName = (TextView)findViewById(R.id.text_name);

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Profile", R.drawable.uff_profile, R.color.colorAccent);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("ECG Result", R.drawable.uff_ecg_result, R.color.colorAccent);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("ECG Review", R.drawable.uff_ecg_review, R.color.colorAccent);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Store", R.drawable.uff_store, R.color.colorAccent);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem("Transaction", R.drawable.uff_transaction, R.color.colorAccent);
        AHBottomNavigationItem item6 = new AHBottomNavigationItem("Setting", R.drawable.uff_setting, R.color.colorAccent);
        // Add items


        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item5);

        bottomNavigation.setForceTitlesDisplay(true);
        bottomNavigation.setSelected(false);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                Intent mIntent;
                switch (position){

                    case 0: //Profile
                        mIntent = new Intent(getBaseContext(),ProfileActivity.class);
                        startActivity(mIntent);
                        break;
                    case 1: //ECG Result
                        mIntent = new Intent(getBaseContext(),ecg_result.class);
                        startActivity(mIntent);
                        break;
                    case 2: //ECG Review
                        mIntent = new Intent(getBaseContext(), Ecg_Review.class);
                        startActivity(mIntent);
                        break;
                    case 3://Store
                        mIntent = new Intent(getBaseContext(), EstoreActivity.class);
                        startActivity(mIntent);
                        break;
                    case 4://Transaction
                        mIntent = new Intent(getBaseContext(),TransactionActivity.class);
                        startActivity(mIntent);
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

        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        imgRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, ecg_recording.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (AppConstant.bExit)finish();
    }
}
