package com.example.user.starwars.database.films;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.starwars.SWAPI.films.Film;
import com.example.user.starwars.database.Repository;
import com.example.user.starwars.database.Specification;
import com.example.user.starwars.database.films.specification.FilmsSpecification;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by user on 14.07.2016.
 */
public class FilmsRepository implements Repository<Film> {

    private SQLiteOpenHelper openHelper;

    public FilmsRepository(SQLiteOpenHelper openHelper) {

        this.openHelper = openHelper;
    }

    @Override
    public void add(Film item) {
        add(Collections.singletonList(item));
    }

    @Override
    public void add(Iterable<Film> items) {
        SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();
        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();
        try {
            writableDatabase.beginTransaction();
            for (Film item : items) {
                Cursor cursor = readableDatabase.rawQuery("SELECT * FROM " + FilmsTable.TABLE_NAME + " WHERE " + FilmsTable.Columns.TITLE + "=?", new String[]{item.getTitle()});
                if (!cursor.moveToFirst()){
                    ContentValues film = new ContentValues();
                    film.put(FilmsTable.Columns.TITLE, item.getTitle());
                    film.put(FilmsTable.Columns.DIRECTOR, item.getDirector());
                    film.put(FilmsTable.Columns.OPENING_CRAWL, item.getOpeningCrawl());
                    film.put(FilmsTable.Columns.PRODUCER, item.getProducer());
                    film.put(FilmsTable.Columns.RELESE_DATE, item.getReleaseDate());
                    writableDatabase.insertOrThrow(FilmsTable.TABLE_NAME, null, film);
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
    public void update(Film item) {

    }

    @Override
    public void remove(Film item) {

    }

    @Override
    public void remove(Specification specification) {

    }

    @Override
    public List<Film> query(Specification specification) {
        final SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery(((FilmsSpecification)specification).toSqlQuery(),new String[]{});
        List<Film> items = new LinkedList<>();
        while(cursor.moveToNext()){
            Film item = new Film();
            item.setTitle(cursor.getString(cursor.getColumnIndex(FilmsTable.Columns.TITLE)));
            item.setDirector(cursor.getString(cursor.getColumnIndex(FilmsTable.Columns.DIRECTOR)));
            item.setOpeningCrawl(cursor.getString(cursor.getColumnIndex(FilmsTable.Columns.OPENING_CRAWL)));
            item.setProducer(cursor.getString(cursor.getColumnIndex(FilmsTable.Columns.PRODUCER)));
            item.setReleaseDate(cursor.getString(cursor.getColumnIndex(FilmsTable.Columns.RELESE_DATE)));
            items.add(item);
        }
        cursor.close();
        readableDatabase.close();
        return items;
    }
}
