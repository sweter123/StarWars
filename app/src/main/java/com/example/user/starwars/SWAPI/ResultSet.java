package com.example.user.starwars.SWAPI;

import com.example.user.starwars.SWAPI.*;
import com.example.user.starwars.SWAPI.people.Person;

import java.util.List;

/**
 * Created by user on 11.07.2016.
 */
public class ResultSet {

    String count;
    List<Person> results;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        count = count;
    }

    public List<Person> getResults() {
        return results;
    }

    public void setResults(List<Person> results) {
        this.results = results;
    }
}

