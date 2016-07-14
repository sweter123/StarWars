package com.example.user.starwars.database.planets.specification;

import com.example.user.starwars.database.SqlSpecification;
import com.example.user.starwars.database.people.PeopleTable;
import com.example.user.starwars.database.planets.PlanetsTable;

/**
 * Created by user on 13.07.2016.
 */
public class PlanetsSpecification implements SqlSpecification {
    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM %s", PlanetsTable.TABLE_NAME);
    }
}
