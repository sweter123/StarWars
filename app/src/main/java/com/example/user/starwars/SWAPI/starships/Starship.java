package com.example.user.starwars.SWAPI.starships;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 13.07.2016.
 */
public class Starship implements Parcelable {

    String name;
    String model;
    String manufacturer;
    String length;
    String crew;
    String starshipClass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public String getStarshipClass() {
        return starshipClass;
    }

    public void setStarshipClass(String starshioClass) {
        this.starshipClass = starshioClass;
    }

    @Override
    public String toString() {
        return "Starship{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", length='" + length + '\'' +
                ", crew='" + crew + '\'' +
                ", starshioClass='" + starshipClass + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.model);
        dest.writeString(this.manufacturer);
        dest.writeString(this.length);
        dest.writeString(this.crew);
        dest.writeString(this.starshipClass);
    }

    public Starship() {
    }

    protected Starship(Parcel in) {
        this.name = in.readString();
        this.model = in.readString();
        this.manufacturer = in.readString();
        this.length = in.readString();
        this.crew = in.readString();
        this.starshipClass = in.readString();
    }

    public static final Creator<Starship> CREATOR = new Creator<Starship>() {
        @Override
        public Starship createFromParcel(Parcel source) {
            return new Starship(source);
        }

        @Override
        public Starship[] newArray(int size) {
            return new Starship[size];
        }
    };
}
