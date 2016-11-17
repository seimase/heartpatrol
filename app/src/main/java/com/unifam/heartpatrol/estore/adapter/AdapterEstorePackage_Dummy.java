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
import com.unifam.heartpatrol.model.Model_Estore_Package;
import com.unifam.heartpatrol.model.Package;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Unifam on 9/19/2016.
 */
public class AdapterEstorePackage_Dummy extends  RecyclerView.Adapter<AdapterEstorePackage_Dummy.ViewHolder>{
    ArrayList<Model_Estore_Package> listData;
    private Context context;
    public int mSelectedItem = -1;

    public AdapterEstorePackage_Dummy(Context context, ArrayList<Model_Estore_Package> model_estore_package, OnQtyClicked listener) {
        this.context = context;
        this.listData = model_estore_package;
        this.listener = listener;
        if (model_estore_package == null) {
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
        //Set text
        holder.txtPackage.setText(listData.get(position).getAtr1());
        holder.txtDescription.setText(listData.get(position).getAtr2());
        holder.txtPrice.setText(listData.get(position).getAtr3());

        if ((position % 2) ==0){
            holder.layoutBar.setBackgroundColor(context.getResources().getColor(R.color.grey_ss));
        }else{
            holder.layoutBar.setBackgroundColor(context.getResources().getColor(R.color.white));
        }


        holder.btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(listData.get(position).getAtrAmount() > 0){
                    listData.get(position).setAtrAmount(listData.get(position).getAtrAmount() - 1);
                    holder.txtValue.setText(Integer.toString(listData.get(position).getAtrAmount() ));

                    listener.OnQtyClicked();
                }
                //setEstimatedCost(holder, item);
            }
        });
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listData.get(position).setAtrAmount(listData.get(position).getAtrAmount() + 1);
                holder.txtValue.setText(Integer.toString(listData.get(position).getAtrAmount() ));
                listener.OnQtyClicked();
                //setEstimatedCost(holder, item);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
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
                          final AdapterEstorePackage_Dummy mCourseAdapter) {
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
