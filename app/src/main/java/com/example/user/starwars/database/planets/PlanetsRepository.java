package com.example.user.starwars.database.planets;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.starwars.SWAPI.planets.Planet;
import com.example.user.starwars.database.Repository;
import com.example.user.starwars.database.Specification;
import com.example.user.starwars.database.people.PeopleTable;
import com.example.user.starwars.database.people.specification.PeopleSpecification;
import com.example.user.starwars.database.planets.specification.PlanetsSpecification;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by user on 14.07.2016.
 */
public class PlanetsRepository implements Repository<Planet> {

    private SQLiteOpenHelper openHelper;

    public PlanetsRepository(SQLiteOpenHelper openHelper) {

        this.openHelper = openHelper;
    }

    @Override
    public void add(Planet item) {
        add(Collections.singletonList(item));
    }

    @Override
    public void add(Iterable<Planet> items) {
        SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();
        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();
        try {
            writableDatabase.beginTransaction();
            for (Planet item : items) {
                Cursor cursor = readableDatabase.rawQuery("SELECT * FROM " + PlanetsTable.TABLE_NAME + " WHERE " + PlanetsTable.Columns.NAME + "=?", new String[]{item.getName()});
                if (!cursor.moveToFirst()){
                    ContentValues planet = new ContentValues();
                    planet.put(PlanetsTable.Columns.NAME, item.getName());
                    planet.put(PlanetsTable.Columns.CLIMATE, item.getClimate());
                    planet.put(PlanetsTable.Columns.GRAVITY, item.getGravity());
                    planet.put(PlanetsTable.Columns.POPULATION, item.getPopulation());
                    planet.put(PlanetsTable.Columns.TERRAIN, item.getTerrain());
                    writableDatabase.insertOrThrow(PlanetsTable.TABLE_NAME, null, planet);
                    Timber.i("dodano do bazy danych: %s", item);
                    cursor.close();
                }
            }

        }catch(SQLiteException e){
            Timber.e(e, "Błąd");
        }finally {
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            writableDatabase.close();
        }
    }

    @Override
    public void update(Planet item) {

    }

    @Override
    public void remove(Planet item) {

    }

    @Override
    public void remove(Specification specification) {

    }

    @Override
    public List<Planet> query(Specification specification) {
        final SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery(((PlanetsSpecification)specification).toSqlQuery(),new String[]{});
        List<Planet> items = new LinkedList<>();
        while(cursor.moveToNext()){
            Planet item = new Planet();
            item.setName(cursor.getString(cursor.getColumnIndex(PlanetsTable.Columns.NAME)));
            item.setClimate(cursor.getString(cursor.getColumnIndex(PlanetsTable.Columns.CLIMATE)));
            item.setGravity(cursor.getString(cursor.getColumnIndex(PlanetsTable.Columns.GRAVITY)));
            item.setPopulation(cursor.getString(cursor.getColumnIndex(PlanetsTable.Columns.POPULATION)));
            item.setTerrain(cursor.getString(cursor.getColumnIndex(PlanetsTable.Columns.TERRAIN)));
            items.add(item);
        }
        cursor.close();
        readableDatabase.close();
        return items;
    }
}
