package com.unifam.heartpatrol.ecg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.model.Ecg_Result_Model;
import com.unifam.heartpatrol.model.ListData;

import java.util.ArrayList;

/**
 * Created by Unifam on 9/19/2016.
 */
public class AdapterEcgOverRead extends  RecyclerView.Adapter<AdapterEcgOverRead.ViewHolder>{
    Ecg_Result_Model ecgResultModel;
    private Context context;

    public AdapterEcgOverRead(Context context, Ecg_Result_Model ecgResultModel, OnDeleteClicked listener) {
        this.context = context;
        this.ecgResultModel = ecgResultModel;
        this.listener = listener;
        if (ecgResultModel == null) {
            throw new IllegalArgumentException("courses ArrayList must not be null");
        }
    }

    public interface OnDeleteClicked {
        public void OnDeleteClicked(int position);
    }

    private OnDeleteClicked listener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.row_ecg_over_read, null);

        return new ViewHolder(itemView, context, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Ecg_Result_Model.Data listData = ecgResultModel.data.get(position);
        //Set text
        holder.txtDescription.setText("Report " + listData.ecg_date);
        holder.txtCredits.setText("10");

        if ((position % 2) ==0){
            holder.layoutDelete.setBackgroundColor(context.getResources().getColor(R.color.grey_ss));
            holder.txtDescription.setBackgroundColor(context.getResources().getColor(R.color.grey_ss));
        }else{
            holder.layoutDelete.setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.txtDescription.setBackgroundColor(context.getResources().getColor(R.color.white));
        }

        holder.layoutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnDeleteClicked(position);
            }
        });

        holder.listData = listData;
    }

    @Override
    public int getItemCount() {
        return ecgResultModel.data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        TextView txtDescription,
                txtCredits;

        Ecg_Result_Model.Data listData;

        RelativeLayout layoutDelete;
        public ViewHolder(View itemView,
                          Context context,
                          final AdapterEcgOverRead mCourseAdapter) {
            super(itemView);
            layoutDelete = (RelativeLayout)itemView.findViewById(R.id.layout_delete);
            txtDescription = (TextView)itemView.findViewById(R.id.textDescription);
            txtCredits = (TextView)itemView.findViewById(R.id.text_credits);
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
