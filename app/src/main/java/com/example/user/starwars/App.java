package com.example.user.starwars;

import android.app.Application;

import com.example.user.starwars.SWAPI.AppModule;
import com.example.user.starwars.SWAPI.NetModule;
import com.example.user.starwars.component.DaggerNetComponent;
import com.example.user.starwars.component.NetComponent;
import com.example.user.starwars.database.DbModule;
import com.facebook.stetho.Stetho;

import timber.log.Timber;

/**
 * Created by user on 11.07.2016.
 */
public class App extends Application {

    NetComponent netComponent;

    @Override

    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
        netComponent = DaggerNetComponent.builder()
                .netModule(new NetModule())
                .appModule(new AppModule(this))
                .dbModule(new DbModule())
                .build();

    }

    public NetComponent getNetComponent() {
        return netComponent;
    }

}

