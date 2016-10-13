package com.unifam.heartpatrol.register.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.unifam.heartpatrol.MainMenu;
import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.model.LocationList;
import com.unifam.heartpatrol.model.Register;
import com.unifam.heartpatrol.model.net.NetworkManager;
import com.unifam.heartpatrol.register.RegisterActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Unifam on 9/15/2016.
 */
public class Frag_Register_Done extends Fragment {
    TextView txtDone;
    Register register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_register_done, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        InitControl(view);
    }

    void InitControl(View v){
        txtDone = (TextView)v.findViewById(R.id.btn_done);
        txtDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        try{
            Call<Register> call = NetworkManager.getNetworkService(getActivity()).getRegister("Setia",
                    "1",
                    "email",
                    "setia",
                    "nugraha");
            call.enqueue(new Callback<Register>() {
                @Override
                public void onResponse(Call<Register> call, Response<Register> response) {
                    int code = response.code();
                    register = response.body();

                    if (code == 200){
                        if (!register.error){
                            getActivity().finish();
                            Intent intent = new Intent(getActivity(), MainMenu.class);
                            startActivity(intent);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Register> call, Throwable t) {

                }
            });
        }catch (Exception e){
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

            }
        });
    }
}
