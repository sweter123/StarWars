package com.example.user.starwars.SWAPI;

import java.util.List;

/**
 * Created by user on 11.07.2016.
 */
public class ResultSet<T> {

    String count;
    List<T> results;
    String next;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        count = count;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}

