package com.example.user.starwars.component;

import com.example.user.starwars.SWAPI.AppModule;
import com.example.user.starwars.SWAPI.NetModule;
import com.example.user.starwars.SWAPI.StarWarsService;
import com.example.user.starwars.database.DbModule;
import com.example.user.starwars.database.films.FilmsRepository;
import com.example.user.starwars.database.people.PeopleRepository;
import com.example.user.starwars.database.planets.PlanetsRepository;
import com.example.user.starwars.database.starships.StarshipsRepository;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by user on 18.07.2016.
 */
@Singleton
@Component(modules = {NetModule.class, DbModule.class, AppModule.class})
public interface NetComponent {
    StarWarsService service();
    PeopleRepository peopleDb();
    FilmsRepository filmsDb();
    PlanetsRepository planetsDb();
    StarshipsRepository starshipsDb();
}

