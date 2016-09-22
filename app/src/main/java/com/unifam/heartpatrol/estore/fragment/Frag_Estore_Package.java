package com.unifam.heartpatrol.estore.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unifam.heartpatrol.AppController;
import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.estore.adapter.AdapterEstorePackage;
import com.unifam.heartpatrol.model.Model_Estore_Package;

import java.util.ArrayList;

/**
 * Created by Unifam on 9/22/2016.
 */
public class Frag_Estore_Package extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    ArrayList<Model_Estore_Package> AryListData;
    Model_Estore_Package model_estore_package;
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
        mRecyclerView = (RecyclerView)v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(v.getContext());
        // use a linear layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    void FillGrid(){
        AryListData = new ArrayList<>();
        for(int i = 1; i < 8 ; i++){
            model_estore_package = new Model_Estore_Package();
            model_estore_package.setAtr1("Package " + i);
            model_estore_package.setAtr2((10 * i) + " Credits");
            model_estore_package.setAtr3("IDR " +  AppController.toCurrency(100000 * i));
            model_estore_package.setAtrAmount(0);
            AryListData.add(model_estore_package);
        }

        mAdapter = new AdapterEstorePackage(getActivity(),AryListData);
        mRecyclerView.setAdapter(mAdapter);
    }
}
