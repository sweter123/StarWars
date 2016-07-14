package com.example.user.starwars.database.films.specification;

import com.example.user.starwars.database.SqlSpecification;
import com.example.user.starwars.database.films.FilmsTable;
import com.example.user.starwars.database.people.PeopleTable;

/**
 * Created by user on 14.07.2016.
 */
public class FilmsSpecification implements SqlSpecification {
    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM %s", FilmsTable.TABLE_NAME);
    }
}
