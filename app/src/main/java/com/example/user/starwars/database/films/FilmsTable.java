package com.example.user.starwars.database.films;

/**
 * Created by user on 14.07.2016.
 */
public interface FilmsTable {
    String TABLE_NAME = "films";
    interface Columns{
        String TITLE = "name";
        String DIRECTOR = "director";
        String PRODUCER = "producer";
        String OPENING_CRAWL = "opening_crawl";
        String RELESE_DATE = "relese_date";
    }
}
