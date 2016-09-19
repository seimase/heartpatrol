package com.unifam.heartpatrol.model.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.model.Model_ecg_result;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by Unifam on 9/19/2016.
 */
public class AdapterEcgResult extends  RecyclerView.Adapter<AdapterEcgResult.ViewHolder>{

    private ArrayList<Model_ecg_result> mCourseArrayList;
    private Context context;

    public AdapterEcgResult(Context context, ArrayList<Model_ecg_result> mCourseArrayList) {
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
                R.layout.row_ecg_result, null);

        return new ViewHolder(itemView, context, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Model_ecg_result listData = mCourseArrayList.get(position);
        //Set text
        holder.txtDate.setText(listData.getAtr1());
        holder.txtTime.setText(listData.getAtr2());
        holder.txtDescription.setText(listData.getAtr3());

        holder.layoutBar.setBackgroundColor(context.getResources().getColor(R.color.red));
        if (!listData.getAtr4().equals("")){
            holder.layoutBar.setBackgroundColor(context.getResources().getColor(R.color.Green_atena));
        }

        holder.smoothCheckBox.setChecked(false);
        holder.listData = listData;
    }

    @Override
    public int getItemCount() {
        return mCourseArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        TextView txtDate,
                txtTime,
                txtDescription;

        RelativeLayout layoutBar;

        Model_ecg_result listData;
        SmoothCheckBox smoothCheckBox;
        public ViewHolder(View itemView,
                          Context context,
                          final AdapterEcgResult mCourseAdapter) {
            super(itemView);
            layoutBar = (RelativeLayout)itemView.findViewById(R.id.relativeLayout4);
            smoothCheckBox = (SmoothCheckBox)itemView.findViewById(R.id.scb);
            txtTime = (TextView)itemView.findViewById(R.id.textTime);
            txtDate = (TextView)itemView.findViewById(R.id.textDate);
            txtDescription = (TextView)itemView.findViewById(R.id.textDescription);

            smoothCheckBox.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                    Log.d("SmoothCheckBox", String.valueOf(isChecked));
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
