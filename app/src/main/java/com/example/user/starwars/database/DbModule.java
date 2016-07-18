package com.example.user.starwars.database;

import android.app.Application;

import com.example.user.starwars.database.films.FilmsRepository;
import com.example.user.starwars.database.people.PeopleRepository;
import com.example.user.starwars.database.planets.PlanetsRepository;
import com.example.user.starwars.database.starships.StarshipsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 18.07.2016.
 */

@Module
public class DbModule {
    @Provides
    @Singleton
    public StarWarsSQLiteOpenhelper getStarWarsSQLiteOpenHelper(Application application) {
        return new StarWarsSQLiteOpenhelper(application);
    }

    @Provides
    @Singleton
    public StarshipsRepository getStarshipRepository(StarWarsSQLiteOpenhelper openHelper) {
        return new StarshipsRepository(openHelper);
    }

    @Provides
    @Singleton
    public FilmsRepository getFilmsRepository(StarWarsSQLiteOpenhelper openHelper) {
        return new FilmsRepository(openHelper);
    }

    @Provides
    @Singleton
    public PeopleRepository getPeopleRepository(StarWarsSQLiteOpenhelper openHelper) {
        return new PeopleRepository(openHelper);
    }

    @Provides
    @Singleton
    public PlanetsRepository getPlanetsRepository(StarWarsSQLiteOpenhelper openHelper) {
        return new PlanetsRepository(openHelper);
    }
}
