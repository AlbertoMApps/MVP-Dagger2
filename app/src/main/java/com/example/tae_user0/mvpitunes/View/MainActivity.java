package com.example.tae_user0.mvpitunes.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tae_user0.mvpitunes.Api.iTunesApi;
import com.example.tae_user0.mvpitunes.MyApp;
import com.example.tae_user0.mvpitunes.Presenter.MainPresenter;
import com.example.tae_user0.mvpitunes.Model.ITunesModel;
import com.example.tae_user0.mvpitunes.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

//View
public class MainActivity extends AppCompatActivity {
    private MainPresenter mainPresenter;
   // @Inject RestAdapter restAdapter; //you can either call inject and initiate it or call the methods and no need the inject
    @Bind(R.id.trackInfo) TextView trackInfo;
    @Bind(R.id.artistName) TextView artistName;

    private CompositeSubscription _subscriptions = new CompositeSubscription();
    private iTunesApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set content view
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //create a new presenter
         mainPresenter = new MainPresenter(this);
//        trackInfo = (TextView) findViewById(R.id.trackInfo); //No needed as I use butterknife for it
//        artistName = (TextView) findViewById(R.id.artistName);
        //getComponent from MyApp and provide me with the methods from my modules part.
//        ((MyApp) getApplication()).getNetComponent().inject(this); //we need to implement it to inject the object, although it works without it too
//        restAdapter = ((MyApp) getApplication()).getNetComponent().provideRetrofit();
//        api = restAdapter.create(iTunesApi.class);

        api = mainPresenter.getApi(); //we call it from the presenter point
        getRetrofit(api); //api called from dagger 2

        patternRX();//patternRX using RX tecniques


//        Observable implementation for RX (Reactive Programming )
        observableSample();

    }//End of onCreate()

    private void observableSample() {
        Observable<Integer> myArrayObservable = Observable.from(new Integer[]{1, 2, 3, 4, 5, 6}); // Emits each item of the array, one at a time
        //Observable<Integer> myArrayObservable = Observable.from(new ITunesModel());
        myArrayObservable.subscribe(new Action1<Integer>() {

            @Override
            public void call(Integer i) {
                Log.d("My Action", String.valueOf(i)); // Prints the number received
                Toast.makeText(getBaseContext(), String.valueOf(i), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getRetrofit(iTunesApi api){
        api.getTrack(new Callback<ITunesModel>() {
            @Override
            public void success(ITunesModel iTunesModel, Response response) {
                //Toast.makeText(getApplicationContext(), "Artist name: " + iTunesModel.getResults().get(0).getArtistName(), Toast.LENGTH_SHORT).show();
                trackInfo.setText(iTunesModel.getResults().get(0).getTrackName());
                artistName.setText(iTunesModel.getResults().get(0).getArtistName());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public void patternRX(){
       _subscriptions.add(api.getTrack()

                       .observeOn(AndroidSchedulers.mainThread())// Handle on UI Thread
                       .subscribeOn(Schedulers.io())//Work on separate thread pool
                       .subscribe(new rx.Observer<List<iTunesApi>>() {

                           @Override
                           public void onCompleted() {

                           }

                           @Override
                           public void onError(Throwable e) {

                           }

                           @Override
                           public void onNext(List<iTunesApi> iTunesApis) {

                               ITunesModel model = (ITunesModel) iTunesApis.get(0);
                               Log.i("Retrofit", model.getResults().get(0).getTrackName().toString());
                              // Toast.makeText(getApplicationContext(), model.getResults().get(0).getTrackName().toString(), Toast.LENGTH_SHORT).show();
                               trackInfo.setText(model.getResults().get(0).getTrackName().toString());
                               artistName.setText(model.getResults().get(0).getArtistName().toString());

                           }


                       })
            );
        }


    }
