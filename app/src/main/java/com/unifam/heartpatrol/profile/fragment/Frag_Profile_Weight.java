package com.unifam.heartpatrol.profile.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unifam.heartpatrol.AppConstant;
import com.unifam.heartpatrol.AppController;
import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.model.Profile;
import com.unifam.heartpatrol.model.net.NetworkManager;
import com.unifam.heartpatrol.profile.ProfileActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ui.widget.RulerView;

/**
 * Created by User on 9/30/2016.
 */
public class Frag_Profile_Weight extends Fragment {

    RulerView rulerView;
    TextView txtHeight, btnNext;
    Profile profile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_profile_weight, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        InitControl(view);
    }

    void InitControl(View v){
        txtHeight = (TextView)v.findViewById(R.id.text_height);
        rulerView = (RulerView)v.findViewById(R.id.ruler_view);
        btnNext = (TextView)v.findViewById(R.id.btn_next);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postData();
            }
        });

        rulerView.setOnScaleListener(new RulerView.OnScaleListener() {
            @Override
            public void onScaleChanged(int scale) {
                AppConstant.PROFILE_WEIGHT = scale;
                txtHeight.setText(Integer.toString(scale));
            }
        });
    }

    void postData(){
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),"Please wait", "Sending data",true);
        progressDialog.show();
        try{
            Call<Profile> call = NetworkManager.getNetworkService(getActivity()).postUpdateProfile(
                    AppConstant.AUTH_USERNAME,
                    AppConstant.PROFILE_BIRTH_DATE,
                    AppConstant.PROFILE_WEIGHT,
                    AppConstant.PROFILE_HEIGHT,
                    AppConstant.PROFILE_GENDER
            );

            call.enqueue(new Callback<Profile>() {
                @Override
                public void onResponse(Call<Profile> call, Response<Profile> response) {
                    int code = response.code();
                    progressDialog.dismiss();
                    if (code == 200){
                        profile = response.body();
                        if (!profile.error){
                            getActivity().finish();
                        }else{
                            AppController.getInstance().CustomeDialog(getActivity(), profile.message);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Profile> call, Throwable t) {
                    progressDialog.dismiss();
                }
            });
        }catch (Exception e){
            progressDialog.dismiss();
        }
    }
}
