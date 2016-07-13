package com.example.user.starwars.database.people.specification;

import com.example.user.starwars.database.people.PeopleTable;
import com.example.user.starwars.database.SqlSpecification;

/**
 * Created by user on 13.07.2016.
 */
public class PeopleSpecification implements SqlSpecification {
    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM %s", PeopleTable.TABLE_NAME);
    }
}
