package com.example.user.starwars;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;

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

