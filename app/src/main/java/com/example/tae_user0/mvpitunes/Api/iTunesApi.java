package com.example.tae_user0.mvpitunes.Api;

import com.example.tae_user0.mvpitunes.Model.ITunesModel;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by TAE_user0 on 04/02/2016.
 */
public interface iTunesApi {
    @GET("/search?term=classick&amp;media=music&amp;entity=song&amp;limit=50")
    public void getTrack( Callback<ITunesModel> response);
    Observable<List<iTunesApi>> getTrack();
}
