package com.unifam.heartpatrol.ecg;

import android.app.DownloadManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.ayz4sci.androidfactory.DownloadProgressView;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.unifam.heartpatrol.AppConstant;
import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.model.Model_ecg_review;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by User on 9/26/2016.
 */
public class Ecg_Review_PDF extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener {

    ImageView imgBack;
    TextView txtLabel;
    AHBottomNavigation bottomNavigation;
    PDFView pdfView;
    Integer pageNumber = 0;

    RelativeLayout rLayoutDownload;
    DownloadProgressView downloadProgressView;
    private long downloadID;
    private DownloadManager downloadManager;
    Uri uri;
    String sUrl = "http://www.khazanah.com.my/khazanah/files/20/200f21f3-07ff-4903-ab99-7c0cb557eb51.pdf";
    String pdfFileName = "" ;
    File folder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecg_review_pdf);
        InitControl();
        DownloadPDF();
    }

    void InitControl(){
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        pdfView = (PDFView)findViewById(R.id.pdfView);
        imgBack = (ImageView)findViewById(R.id.arrow_back);
        txtLabel = (TextView)findViewById(R.id.textLabel);
        rLayoutDownload = (RelativeLayout)findViewById(R.id.layout_download);
        downloadProgressView = (DownloadProgressView) findViewById(R.id.downloadProgressView);
        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

        txtLabel.setText(getResources().getText(R.string.ecg_review));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Email", R.drawable.ic_email_2, R.color.colorAccent);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Print", R.drawable.uff_print, R.color.colorAccent);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Save PDF", R.drawable.uff_save, R.color.colorAccent);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        bottomNavigation.setForceTitlesDisplay(true);
        bottomNavigation.setSelected(false);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                Intent mIntent;
                switch (position){

                    case 0: //Email ECG Result
                            finish();
                        break;
                    case 1: //Print
                        //CustomAlertDialogBuilder builder = new CustomAlertDialogBuilder(getActivity(), getResources().getColor(R.color.green_xxl));
                        break;
                    case 2: //Download
                        DownloadPDF();
                        break;
                }
                return true;
            }
        });
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override public void onPositionChange(int y) {
                // Manage the new y position
            }
        });
    }

    @Override
    public void loadComplete(int nbPages) {

    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
    }


    void DownloadPDF(){
        pdfView.setVisibility(View.GONE);
        rLayoutDownload.setVisibility(View.VISIBLE);

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(sUrl));
        request.setTitle("TITLE");
        request.setDescription("DESCRIPTION");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(AppConstant.STORAGE_CARD_DOWNLOAD,  "sample_pdf.pdf");
        request.allowScanningByMediaScanner();
        downloadID = downloadManager.enqueue(request);


        downloadProgressView.show(downloadID, new DownloadProgressView.DownloadStatusListener() {
            @Override
            public void downloadFailed(int reason) {
                //Action to perform when download fails, reason as returned by DownloadManager.COLUMN_REASON
                pdfView.setVisibility(View.VISIBLE);
                rLayoutDownload.setVisibility(View.GONE);
            }

            @Override
            public void downloadSuccessful() {
                //Action to perform on success
                pdfView.setVisibility(View.VISIBLE);
                rLayoutDownload.setVisibility(View.GONE);

                DisplayPDF(AppConstant.STORAGE_CARD + "/Download/sample_pdf.pdf");
            }

            @Override
            public void downloadCancelled() {
                //Action to perform when user press the cancel button
                pdfView.setVisibility(View.VISIBLE);
                rLayoutDownload.setVisibility(View.GONE);
            }
        });
    }

    void DisplayPDF(String sPath){
        File file = new File(sPath);
        if (file.exists()){
            pdfView.fromFile(file)
                    .defaultPage(pageNumber)
                    .onPageChange(this)
                    .enableAnnotationRendering(true)
                    .onLoad(this)
                    .scrollHandle(new DefaultScrollHandle(this))
                    .load();

            pdfView.setMinZoom(10);
        }

    }
}
