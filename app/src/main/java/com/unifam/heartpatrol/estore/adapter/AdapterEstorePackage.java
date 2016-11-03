package com.unifam.heartpatrol.estore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unifam.heartpatrol.AppController;
import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.model.Package;

import java.util.List;

/**
 * Created by Unifam on 9/19/2016.
 */
public class AdapterEstorePackage extends  RecyclerView.Adapter<AdapterEstorePackage.ViewHolder>{

    private Context context;
    public int mSelectedItem = -1;

    Package aPackage;
    public AdapterEstorePackage(Context context, Package aPackage, OnQtyClicked listener) {
        this.context = context;
        this.aPackage = aPackage;
        this.listener = listener;
        if (aPackage == null) {
            throw new IllegalArgumentException("courses ArrayList must not be null");
        }
    }

    public interface OnQtyClicked {
        public void OnQtyClicked();
    }

    private OnQtyClicked listener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.row_estore_package, null);

        return new ViewHolder(itemView, context, this);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final List<Package.Datum> listData = aPackage.data;
        //Set text
        holder.txtPackage.setText(listData.get(position).package_name);
        holder.txtDescription.setText(Integer.toString(listData.get(position).credits) + " Credits");
        holder.txtPrice.setText(listData.get(position).currency + " " + AppController.toCurrency(listData.get(position).price));

        if ((position % 2) ==0){
            holder.layoutBar.setBackgroundColor(context.getResources().getColor(R.color.grey_ss));
        }else{
            holder.layoutBar.setBackgroundColor(context.getResources().getColor(R.color.white));
        }


        holder.btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(listData.get(position).qty > 0){
                    listData.get(position).qty = listData.get(position).qty - 1;
                    holder.txtValue.setText(Integer.toString(listData.get(position).qty ));

                    listener.OnQtyClicked();
                }
                //setEstimatedCost(holder, item);
            }
        });
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listData.get(position).qty = listData.get(position).qty + 1;
                holder.txtValue.setText(Integer.toString(listData.get(position).qty ));
                listener.OnQtyClicked();
                //setEstimatedCost(holder, item);
            }
        });


        holder.listData = listData;
    }

    @Override
    public int getItemCount() {
        return aPackage.data.size();
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

        List<Package.Datum> listData;
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
