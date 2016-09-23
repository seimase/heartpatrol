package com.unifam.heartpatrol.estore.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.ecg.adapter.AdapterEcgOverRead;
import com.unifam.heartpatrol.estore.adapter.AdapterEstoreReview;
import com.unifam.heartpatrol.model.ListData;
import com.unifam.heartpatrol.model.Model_Estore_Review;

import java.util.ArrayList;

/**
 * Created by User on 9/23/2016.
 */
public class Frag_Estore_Review extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    Model_Estore_Review listData;
    ArrayList<Model_Estore_Review> AryListData;
    TextView txtCredit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_estore_review, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        InitControl(view);
        FillGrid();
    }

    void InitControl(View v){
        txtCredit = (TextView) v.findViewById(R.id.text_credits);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(v.getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    void FillGrid(){
        AryListData = new ArrayList<>();

        listData = new Model_Estore_Review();
        long lCredit = 0;
        for(int i = 1; i < 8 ; i++){
            listData = new Model_Estore_Review();
            listData.setAtr1("Package " + i);
            listData.setAtr2("1");
            listData.setAtr3("10");
            lCredit += 10;
            AryListData.add(listData);
        }

        txtCredit.setText(Long.toString(lCredit));

        mAdapter = new AdapterEstoreReview(getActivity(), AryListData);
        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);
    }
}
