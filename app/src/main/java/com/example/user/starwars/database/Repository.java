package com.example.user.starwars.database;

import java.util.List;

/**
 * Created by user on 12.07.2016.
 */

public interface Repository <T>{
        void add(T item);

        void add(Iterable<T> items);

        void update(T item);

        void remove(T item);

        void remove(Specification specification);

        List<T> query(Specification specification);

}
