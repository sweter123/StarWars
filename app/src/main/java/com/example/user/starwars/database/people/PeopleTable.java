package com.example.user.starwars.database.people;

/**
 * Created by user on 12.07.2016.
 */
public interface PeopleTable {
    String TABLE_NAME = "people";
    public interface Columns{
        String NAME = "name";
        String BIRTH_YEAR = "birth_year";
        String EYE_COLOR = "eye_color";
        String HEIGHT = "height";
        String GENDER = "gender";
        String HAIR_COLOR = "hair_color";
        String MASS = "mass";
    }
}
