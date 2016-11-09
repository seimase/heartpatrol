package com.unifam.heartpatrol.register.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.model.Ecg_Result_Model;
import com.unifam.heartpatrol.model.Register;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by Unifam on 9/19/2016.
 */
public class AdapterListUser extends  RecyclerView.Adapter<AdapterListUser.ViewHolder>{

    Register register;
    private Context context;
    public int mSelectedItem = -1;

    public interface OnDownloadClicked {
        public void OnDownloadClicked(String sUserName);
    }

    private AdapterListUser.OnDownloadClicked listener;

    public AdapterListUser(Context context, Register register, OnDownloadClicked listener) {
        this.context = context;
        this.register = register;
        this.listener = listener;
        if (register == null) {
            throw new IllegalArgumentException("courses ArrayList must not be null");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.row_list_user, null);

        return new ViewHolder(itemView, context, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Register.Datum listData = register.data.get(position);
        //Set text
        holder.txtUserName.setText(listData.user_name);
        holder.txtFirstName.setText(listData.first_name);
        holder.txtLastName.setText(listData.last_name);

        if ((position % 2) ==0){
            holder.layoutRow.setBackgroundColor(context.getResources().getColor(R.color.grey_ss));
        }else{
            holder.layoutRow.setBackgroundColor(context.getResources().getColor(R.color.white));
        }

        holder.layoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnDownloadClicked(listData.user_name);
            }
        });
        holder.listData = listData;
    }

    @Override
    public int getItemCount() {
        return register.data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        TextView txtUserName,
                txtFirstName,
                txtLastName;

        Register.Datum listData;

        RelativeLayout layoutRow;
        public ViewHolder(View itemView,
                          Context context,
                          final AdapterListUser mCourseAdapter) {
            super(itemView);
;
            txtUserName = (TextView)itemView.findViewById(R.id.text_username);
            txtFirstName = (TextView)itemView.findViewById(R.id.text_firtname);
            txtLastName = (TextView)itemView.findViewById(R.id.text_lastname);
            layoutRow = (RelativeLayout)itemView.findViewById(R.id.layout_row);
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
