package potrait;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.ecg.adapter.AdapterEcgOverRead;
import com.unifam.heartpatrol.model.ListData;

import java.util.ArrayList;

import potrait.adapter.AdapterPotraitMainMenu;

/**
 * Created by setia.n on 10/11/2016.
 */

public class Potrait_MainMenuActivity extends AppCompatActivity {
    AHBottomNavigation bottomNavigation;
    ListData listData;
    ArrayList<ListData> AryListData;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private GridLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.potrait_mainmenu);
        //InitControl();
        //FillGrid();

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Profile", R.drawable.uff_profile, R.color.colorAccent);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("ECG Result", R.drawable.uff_ecg_result, R.color.colorAccent);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("ECG Review", R.drawable.uff_ecg_review, R.color.colorAccent);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Store", R.drawable.uff_store, R.color.colorAccent);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem("Transaction", R.drawable.uff_transaction, R.color.colorAccent);
        AHBottomNavigationItem item6 = new AHBottomNavigationItem("Setting", R.drawable.uff_setting, R.color.colorAccent);
        // Add items


        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item5);

        bottomNavigation.setForceTitlesDisplay(true);
        bottomNavigation.setSelected(false);
    }

    void InitControl() {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 3);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    void FillGrid(){
        AryListData = new ArrayList<>();

        listData = new ListData();
        listData.setAtr1("Profile");
        listData.setImg(R.drawable.uff_profile);
        AryListData.add(listData);

        listData = new ListData();
        listData.setAtr1("ECG Result");
        listData.setImg(R.drawable.uff_ecg_result);
        AryListData.add(listData);

        listData = new ListData();
        listData.setAtr1("ECG Review");
        listData.setImg(R.drawable.uff_ecg_review);
        AryListData.add(listData);

        listData = new ListData();
        listData.setAtr1("Store");
        listData.setImg(R.drawable.uff_store);
        AryListData.add(listData);

        listData = new ListData();
        listData.setAtr1("Transaction");
        listData.setImg(R.drawable.uff_transaction);
        AryListData.add(listData);

        listData = new ListData();
        listData.setAtr1("Setting");
        listData.setImg(R.drawable.uff_setting);
        AryListData.add(listData);


        mAdapter = new AdapterPotraitMainMenu(this, AryListData);
        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);
    }
}
