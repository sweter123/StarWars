package com.example.user.starwars.database.starships.specification;

import com.example.user.starwars.database.SqlSpecification;
import com.example.user.starwars.database.starships.StarshipsTable;

/**
 * Created by user on 14.07.2016.
 */
public class StarshipsSpecification implements SqlSpecification {
    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM %s", StarshipsTable.TABLE_NAME);
    }
}
