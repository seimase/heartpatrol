package com.unifam.heartpatrol.ecg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.model.ListData;

import java.util.ArrayList;

/**
 * Created by Unifam on 9/19/2016.
 */
public class AdapterEcgOverRead extends  RecyclerView.Adapter<AdapterEcgOverRead.ViewHolder>{

    private ArrayList<ListData> mCourseArrayList;
    private Context context;

    public AdapterEcgOverRead(Context context, ArrayList<ListData> mCourseArrayList) {
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
                R.layout.row_ecg_over_read, null);

        return new ViewHolder(itemView, context, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListData listData = mCourseArrayList.get(position);
        //Set text
        holder.txtDescription.setText(listData.getAtr1());
        holder.txtCredits.setText(listData.getAtr2());

        if ((position % 2) ==0){
            holder.txtDescription.setBackgroundColor(context.getResources().getColor(R.color.grey_ss));
        }else{
            holder.txtDescription.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
        holder.listData = listData;
    }

    @Override
    public int getItemCount() {
        return mCourseArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        TextView txtDescription,
                txtCredits;

        ListData listData;
        public ViewHolder(View itemView,
                          Context context,
                          final AdapterEcgOverRead mCourseAdapter) {
            super(itemView);

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
