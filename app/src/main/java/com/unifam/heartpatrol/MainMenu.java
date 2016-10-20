package com.unifam.heartpatrol;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alexzh.circleimageview.CircleImageView;
import com.alexzh.circleimageview.ItemSelectedListener;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.unifam.heartpatrol.ecg.Ecg_Review;
import com.unifam.heartpatrol.ecg.ecg_recording;
import com.unifam.heartpatrol.ecg.ecg_result;
import com.unifam.heartpatrol.estore.EstoreActivity;
import com.unifam.heartpatrol.model.Profile;
import com.unifam.heartpatrol.model.Register;
import com.unifam.heartpatrol.model.net.NetworkManager;
import com.unifam.heartpatrol.profile.ProfileActivity;
import com.unifam.heartpatrol.profile.ProfileViewActivity;
import com.unifam.heartpatrol.transaction.TransactionActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Unifam on 9/15/2016.
 */
public class MainMenu extends AppCompatActivity {
    AHBottomNavigation bottomNavigation;
    ImageView imgSetting;
    ImageView imgRecord;
    TextView txtName;

    Profile profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        InitControl();

        Register register = AppController.getInstance().getSessionManager().getUserProfile();

        if (register !=null){
            AppConstant.AUTH_USERNAME = register.name;
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
                        final ProgressDialog progress;
                        progress = ProgressDialog.show(getBaseContext(), "Information",
                                "Get data", true);
                        progress.show();

                        try{
                            Call<Profile> call = NetworkManager.getNetworkService(getBaseContext()).getProfile(AppConstant.AUTH_USERNAME);
                            call.enqueue(new Callback<Profile>() {
                                @Override
                                public void onResponse(Call<Profile> call, Response<Profile> response) {
                                    int code = response.code();
                                    profile = response.body();
                                    progress.dismiss();
                                    if (!profile.error){
                                        if (profile.data.size() > 0){
                                            Intent mIntent = new Intent(getBaseContext(),ProfileViewActivity.class);
                                            AppConstant.profile = profile;
                                            startActivity(mIntent);
                                        }else{
                                            Intent mIntent = new Intent(getBaseContext(),ProfileActivity.class);
                                            startActivity(mIntent);
                                        }
                                    }else{
                                        AppController.getInstance().CustomeDialog(getBaseContext(),profile.message);
                                    }
                                }

                                @Override
                                public void onFailure(Call<Profile> call, Throwable t) {
                                    progress.dismiss();
                                }
                            });
                        }catch (Exception e){
                            AppController.getInstance().CustomeDialog(getBaseContext(), e.getMessage());
                            progress.dismiss();
                        }
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
        if (AppConstant.bExit){
            Register register = null;
            AppController.getInstance().getSessionManager().setUserAccount(register);
            finish();
        }
    }
}
