package com.unifam.heartpatrol.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.profile.fragment.Frag_Profile_Birthdate;
import com.unifam.heartpatrol.register.fragment.Frag_Register_Done;
import com.unifam.heartpatrol.register.fragment.Frag_Register_Password;
import com.unifam.heartpatrol.register.fragment.Frag_Register_With;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

/**
 * Created by Unifam on 9/16/2016.
 */
public class ProfileActivity extends AppCompatActivity {
    ImageView imgBack;
    TextView txtLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imgBack = (ImageView)findViewById(R.id.arrow_back);
        txtLabel = (TextView)findViewById(R.id.textLabel);

        txtLabel.setText(getResources().getText(R.string.Profile));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        displayView(0);
    }

    public void displayView(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Frag_Profile_Birthdate();
                break;
            case 1:
                fragment = new Frag_Register_Password();
                break;
            case 2:
                fragment = new Frag_Register_Done();
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_content, fragment);
            fragmentTransaction.commit();
        }
    }
}
