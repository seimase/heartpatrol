package com.unifam.heartpatrol.model.net;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.unifam.heartpatrol.AppConstant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;

public class NetworkManager {

    public static NetworkManager instance;
    private Context context;
    public static NetworkManager getInstance(Class<NetworkService> networkServiceClass)
    {
        if(instance == null) {
            instance = new NetworkManager();
        }

        return instance;
    }

    static OkHttpClient okHttpClient;
    public static NetworkService getNetworkService(Context context){
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.DOMAIN_URL + AppConstant.API_VERSION)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        NetworkService networkService = retrofit.create(NetworkService.class);
        return  networkService;
    }

}
