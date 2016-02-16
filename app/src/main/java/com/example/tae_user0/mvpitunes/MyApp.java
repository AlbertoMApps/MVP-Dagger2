package com.example.tae_user0.mvpitunes;

import android.app.Application;
import android.content.Context;

import com.example.tae_user0.mvpitunes.Constants.Constant;
import com.example.tae_user0.mvpitunes.di.Components.DaggerNetComponent;
import com.example.tae_user0.mvpitunes.di.Components.NetComponent;
import com.example.tae_user0.mvpitunes.di.Modules.NetModule;

/**
 * Created by TAE_user0 on 05/02/2016.
 */
public class MyApp extends Application {
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent
                .builder()
                .netModule(new NetModule())
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

}
