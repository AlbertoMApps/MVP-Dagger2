package com.example.tae_user0.mvpitunes.Presenter;

import android.view.View;

import com.example.tae_user0.mvpitunes.Api.iTunesApi;
import com.example.tae_user0.mvpitunes.View.MainActivity;

public interface IMainPresenter {
    MainActivity getView();
    iTunesApi getApi();
}
