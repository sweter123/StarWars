package com.example.user.starwars;

import android.app.Application;

import com.example.user.starwars.SWAPI.StarWarsService;
import com.example.user.starwars.database.StarWarsSQLiteOpenhelper;
import com.example.user.starwars.database.films.FilmsRepository;
import com.example.user.starwars.database.people.PeopleRepository;
import com.example.user.starwars.database.planets.PlanetsRepository;
import com.example.user.starwars.database.starships.StarshipsRepository;
import com.facebook.stetho.Stetho;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by user on 11.07.2016.
 */
public class App extends Application implements AppProvider {

    private static final String HTTP_SWAPI_CO_API = "http://swapi.co/api/";

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
    }

    @Override
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    @Override
    public Gson getGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Override
    public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(HTTP_SWAPI_CO_API)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build();
    }

    @Override
    public StarWarsService getStarWarsService() {
        return getRetrofit().create(StarWarsService.class);
    }

    @Override
    public StarWarsSQLiteOpenhelper getStarWarsSQLiteOpenHelper() {
        return new StarWarsSQLiteOpenhelper(this);
    }

    @Override
    public StarshipsRepository getStarshipRepository() {
        return new StarshipsRepository(getStarWarsSQLiteOpenHelper());
    }

    @Override
    public FilmsRepository getFilmsRepository() {
        return new FilmsRepository(getStarWarsSQLiteOpenHelper());
    }

    @Override
    public PeopleRepository getPeopleRepository() {
        return new PeopleRepository(getStarWarsSQLiteOpenHelper());
    }

    @Override
    public PlanetsRepository getPlanetsRepository() {
        return new PlanetsRepository(getStarWarsSQLiteOpenHelper());
    }
}

