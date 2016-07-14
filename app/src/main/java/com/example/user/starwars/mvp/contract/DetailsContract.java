package com.example.user.starwars.mvp.contract;


/**
 * Created by user on 13.07.2016.
 */
public interface DetailsContract {

    interface Presenter {

        void getData();
    }

    interface View<T> {

        void onDataLoaded(T item);
    }
}
