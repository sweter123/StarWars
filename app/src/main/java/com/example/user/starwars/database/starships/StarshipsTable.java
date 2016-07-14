package com.example.user.starwars.database.starships;

/**
 * Created by user on 14.07.2016.
 */
public interface StarshipsTable {
    String TABLE_NAME = "starships";
    interface Columns{
        String NAME = "name";
        String MODEL = "model";
        String MANUFACTURER = "manufacturer";
        String LENGTH = "length";
        String CREW = "crew";
        String STARSHIP_CLASS = "starship_class";
    }
}
