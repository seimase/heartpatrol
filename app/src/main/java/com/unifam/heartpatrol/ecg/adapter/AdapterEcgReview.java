package com.unifam.heartpatrol.ecg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.model.Model_ecg_review;


import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by Unifam on 9/19/2016.
 */
public class AdapterEcgReview extends  RecyclerView.Adapter<AdapterEcgReview.ViewHolder>{


    ArrayList<Model_ecg_review> mCourseArrayList;
    private Context context;
    public int mSelectedItem = -1;

    /*private OnCheckBoxClicked listener;

    public interface OnBarcodeClicked {
        public void OnBarcodeClicked(String sKode, boolean bCamera, boolean bSave);
    }*/

    public AdapterEcgReview(Context context, ArrayList<Model_ecg_review> mCourseArrayList) {
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
                R.layout.row_ecg_review, null);

        return new ViewHolder(itemView, context, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Model_ecg_review listData = mCourseArrayList.get(position);
        //Set text
        holder.txtDate.setText(listData.getAtr1());
        holder.txtTime.setText(listData.getAtr2());
        holder.txtDescription.setText(listData.getAtr3());

        holder.smoothCheckBox.setChecked(false);

        holder.smoothCheckBox.setChecked(listData.getAtrCheck1());
        holder.listData = listData;
    }

    @Override
    public int getItemCount() {
        return mCourseArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        TextView txtDate,
                txtTime,
                txtDescription;

        Model_ecg_review listData;
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
                        mCourseArrayList.get(getAdapterPosition()).setAtrCheck1(false);
                        smoothCheckBox.setChecked(false);
                    }else{
                        mCourseArrayList.get(getAdapterPosition()).setAtrCheck1(true);
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
