package com.unifam.heartpatrol.ecg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.model.Ecg_Result_Model;
import com.unifam.heartpatrol.model.model_ecg_result;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by Unifam on 9/19/2016.
 */
public class AdapterEcgResult_Dummy extends  RecyclerView.Adapter<AdapterEcgResult_Dummy.ViewHolder>{

    ArrayList<model_ecg_result> listdata;
    private Context context;
    public int mSelectedItem = -1;



    public interface OnDownloadClicked {
        public void OnDownloadClicked(String sUrl, boolean bStatus);
    }

    private AdapterEcgResult_Dummy.OnDownloadClicked listener;

    public AdapterEcgResult_Dummy(Context context, ArrayList<model_ecg_result> listdata, OnDownloadClicked listener) {
        this.context = context;
        this.listdata = listdata;
        this.listener = listener;
        if (listdata == null) {
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
        //Set text
        holder.txtDate.setText(listdata.get(position).getAtr1());
        holder.txtTime.setText(listdata.get(position).getAtr2());
        holder.txtDescription.setText(listdata.get(position).getAtr3());

        holder.layoutBar.setBackgroundColor(context.getResources().getColor(R.color.red));
        if (listdata.get(position).getAtr3().equals("No abnormality Detected")){
            holder.layoutBar.setBackgroundColor(context.getResources().getColor(R.color.Green_atena));
        }

        holder.smoothCheckBox.setChecked(listdata.get(position).getAtrCheck1());
        //holder.smoothCheckBox.setChecked(position == mSelectedItem);

        holder.btnPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnDownloadClicked("http://unec.edu.az/application/uploads/2014/12/pdf-sample.pdf", true);
            }
        });


        holder.listData = listdata;
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        TextView txtDate,
                txtTime,
                txtDescription;

        RelativeLayout layoutBar;

        ImageView btnPDF;

        ArrayList<model_ecg_result> listData;
        final SmoothCheckBox smoothCheckBox;
        public ViewHolder(View itemView,
                          Context context,
                          final AdapterEcgResult_Dummy mCourseAdapter) {
            super(itemView);
            layoutBar = (RelativeLayout)itemView.findViewById(R.id.relativeLayout4);
            smoothCheckBox = (SmoothCheckBox)itemView.findViewById(R.id.scb);
            txtTime = (TextView)itemView.findViewById(R.id.textTime);
            txtDate = (TextView)itemView.findViewById(R.id.textDate);
            txtDescription = (TextView)itemView.findViewById(R.id.textDescription);
            btnPDF = (ImageView)itemView.findViewById(R.id.btn_pdf);

            smoothCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (smoothCheckBox.isChecked()){
                        listdata.get(getAdapterPosition()).setAtrCheck1(false);
                        smoothCheckBox.setChecked(false);
                    }else{
                        listdata.get(getAdapterPosition()).setAtrCheck1(true);
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
