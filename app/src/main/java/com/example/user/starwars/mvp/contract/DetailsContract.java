package com.example.user.starwars.mvp.contract;

import com.example.user.starwars.Person;

/**
 * Created by user on 13.07.2016.
 */
public interface DetailsContract {

    interface Presenter {

        void getData();
    }

    interface View {

        void onDataLoaded(Person item);
    }
}
