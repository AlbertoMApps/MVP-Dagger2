package com.example.tae_user0.mvpitunes.di.Modules;

import android.util.Log;

import com.example.tae_user0.mvpitunes.Api.iTunesApi;
import com.example.tae_user0.mvpitunes.Constants.Constant;
import com.example.tae_user0.mvpitunes.MyApp;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by TAE_user0 on 05/02/2016.
 */
@Module public class NetModule {

    private RestAdapter restAdapt;
//    private iTunesApi spAdapt;
   // private final MyApp myApp;
//    private String mBaseUrl;
//
//    public NetModule(String baseUrl) {
//        //this.myApp = myApp1;
//        this.mBaseUrl = baseUrl;
//    }

//    @Provides @Singleton
//    OkHttpClient provideOkHttpClient(Cache cache) {
//        OkHttpClient okHttpClient = new OkHttpClient();
//        okHttpClient.setCache(cache);
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        okHttpClient.interceptors().add(interceptor);
//        return okHttpClient;
//    }

    @Provides @Singleton
    RestAdapter provideRetrofit() {
        Log.i("Retrofit", "provideRetrofit");

        //restAdapt
        restAdapt = new RestAdapter.Builder()
                .setEndpoint(Constant.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                //.setClient(new OkClient(okhttp))
                .build();
        //spAdapt = restAdapt.create(iTunesApi.class); //we won't use it here

        return restAdapt;
    }
}
