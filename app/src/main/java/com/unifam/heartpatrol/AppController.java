package com.unifam.heartpatrol;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by User on 9/15/2016.
 */
public class AppController extends Application {
    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }

    public static Context getAppContext() {
        return mInstance;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void displayImage(Context context,String uri, ImageView imageView) {
        Picasso picasso = Picasso.with(context);
        picasso.setIndicatorsEnabled(false);
        picasso.load(uri)
                .into(imageView);
    }
}
