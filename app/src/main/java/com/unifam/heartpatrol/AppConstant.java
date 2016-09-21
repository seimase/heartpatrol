package com.unifam.heartpatrol;

import android.content.SharedPreferences;

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

    public static final String DOMAIN_URL = "https://api.github.com";
    public final static String API_VERSION = "/";

}
