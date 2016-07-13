package com.example.user.starwars.database.people.specification;

import com.example.user.starwars.database.people.PeopleTable;
import com.example.user.starwars.database.SqlSpecification;

/**
 * Created by user on 13.07.2016.
 */
public class PersonNameSpecification implements SqlSpecification {

    private final String name;

    public PersonNameSpecification(String name) {
        this.name = name;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM "+ PeopleTable.TABLE_NAME + " WHERE " + PeopleTable.Columns.NAME + "=%s", name);

    }
}
