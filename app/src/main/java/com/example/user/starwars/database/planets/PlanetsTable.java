package com.example.user.starwars.database.planets;

/**
 * Created by user on 14.07.2016.
 */
public interface PlanetsTable {
    String TABLE_NAME = "planets";
    interface Columns{
        String NAME = "name";
        String CLIMATE = "climate";
        String TERRAIN = "terrain";
        String POPULATION = "population";
        String GRAVITY = "gravity";
    }
}
