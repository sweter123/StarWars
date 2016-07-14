package com.example.user.starwars.database.starships;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.starwars.SWAPI.planets.Planet;
import com.example.user.starwars.SWAPI.starships.Starship;
import com.example.user.starwars.database.Repository;
import com.example.user.starwars.database.Specification;
import com.example.user.starwars.database.people.specification.PeopleSpecification;
import com.example.user.starwars.database.planets.PlanetsTable;
import com.example.user.starwars.database.starships.specification.StarshipsSpecification;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by user on 14.07.2016.
 */
public class StarshipsRepository implements Repository<Starship> {

    private SQLiteOpenHelper openHelper;

    public StarshipsRepository(SQLiteOpenHelper openHelper) {

        this.openHelper = openHelper;
    }

    @Override
    public void add(Starship item) {
        add(Collections.singletonList(item));
    }

    @Override
    public void add(Iterable<Starship> items) {
        SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();
        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();
        try {
            writableDatabase.beginTransaction();
            for (Starship item : items) {
                Cursor cursor = readableDatabase.rawQuery("SELECT * FROM " + StarshipsTable.TABLE_NAME + " WHERE " + StarshipsTable.Columns.NAME + "=?", new String[]{item.getName()});
                if (!cursor.moveToFirst()){
                    ContentValues starship = new ContentValues();
                    starship.put(StarshipsTable.Columns.NAME, item.getName());
                    starship.put(StarshipsTable.Columns.MODEL, item.getModel());
                    starship.put(StarshipsTable.Columns.LENGTH, item.getLength());
                    starship.put(StarshipsTable.Columns.CREW, item.getCrew());
                    starship.put(StarshipsTable.Columns.MANUFACTURER, item.getManufacturer());
                    starship.put(StarshipsTable.Columns.STARSHIP_CLASS, item.getStarshipClass());
                    writableDatabase.insertOrThrow(StarshipsTable.TABLE_NAME, null, starship);
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
    public void update(Starship item) {

    }

    @Override
    public void remove(Starship item) {

    }

    @Override
    public void remove(Specification specification) {

    }

    @Override
    public List<Starship> query(Specification specification) {
        final SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery(((StarshipsSpecification)specification).toSqlQuery(),new String[]{});
        List<Starship> items = new LinkedList<>();
        while(cursor.moveToNext()){
            Starship item = new Starship();
            item.setName(cursor.getString(cursor.getColumnIndex(StarshipsTable.Columns.NAME)));
            item.setCrew(cursor.getString(cursor.getColumnIndex(StarshipsTable.Columns.CREW)));
            item.setLength(cursor.getString(cursor.getColumnIndex(StarshipsTable.Columns.LENGTH)));
            item.setManufacturer(cursor.getString(cursor.getColumnIndex(StarshipsTable.Columns.MANUFACTURER)));
            item.setModel(cursor.getString(cursor.getColumnIndex(StarshipsTable.Columns.MODEL)));
            item.setStarshipClass(cursor.getString(cursor.getColumnIndex(StarshipsTable.Columns.STARSHIP_CLASS)));
            items.add(item);
        }
        cursor.close();
        readableDatabase.close();
        return items;
    }
}
