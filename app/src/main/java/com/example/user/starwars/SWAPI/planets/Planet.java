package com.example.user.starwars.SWAPI.planets;

import com.example.user.starwars.database.SqlSpecification;

/**
 * Created by user on 13.07.2016.
 */
public class Planet {
    String name;
    String climate;
    String terrain;
    String population;
    String gravity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", climate='" + climate + '\'' +
                ", terrain='" + terrain + '\'' +
                ", population='" + population + '\'' +
                ", gravity='" + gravity + '\'' +
                '}';
    }
}
