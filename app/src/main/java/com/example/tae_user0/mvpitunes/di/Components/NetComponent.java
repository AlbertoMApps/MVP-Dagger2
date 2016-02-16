package com.example.tae_user0.mvpitunes.di.Components;

import com.example.tae_user0.mvpitunes.View.MainActivity;
import com.example.tae_user0.mvpitunes.di.Modules.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit.RestAdapter;

/**
 * Created by TAE_user0 on 05/02/2016.
 */
@Singleton
@Component(modules={NetModule.class})
public interface NetComponent {
    RestAdapter provideRetrofit();
    void inject (MainActivity main);
   // OkHttpClient provideOkHttpClient();
}
