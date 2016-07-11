package com.example.user.starwars;

import java.io.Serializable;

/**
 * Created by user on 08.07.2016.
 */
public class Person implements Serializable {
    String name;
    String birthYear;
    String eyeColor;
    String height;
    String gender;
    String hairColor;
    String mass;

    public String getName() {
        return name;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getHeight() { return height; }

    public String getGender() {
        return gender;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getMass() {
        return mass;
    }
}