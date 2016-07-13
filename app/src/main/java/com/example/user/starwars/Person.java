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

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", height='" + height + '\'' +
                ", gender='" + gender + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", mass='" + mass + '\'' +
                '}';
    }
}