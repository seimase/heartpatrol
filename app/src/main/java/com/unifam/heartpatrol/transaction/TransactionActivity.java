package com.unifam.heartpatrol.transaction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.unifam.heartpatrol.AppController;
import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.model.ListData;
import com.unifam.heartpatrol.transaction.Adapter.AdapterTransaction;

import java.util.ArrayList;

/**
 * Created by Unifam on 9/19/2016.
 */
public class TransactionActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListData listData;
    ArrayList<ListData> AryListData;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    ImageView imgBack;
    TextView txtLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        InitControl();
        setSupportActionBar(toolbar);

        FillGrid();
    }

    void InitControl(){
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        imgBack = (ImageView)findViewById(R.id.arrow_back);
        txtLabel = (TextView)findViewById(R.id.textLabel);

        txtLabel.setText(getResources().getText(R.string.Transaction));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    void FillGrid(){
        AryListData = new ArrayList<>();

        listData = new ListData();

        for(int i = 1; i < 10 ; i++){
            listData = new ListData();
            listData.setAtr1("Package " + i);
            listData.setAtr2("25 Jul 2016");
            listData.setAtr3(((AppController) getApplication()).toCurrency(50000 * i));
            listData.setAtr4("IDR");

            AryListData.add(listData);
        }

        mAdapter = new AdapterTransaction(this, AryListData);
        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case R.id.action_home:
                //NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

