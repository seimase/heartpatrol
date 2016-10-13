package com.unifam.heartpatrol;

import android.content.SharedPreferences;
import android.os.Environment;

/**
 * Created by Unifam on 9/16/2016.
 */
public final class AppConstant {
    // Shared Preferences
    public static SharedPreferences pref;

    // Editor for Shared preferences
    public static SharedPreferences.Editor editor;
    public static final String LANGUAGE = "language";
    public static final String SESSION_PREFERENCE = "sesion_preference";
    public static final String EMPTY_STRING = "";
    public static final String LANGUAGE_DEFAULT = "en";

    public static final String DOMAIN_URL = "http://192.168.0.121/heart-patrol";
    public final static String API_VERSION = "/v1/";

    public static String STORAGE_CARD_DOWNLOAD = "/HeartPatrol/Download/";
    public static String STORAGE_CARD = Environment.getExternalStorageDirectory().toString() + "/HeartPatrol";

    public static String AUTH_USERNAME;
    public static String AUTH_SOURCE;
    public static String AUTH_FIRST_NAME;
    public static String AUTH_LAST_NAME;
}
