package com.unifam.heartpatrol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

/**
 * Created by Unifam on 9/15/2016.
 */
public class MainMenu extends AppCompatActivity {
    AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        InitControl();
    }

    void InitControl(){
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Profile", R.drawable.uff_profile, R.color.colorAccent);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("ECG Result", R.drawable.uff_ecg_result, R.color.colorAccent);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("ECG Review", R.drawable.uff_ecg_review, R.color.colorAccent);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Store", R.drawable.uff_store, R.color.colorAccent);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem("Transacion", R.drawable.uff_transaction, R.color.colorAccent);
        AHBottomNavigationItem item6 = new AHBottomNavigationItem("Setting", R.drawable.uff_setting, R.color.colorAccent);
        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item5);
        bottomNavigation.addItem(item6);

        bottomNavigation.setForceTitlesDisplay(true);
        bottomNavigation.setSelected(false);
    }
}
