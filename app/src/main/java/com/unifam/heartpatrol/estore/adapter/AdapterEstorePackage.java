package com.unifam.heartpatrol.estore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.model.Model_Estore_Package;
import com.unifam.heartpatrol.model.Model_ecg_result;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by Unifam on 9/19/2016.
 */
public class AdapterEstorePackage extends  RecyclerView.Adapter<AdapterEstorePackage.ViewHolder>{

    ArrayList<Model_Estore_Package> mCourseArrayList;
    private Context context;
    public int mSelectedItem = -1;

    /*private OnCheckBoxClicked listener;

    public interface OnBarcodeClicked {
        public void OnBarcodeClicked(String sKode, boolean bCamera, boolean bSave);
    }*/

    public AdapterEstorePackage(Context context, ArrayList<Model_Estore_Package> mCourseArrayList) {
        this.context = context;
        this.mCourseArrayList = mCourseArrayList;
        if (mCourseArrayList == null) {
            throw new IllegalArgumentException("courses ArrayList must not be null");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.row_estore_package, null);

        return new ViewHolder(itemView, context, this);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Model_Estore_Package listData = mCourseArrayList.get(position);
        //Set text
        holder.txtPackage.setText(listData.getAtr1());
        holder.txtDescription.setText(listData.getAtr2());
        holder.txtPrice.setText(listData.getAtr3());

        if ((position % 2) ==0){
            holder.layoutBar.setBackgroundColor(context.getResources().getColor(R.color.grey_ss));
        }else{
            holder.layoutBar.setBackgroundColor(context.getResources().getColor(R.color.white));
        }


        holder.btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(listData.getAtrAmount() > 0){
                    listData.setAtrAmount(listData.getAtrAmount() - 1);
                    holder.txtValue.setText(Integer.toString(listData.getAtrAmount() ));
                }
                //setEstimatedCost(holder, item);
            }
        });
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listData.setAtrAmount(listData.getAtrAmount() + 1);
                holder.txtValue.setText(Integer.toString(listData.getAtrAmount()));
                //setEstimatedCost(holder, item);
            }
        });


        holder.listData = listData;
    }

    @Override
    public int getItemCount() {
        return mCourseArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        TextView txtPackage,
                txtDescription,
                txtPrice,
                btnPlus,
                btnMin
                        ;

        EditText txtValue;

        LinearLayout layoutBar;

        Model_Estore_Package listData;
        public ViewHolder(View itemView,
                          Context context,
                          final AdapterEstorePackage mCourseAdapter) {
            super(itemView);
            layoutBar = (LinearLayout)itemView.findViewById(R.id.layout_bar);
            txtPackage = (TextView)itemView.findViewById(R.id.textPackage);
            txtDescription = (TextView)itemView.findViewById(R.id.textDescription);
            txtPrice = (TextView)itemView.findViewById(R.id.textPrice);
            btnMin = (TextView)itemView.findViewById(R.id.btn_product_package_min);
            btnPlus = (TextView)itemView.findViewById(R.id.btn_product_package_plus);
            txtValue = (EditText) itemView.findViewById(R.id.txt_product_package_amount);



        }


        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
