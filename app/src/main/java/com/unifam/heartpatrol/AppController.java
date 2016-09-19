package com.unifam.heartpatrol;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by User on 9/15/2016.
 */
public class AppController extends Application {
    private static AppController mInstance;
    private SessionManager sessionManager;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        sessionManager = new SessionManager(getApplicationContext());

    }

    public static Context getAppContext() {
        return mInstance;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static String getStringData(Context ctx, String sData){
        String sResult = "";
        sResult = AppConstant.pref.getString(sData, AppConstant.EMPTY_STRING);
        return  sResult;
    }

    public void displayImage(Context context,String uri, ImageView imageView) {
        Picasso picasso = Picasso.with(context);
        picasso.setIndicatorsEnabled(false);
        picasso.load(uri)
                .into(imageView);
    }

    public static String toCurrency(double dValue) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        String sCur = nf.format(dValue);
        //sCur = sCur.replace("$", "Rp ");
        sCur = sCur.replace("$", "");
        sCur = sCur.replace(".00", "");
        return sCur;
    }

    public static String toCurrency1(double dValue) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        String sCur = nf.format(dValue);
        sCur = sCur.replace("$", "");
        sCur = sCur.replace(".00", "");
        return sCur;
    }

    public static String toCurrencyRp(double dValue) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        String sCur = nf.format(dValue);
        sCur = sCur.replace("$", "Rp ");

        sCur = sCur.replace(".00", "");
        return sCur;
    }
}
