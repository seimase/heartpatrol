package com.unifam.heartpatrol.estore.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.unifam.heartpatrol.AppConstant;
import com.unifam.heartpatrol.AppController;
import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.estore.EstoreActivity;
import com.unifam.heartpatrol.estore.adapter.AdapterEstorePackage;
import com.unifam.heartpatrol.estore.adapter.AdapterEstorePackage_Dummy;
import com.unifam.heartpatrol.model.Model_Estore_Package;
import com.unifam.heartpatrol.model.Package;
import com.unifam.heartpatrol.model.net.NetworkManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Unifam on 9/22/2016.
 */
public class Frag_Estore_Package extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    ArrayList<Model_Estore_Package> AryListData;
    Model_Estore_Package model_estore_package;

    TextView btnReview, notifCount, txtAmount;
    RelativeLayout layout_loading;
    Package aPackage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_estore_package, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        InitControl(view);
        FillGrid();

    }

    void InitControl(View v){
        notifCount = (TextView)v.findViewById(R.id.badge_notification_1);
        txtAmount = (TextView)v.findViewById(R.id.text_amount);
        layout_loading = (RelativeLayout)v.findViewById(R.id.layout_loading);
        btnReview = (TextView)v.findViewById(R.id.btn_review);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(v.getContext());
        // use a linear layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);

        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int iQty = 0;
                double iAmount = 0;
                AppConstant.EstorePackage = new Package();
                AppConstant.EstorePackage.data = new ArrayList<Package.Datum>();
                int iIndex = 0;
                for (Package.Datum dat : aPackage.data){
                    iQty += dat.qty;
                    iAmount += dat.qty * dat.price;
                    if (dat.qty > 0){
                        AppConstant.EstorePackage.data.add(dat);
                    }
                    iIndex += 1;
                }

                if (iQty == 0 && iAmount == 0){
                    AppController.getInstance().CustomeDialog(getActivity(),"Please select package fisrt, Try Again!");
                }else{
                    //AppConstant.EstorePackage = aPackage;
                    ((EstoreActivity)getActivity()).nextStep();
                }
            }
        });
    }

    void FillGrid(){
        AryListData = new ArrayList<>();
        /*for(int i = 1; i < 8 ; i++){
            model_estore_package = new Model_Estore_Package();
            model_estore_package.setAtr1("Package " + i);
            model_estore_package.setAtr2((10 * i) + " Credits");
            model_estore_package.setAtr3("IDR " +  AppController.toCurrency(100000 * i));
            model_estore_package.setAtrAmount(0);
            AryListData.add(model_estore_package);
        }

        mAdapter = new AdapterEstorePackage(getActivity(),AryListData);
        mRecyclerView.setAdapter(mAdapter);*/


        try{
            btnReview.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.GONE);
            layout_loading.setVisibility(View.VISIBLE);
            Call<Package> call = NetworkManager.getNetworkService(getActivity()).getPackage();
            call.enqueue(new Callback<Package>() {
                @Override
                public void onResponse(Call<Package> call, Response<Package> response) {
                    int code = response.code();
                    if (code == 200){
                        aPackage = response.body();
                        if (!aPackage.error){
                            FillAdapter();
                        }else{
                            AppController.getInstance().CustomeDialog(getActivity(),aPackage.message);
                        }

                        btnReview.setVisibility(View.VISIBLE);
                        mRecyclerView.setVisibility(View.VISIBLE);
                        layout_loading.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<Package> call, Throwable t) {
                    btnReview.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    layout_loading.setVisibility(View.GONE);
                }
            });
        }catch (Exception e){
            btnReview.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
            layout_loading.setVisibility(View.GONE);
        }
    }

    void FillAdapter(){
        mAdapter = new AdapterEstorePackage(getActivity(), aPackage, new AdapterEstorePackage.OnQtyClicked() {
            @Override
            public void OnQtyClicked() {
                int iQty = 0;
                double iAmount = 0;
                for (Package.Datum dat : aPackage.data){
                    iQty += dat.qty;
                    iAmount += dat.qty * dat.price;
                }

                notifCount.setText(String.valueOf(iQty));
                txtAmount.setText(AppController.toCurrencyRp(iAmount));
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    void FillDummy(){
        AryListData = new ArrayList<>();
        for(int i = 1; i < 8 ; i++){
            model_estore_package = new Model_Estore_Package();
            model_estore_package.setAtr1("Package " + i);
            model_estore_package.setAtr2((10 * i) + " Credits");
            model_estore_package.setAtr3("IDR " +  AppController.toCurrency(100000 * i));
            model_estore_package.setAtrAmount(0);
            AryListData.add(model_estore_package);
        }

        mAdapter = new AdapterEstorePackage_Dummy(getActivity(), AryListData, new AdapterEstorePackage_Dummy.OnQtyClicked() {
            @Override
            public void OnQtyClicked() {

            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }
}
