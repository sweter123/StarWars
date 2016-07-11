package com.example.user.starwars;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by user on 11.07.2016.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
