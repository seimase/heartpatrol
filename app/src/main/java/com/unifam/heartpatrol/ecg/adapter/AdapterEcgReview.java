package com.unifam.heartpatrol.ecg.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.ecg.Ecg_Review_PDF;
import com.unifam.heartpatrol.model.Ecg_Result_Model;
import com.unifam.heartpatrol.model.Model_ecg_review;


import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by Unifam on 9/19/2016.
 */
public class AdapterEcgReview extends  RecyclerView.Adapter<AdapterEcgReview.ViewHolder>{
    Ecg_Result_Model ecgResultModel;
    private Context context;
    public int mSelectedItem = -1;

    public interface OnDownloadClicked {
        public void OnDownloadClicked(String sUrl, boolean bStatus);
    }

    private OnDownloadClicked listener;

    public AdapterEcgReview(Context context, Ecg_Result_Model ecgResultModel, OnDownloadClicked listener) {
        this.context = context;
        this.ecgResultModel = ecgResultModel;
        this.listener = listener;
        if (ecgResultModel == null) {
            throw new IllegalArgumentException("courses ArrayList must not be null");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.row_ecg_review, null);

        return new ViewHolder(itemView, context, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Ecg_Result_Model.Data listData = ecgResultModel.data.get(position);
        //Set text
        String sDate[] = listData.ecg_date.split(" ");
        holder.txtDate.setText(sDate[0].trim());
        holder.txtTime.setText(sDate[1].trim());
        holder.txtDescription.setText(listData.status_description);

        holder.smoothCheckBox.setChecked(false);

        holder.smoothCheckBox.setChecked(listData.flag);

        holder.txtDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnDownloadClicked("http://unec.edu.az/application/uploads/2014/12/pdf-sample.pdf", true);
            }
        });
        holder.listData = listData;
    }

    @Override
    public int getItemCount() {
        return ecgResultModel.data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        TextView txtDate,
                txtTime,
                txtDescription;

        Ecg_Result_Model.Data listData;
        final SmoothCheckBox smoothCheckBox;
        public ViewHolder(View itemView,
                          Context context,
                          final AdapterEcgReview mCourseAdapter) {
            super(itemView);
            smoothCheckBox = (SmoothCheckBox)itemView.findViewById(R.id.scb);
            txtTime = (TextView)itemView.findViewById(R.id.textTime);
            txtDate = (TextView)itemView.findViewById(R.id.textDate);
            txtDescription = (TextView)itemView.findViewById(R.id.textDescription);

            smoothCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (smoothCheckBox.isChecked()){
                        ecgResultModel.data.get(getAdapterPosition()).flag = false;
                        smoothCheckBox.setChecked(false);
                    }else{
                        ecgResultModel.data.get(getAdapterPosition()).flag = true;
                        smoothCheckBox.setChecked(true);
                    }

                }
            });



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
