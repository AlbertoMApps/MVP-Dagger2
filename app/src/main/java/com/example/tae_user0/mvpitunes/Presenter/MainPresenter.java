package com.example.tae_user0.mvpitunes.Presenter;

import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.tae_user0.mvpitunes.Api.iTunesApi;
import com.example.tae_user0.mvpitunes.Constants.Constant;
import com.example.tae_user0.mvpitunes.Model.ITunesModel;
import com.example.tae_user0.mvpitunes.MyApp;
import com.example.tae_user0.mvpitunes.View.MainActivity;
import javax.inject.Inject;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by TAE_user0 on 04/02/2016.
 */

//Presenter
public class MainPresenter implements IMainPresenter{
    private MainActivity main; //View attribute
    private ITunesModel model; //Models attributes
    @Inject RestAdapter restAdapter; //you can either call inject and initiate it or call the methods and no need the inject

    public MainPresenter( MainActivity main) {
        this.main = main;
        this.model = new ITunesModel();
    }


    @Override
    public MainActivity getView() {
       return main;
    }
    @Override
    public iTunesApi getApi(){
        ((MyApp) main.getApplicationContext()).getNetComponent().inject(main); //we need to implement it to inject the object, although it works without it too
        restAdapter = ((MyApp)  main.getApplicationContext()).getNetComponent().provideRetrofit();
        return restAdapter.create(iTunesApi.class);
    }
}
