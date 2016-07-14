package com.example.user.starwars.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.starwars.database.films.FilmsTable;
import com.example.user.starwars.database.people.PeopleTable;
import com.example.user.starwars.database.planets.PlanetsTable;
import com.example.user.starwars.database.starships.StarshipsTable;

import timber.log.Timber;

/**
 * Created by user on 12.07.2016.
 */
public class StarWarsSQLiteOpenhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Star_wars.db";
    private static final int version = 1;

    public StarWarsSQLiteOpenhelper(Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Timber.i("tworze baze");
        createPeople(sqLiteDatabase);
        createPlanets(sqLiteDatabase);
        createFilms(sqLiteDatabase);
        createStarships(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void createPeople(SQLiteDatabase sqLiteDatabase){
        Timber.i("tworze Tabele people");
        sqLiteDatabase.execSQL("CREATE TABLE " + PeopleTable.TABLE_NAME + " (" +
                PeopleTable.Columns.NAME + " TEXT, " +
                PeopleTable.Columns.BIRTH_YEAR + " TEXT, " +
                PeopleTable.Columns.EYE_COLOR + " TEXT, " +
                PeopleTable.Columns.GENDER + " TEXT, " +
                PeopleTable.Columns.HAIR_COLOR + " TEXT, " +
                PeopleTable.Columns.HEIGHT + " TEXT, " +
                PeopleTable.Columns.MASS + " TEXT)");
    }

    private void createPlanets(SQLiteDatabase sqLiteDatabase){
        Timber.i("tworze Tabele planets");
        sqLiteDatabase.execSQL("CREATE TABLE " + PlanetsTable.TABLE_NAME + " (" +
                PlanetsTable.Columns.NAME + " TEXT, " +
                PlanetsTable.Columns.CLIMATE + " TEXT, " +
                PlanetsTable.Columns.GRAVITY + " TEXT, " +
                PlanetsTable.Columns.POPULATION + " TEXT, " +
                PlanetsTable.Columns.TERRAIN + " TEXT)");
    }

    private void createFilms(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE " + FilmsTable.TABLE_NAME + " (" +
                FilmsTable.Columns.TITLE + " TEXT, " +
                FilmsTable.Columns.DIRECTOR + " TEXT, " +
                FilmsTable.Columns.PRODUCER + " TEXT, " +
                FilmsTable.Columns.OPENING_CRAWL + " TEXT, " +
                FilmsTable.Columns.RELESE_DATE + " TEXT)");
    }

    private void createStarships(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE " + StarshipsTable.TABLE_NAME + " (" +
                StarshipsTable.Columns.NAME + " TEXT, " +
                StarshipsTable.Columns.MANUFACTURER + " TEXT, " +
                StarshipsTable.Columns.MODEL + " TEXT, " +
                StarshipsTable.Columns.LENGTH + " TEXT, " +
                StarshipsTable.Columns.CREW + " TEXT, " +
                StarshipsTable.Columns.STARSHIP_CLASS + " TEXT)");
    }
}
