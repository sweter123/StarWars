package com.example.user.starwars.SWAPI.planets;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.user.starwars.database.SqlSpecification;

import java.io.Serializable;

/**
 * Created by user on 13.07.2016.
 */
public class Planet implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.climate);
        dest.writeString(this.terrain);
        dest.writeString(this.population);
        dest.writeString(this.gravity);
    }

    public Planet() {
    }

    protected Planet(Parcel in) {
        this.name = in.readString();
        this.climate = in.readString();
        this.terrain = in.readString();
        this.population = in.readString();
        this.gravity = in.readString();
    }

    public static final Creator<Planet> CREATOR = new Creator<Planet>() {
        @Override
        public Planet createFromParcel(Parcel source) {
            return new Planet(source);
        }

        @Override
        public Planet[] newArray(int size) {
            return new Planet[size];
        }
    };
}
