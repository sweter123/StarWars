package com.example.user.starwars.component;

import com.example.user.starwars.FilmsFragment;
import com.example.user.starwars.PeopleFragment;
import com.example.user.starwars.PlanetsFragment;
import com.example.user.starwars.StarshipsFragment;
import com.example.user.starwars.mvp.CustomScope;
import com.example.user.starwars.mvp.PeopleScreenModule;

import dagger.Component;

/**
 * Created by Aditya on 11-May-16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = PeopleScreenModule.class)
public interface FragmentComponent {
    void inject(PeopleFragment activity);
    void inject(FilmsFragment activity);
    void inject(PlanetsFragment activity);
    void inject(StarshipsFragment activity);
}
