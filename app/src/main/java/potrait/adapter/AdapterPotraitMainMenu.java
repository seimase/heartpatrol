package potrait.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.model.ListData;

import java.util.ArrayList;

/**
 * Created by Unifam on 9/19/2016.
 */
public class AdapterPotraitMainMenu extends  RecyclerView.Adapter<AdapterPotraitMainMenu.ViewHolder>{

    private ArrayList<ListData> mCourseArrayList;
    private Context context;

    public AdapterPotraitMainMenu(Context context, ArrayList<ListData> mCourseArrayList) {
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
                R.layout.potrait_row_menu, null);

        return new ViewHolder(itemView, context, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListData listData = mCourseArrayList.get(position);
        //Set text
        holder.txtMenu.setText(listData.getAtr1());
        holder.imgMenu.setImageResource(listData.getImg());
        holder.listData = listData;
    }

    @Override
    public int getItemCount() {
        return mCourseArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        TextView txtMenu;
        ImageView imgMenu;

        ListData listData;
        public ViewHolder(View itemView,
                          Context context,
                          final AdapterPotraitMainMenu mCourseAdapter) {
            super(itemView);

            txtMenu = (TextView)itemView.findViewById(R.id.text_menu);
            imgMenu = (ImageView)itemView.findViewById(R.id.img_menu);
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
