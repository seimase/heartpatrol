package com.unifam.heartpatrol.transaction.Adapter;

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
public class AdapterTransaction extends  RecyclerView.Adapter<AdapterTransaction.ViewHolder>{

    private ArrayList<ListData> mCourseArrayList;
    private Context context;

    public AdapterTransaction(Context context, ArrayList<ListData> mCourseArrayList) {
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
                R.layout.row_transaction, null);

        return new ViewHolder(itemView, context, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListData listData = mCourseArrayList.get(position);
        //Set text
        holder.txtPackage.setText(listData.getAtr1());
        holder.txtDate.setText(listData.getAtr2());
        holder.txtValue.setText(listData.getAtr3());
        holder.txtCurrency.setText(listData.getAtr4());

        holder.listData = listData;
    }

    @Override
    public int getItemCount() {
        return mCourseArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        TextView txtPackage,
                txtDate,
                txtValue,
                txtCurrency;

        ListData listData;
        public ViewHolder(View itemView,
                          Context context,
                          final AdapterTransaction mCourseAdapter) {
            super(itemView);

            txtPackage = (TextView)itemView.findViewById(R.id.textPackage);
            txtDate = (TextView)itemView.findViewById(R.id.textDate);
            txtValue = (TextView)itemView.findViewById(R.id.textValue);
            txtCurrency = (TextView)itemView.findViewById(R.id.textCurrency);
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
