package com.example.user.starwars.SWAPI.starships;

/**
 * Created by user on 13.07.2016.
 */
public class Starship {
    String name;
    String model;
    String manufacturer;
    String length;
    String crew;
    String starshioClass;

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

    public String getStarshioClass() {
        return starshioClass;
    }

    public void setStarshioClass(String starshioClass) {
        this.starshioClass = starshioClass;
    }

    @Override
    public String toString() {
        return "Starship{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", length='" + length + '\'' +
                ", crew='" + crew + '\'' +
                ", starshioClass='" + starshioClass + '\'' +
                '}';
    }
}
