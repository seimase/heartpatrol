package com.unifam.heartpatrol;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.unifam.heartpatrol.model.LocationList;
import com.unifam.heartpatrol.model.net.NetworkManager;
import com.unifam.heartpatrol.register.RegisterActivity;

import java.io.File;
import java.util.List;

import potrait.Potrait_MainMenuActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RelativeLayout layoutLogin, layoutRegister;
    List<LocationList> resultsLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitControl();
        accessgallery();

        AppConstant.bExit = false;
        /*if (AppController.getInstance().getSessionManager().isUserLogon()){finish();
            Intent intent = new Intent(MainActivity.this, MainMenu.class);
            startActivity(intent);
        }*/

    }

    void InitControl(){
        layoutLogin = (RelativeLayout)findViewById(R.id.layoutLogin);
        layoutRegister = (RelativeLayout)findViewById(R.id.layoutRegister);

        layoutLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        layoutRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        try{
            Call<List<LocationList>> call = NetworkManager.getNetworkService(this).getLocationList();
            call.enqueue(new Callback<List<LocationList>>() {
                @Override
                public void onResponse(Call<List<LocationList>> call, Response<List<LocationList>> response) {
                    int code = response.code();
                    resultsLocation = response.body();
                }

                @Override
                public void onFailure(Call<List<LocationList>> call, Throwable t) {

                }
            });
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }



    }

    void InitFolder(){

        File folder;
        try{
            folder = new File(AppConstant.STORAGE_CARD);
            if (!folder.exists()) folder.mkdirs();

            folder = new File(AppConstant.STORAGE_CARD + "/Download");
            if (!folder.exists()) folder.mkdirs();
        }catch (Exception e){
            Log.d("Error", e.getMessage());
        }
    }

    private void accessgallery() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, 2900);
            } else {
                /*requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, 2900);
*/
                InitFolder();
            }
        } else{
            InitFolder();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 2900:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    InitFolder();
                }
                break;

        }
    }
}
