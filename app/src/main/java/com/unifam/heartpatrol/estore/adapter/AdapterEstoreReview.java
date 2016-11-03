package com.unifam.heartpatrol.estore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.model.ListData;
import com.unifam.heartpatrol.model.Model_Estore_Review;
import com.unifam.heartpatrol.model.Package;

import java.util.ArrayList;

/**
 * Created by Unifam on 9/19/2016.
 */
public class AdapterEstoreReview extends  RecyclerView.Adapter<AdapterEstoreReview.ViewHolder>{
    Package aPackage;
    private Context context;

    public AdapterEstoreReview(Context context, Package aPackage) {
        this.context = context;
        this.aPackage = aPackage;
        if (aPackage == null) {
            throw new IllegalArgumentException("courses ArrayList must not be null");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.row_estore_review, null);

        return new ViewHolder(itemView, context, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Package.Datum listData = aPackage.data.get(position);
        //Set text
        holder.txtDescription.setText(listData.package_name);
        holder.txtTotal.setText(String.valueOf(listData.qty));
        holder.txtCredits.setText(String.valueOf(listData.credits));

        if ((position % 2) ==0){
            holder.txtDescription.setBackgroundColor(context.getResources().getColor(R.color.grey_ss));
            holder.txtTotal.setBackgroundColor(context.getResources().getColor(R.color.grey_ss));
        }else{
            holder.txtDescription.setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.txtTotal.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
        holder.listData = listData;
    }

    @Override
    public int getItemCount() {
        return aPackage.data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        TextView txtDescription,
                txtTotal,
                txtCredits;

        Package.Datum listData;
        public ViewHolder(View itemView,
                          Context context,
                          final AdapterEstoreReview mCourseAdapter) {
            super(itemView);

            txtDescription = (TextView)itemView.findViewById(R.id.textDescription);
            txtTotal = (TextView)itemView.findViewById(R.id.textTotal);
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
