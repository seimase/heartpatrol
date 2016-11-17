package com.unifam.heartpatrol.ecg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.model.Ecg_Result_Model;
import com.unifam.heartpatrol.model.Model_ecg_review;
import com.unifam.heartpatrol.model.model_ecg_result;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by Unifam on 9/19/2016.
 */
public class AdapterEcgReview_Dummy extends  RecyclerView.Adapter<AdapterEcgReview_Dummy.ViewHolder>{
    ArrayList<Model_ecg_review> ecgResultModel;
    private Context context;
    public int mSelectedItem = -1;

    public interface OnDownloadClicked {
        public void OnDownloadClicked(String sUrl, boolean bStatus);
    }

    private OnDownloadClicked listener;

    public AdapterEcgReview_Dummy(Context context, ArrayList<Model_ecg_review> ecgResultModel, OnDownloadClicked listener) {
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
        //Set text
        holder.txtDate.setText(ecgResultModel.get(position).getAtr1());
        holder.txtTime.setText(ecgResultModel.get(position).getAtr2());
        holder.txtDescription.setText(ecgResultModel.get(position).getAtr3());

        holder.smoothCheckBox.setChecked(false);

        holder.smoothCheckBox.setChecked(ecgResultModel.get(position).getAtrCheck1());

        holder.btnPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnDownloadClicked("http://unec.edu.az/application/uploads/2014/12/pdf-sample.pdf", true);
            }
        });
        holder.listData = ecgResultModel;
    }

    @Override
    public int getItemCount() {
        return ecgResultModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        TextView txtDate,
                txtTime,
                txtDescription;

        ImageView btnPDF;

        ArrayList<Model_ecg_review> listData;
        final SmoothCheckBox smoothCheckBox;
        public ViewHolder(View itemView,
                          Context context,
                          final AdapterEcgReview_Dummy mCourseAdapter) {
            super(itemView);
            smoothCheckBox = (SmoothCheckBox)itemView.findViewById(R.id.scb);
            txtTime = (TextView)itemView.findViewById(R.id.textTime);
            txtDate = (TextView)itemView.findViewById(R.id.textDate);
            txtDescription = (TextView)itemView.findViewById(R.id.textDescription);
            btnPDF = (ImageView)itemView.findViewById(R.id.btn_pdf);

            smoothCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (smoothCheckBox.isChecked()){
                        ecgResultModel.get(getAdapterPosition()).setAtrCheck1(false);
                        smoothCheckBox.setChecked(false);
                    }else{
                        ecgResultModel.get(getAdapterPosition()).setAtrCheck1(true);
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
