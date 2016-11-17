package com.unifam.heartpatrol.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.unifam.heartpatrol.AppConstant;
import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.estore.adapter.AdapterEstorePackage;
import com.unifam.heartpatrol.model.Model_Estore_Package;
import com.unifam.heartpatrol.model.Profile;

import java.util.ArrayList;

/**
 * Created by User on 9/16/2016.
 */
public class ProfileViewActivity extends AppCompatActivity {
    ImageView imgBack;
    TextView txtLabel,
            txtName,
            txtBirthDate,
            txtEmail,
            txtEthnicity
            ;

    public  Profile profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        imgBack = (ImageView)findViewById(R.id.arrow_back);
        txtLabel = (TextView)findViewById(R.id.textLabel);
        txtName = (TextView)findViewById(R.id.txtName);
        txtBirthDate = (TextView)findViewById(R.id.txtBirth);
        txtEmail = (TextView)findViewById(R.id.txtEmail);
        txtEthnicity = (TextView)findViewById(R.id.txtEthnicity);

        txtLabel.setText(getResources().getText(R.string.Profile));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtName.setText("Ronald");
        txtBirthDate.setText("1987-11-11");
        txtEmail.setText("test@gmail.com");
        txtEthnicity.setText("");

        /*profile = AppConstant.profile;
        for (Profile.Data dat : profile.data){
            txtName.setText(dat.first_name + " " + dat.last_name);
            txtBirthDate.setText(dat.birth_date);
            txtEmail.setText(dat.email);
            txtEthnicity.setText(dat.gender);
        }*/

    }


}
