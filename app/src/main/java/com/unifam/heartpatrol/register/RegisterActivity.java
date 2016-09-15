package com.unifam.heartpatrol.register;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.register.fragment.Frag_Register_Done;
import com.unifam.heartpatrol.register.fragment.Frag_Register_Password;
import com.unifam.heartpatrol.register.fragment.Frag_Register_With;

/**
 * Created by Unifam on 9/15/2016.
 */
public class RegisterActivity extends AppCompatActivity{

    ImageView imgBack;
    TextView txtLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        imgBack = (ImageView)findViewById(R.id.arrow_back);
        txtLabel = (TextView)findViewById(R.id.textLabel);

        txtLabel.setText(getResources().getText(R.string.Register));
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
                fragment = new Frag_Register_With();
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
