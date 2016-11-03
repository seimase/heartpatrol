package com.unifam.heartpatrol.estore.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.unifam.heartpatrol.AppConstant;
import com.unifam.heartpatrol.AppController;
import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.ecg.adapter.AdapterEcgOverRead;
import com.unifam.heartpatrol.estore.EstoreActivity;
import com.unifam.heartpatrol.estore.adapter.AdapterEstoreReview;
import com.unifam.heartpatrol.model.ListData;
import com.unifam.heartpatrol.model.Model_Estore_Review;
import com.unifam.heartpatrol.model.Package;
import com.unifam.heartpatrol.model.Promo;
import com.unifam.heartpatrol.model.net.NetworkManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 9/23/2016.
 */
public class Frag_Estore_Review extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    Model_Estore_Review listData;
    ArrayList<Model_Estore_Review> AryListData;
    TextView txtCredit, txtPay, txtSubtotal, txtPromo, txtTotal, btnApply, txtPromoCode;

    Package aPackage;
    Promo promo;

    long lCredit = 0;
    double dSubtotal = 0;
    double dPromo = 0;
    double dTotal = 0;
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
        txtPromoCode = (EditText)v.findViewById(R.id.txt_product_package_amount);
        btnApply = (TextView)v.findViewById(R.id.btn_Apply);
        txtTotal = (TextView)v.findViewById(R.id.txt_TotalPayment);
        txtPromo = (TextView)v.findViewById(R.id.txt_Promo);
        txtSubtotal = (TextView)v.findViewById(R.id.txt_Subtotal);
        txtCredit = (TextView) v.findViewById(R.id.text_credits);
        txtPay = (TextView)v.findViewById(R.id.btn_pay);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(v.getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);

        txtPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EstoreActivity)getActivity()).nextStep();
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalculatePromo(txtPromoCode.getText().toString().trim());
            }
        });
    }

    void FillGrid(){
        aPackage = AppConstant.EstorePackage;
 /*       AryListData = new ArrayList<>();

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
*/


        for(Package.Datum dat : aPackage.data){
            lCredit += (dat.qty * dat.credits);
            dSubtotal += dat.qty * dat.price;
        }

        dTotal = dSubtotal - dPromo;
        txtCredit.setText(Long.toString(lCredit));

        txtSubtotal.setText(AppController.toCurrency(dSubtotal));
        txtPromo.setText(AppController.toCurrency(dPromo));
        txtTotal.setText(AppController.toCurrency(dTotal));

        mAdapter = new AdapterEstoreReview(getActivity(), aPackage);
        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);
    }

    void CalculatePromo(String sPromoCode){
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "Information", "Calculate promo", true);
        try{
            Call<Promo> call = NetworkManager.getNetworkService(getActivity()).getPromo(sPromoCode);
            call.enqueue(new Callback<Promo>() {
                @Override
                public void onResponse(Call<Promo> call, Response<Promo> response) {
                    int code = response.code();
                    progressDialog.dismiss();
                    if (code == 200){
                        promo = response.body();
                        if (!promo.error){
                            for(Promo.Datum dat : promo.data){
                                if (dat.promo_type.equals("1")){
                                    dPromo = (dSubtotal * dat.percent) / 100 ;
                                }else{
                                    dPromo = dSubtotal - dat.value ;
                                }
                            }

                            dTotal = dSubtotal - dPromo;
                            txtCredit.setText(Long.toString(lCredit));

                            txtSubtotal.setText(AppController.toCurrency(dSubtotal));
                            txtPromo.setText(AppController.toCurrency(dPromo));
                            txtTotal.setText(AppController.toCurrency(dTotal));
                        }
                    }
                }

                @Override
                public void onFailure(Call<Promo> call, Throwable t) {
                    progressDialog.dismiss();
                }
            });
        }catch (Exception e){
            progressDialog.dismiss();
        }
    }
}
