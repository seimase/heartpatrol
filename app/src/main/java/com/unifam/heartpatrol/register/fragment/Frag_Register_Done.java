package com.unifam.heartpatrol.register.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.unifam.heartpatrol.AppConstant;
import com.unifam.heartpatrol.AppController;
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
    EditText txtFirst, txtLast;
    ProgressDialog progress;

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
        txtFirst = (EditText)v.findViewById(R.id.edt_first);
        txtLast = (EditText)v.findViewById(R.id.edt_last);
        txtDone = (TextView)v.findViewById(R.id.btn_done);
        txtDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = ProgressDialog.show(getActivity(), "Information",
                        "Registration", true);
                progress.show();

                AppConstant.AUTH_USERNAME = txtFirst.getText().toString().trim();
                getActivity().finish();
                Intent intent = new Intent(getActivity(), MainMenu.class);
                startActivity(intent);

                /*try{
                    Call<Register> call = NetworkManager.getNetworkService(getActivity()).getRegister(
                            AppConstant.AUTH_USERNAME,
                            AppConstant.AUTH_PASSWORD,
                            AppConstant.AUTH_USERNAME,
                            txtFirst.getText().toString().trim(),
                            txtLast.getText().toString().trim(),
                            AppConstant.DEVICE_ID,
                            AppConstant.TOKEN);
                    call.enqueue(new Callback<Register>() {
                        @Override
                        public void onResponse(Call<Register> call, Response<Register> response) {
                            int code = response.code();
                            register = response.body();
                            progress.dismiss();
                            if (code == 200){
                                if (!register.error){
                                    AppController.getInstance().getSessionManager().setUserAccount(register);
                                    getActivity().finish();
                                    Intent intent = new Intent(getActivity(), MainMenu.class);
                                    startActivity(intent);
                                }else{
                                    AppController.getInstance().CustomeDialog(getActivity(),register.message);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Register> call, Throwable t) {
                            progress.dismiss();
                        }
                    });
                }catch (Exception e){
                    progress.dismiss();
                    AppController.getInstance().CustomeDialog(getActivity(), e.getMessage());
                }*/

            }
        });
    }
}
