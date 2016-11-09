package com.unifam.heartpatrol.register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.unifam.heartpatrol.AppConstant;
import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.model.Register;
import com.unifam.heartpatrol.model.net.NetworkManager;
import com.unifam.heartpatrol.register.adapter.AdapterListUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by setia.n on 11/8/2016.
 */

public class ListUserActivity extends AppCompatActivity {

    ImageView imgBack;
    TextView txtLabel;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    public Register register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        InitControl();
        register = AppConstant.register;
        //FillGrid();
        FillAdapter();
    }

    void InitControl(){
        imgBack = (ImageView) findViewById(R.id.arrow_back);
        txtLabel = (TextView) findViewById(R.id.textLabel);

        txtLabel.setText(getResources().getText(R.string.ListUser));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    void FillGrid(){
        try{
            Call<Register> call = NetworkManager.getNetworkService(this).getListUser(AppConstant.DEVICE_ID);
            call.enqueue(new Callback<Register>() {
                @Override
                public void onResponse(Call<Register> call, Response<Register> response) {
                    int code = response.code();
                    if (code == 200){
                        register = response.body();
                        if (!register.error){
                            FillAdapter();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Register> call, Throwable t) {

                }
            });
        }catch (Exception e){

        }
    }

    void FillAdapter(){
        mAdapter = new AdapterListUser(this, register, new AdapterListUser.OnDownloadClicked() {
            @Override
            public void OnDownloadClicked(String sUserName) {
                AppConstant.USER_FROM_LIST = sUserName;
                finish();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }
}
