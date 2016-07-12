package com.example.user.starwars.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.starwars.Person;

import java.util.Collections;
import java.util.List;

import timber.log.Timber;

/**
 * Created by user on 12.07.2016.
 */
public class PeopleRepository implements Repository<Person> {


    private SQLiteOpenHelper openHelper;

    public PeopleRepository(SQLiteOpenHelper openHelper) {

        this.openHelper = openHelper;
    }

    @Override
    public void add(Person item) {
        add(Collections.singletonList(item));
    }

    @Override
    public void add(Iterable<Person> items) {
        final SQLiteDatabase database = openHelper.getWritableDatabase();
        database.beginTransaction();
        try {
            for (Person item : items) {
                ContentValues person = new ContentValues();
                person.put(PeopleTable.Columns.NAME, item.getName());
                person.put(PeopleTable.Columns.BIRTH_YEAR, item.getName());
                person.put(PeopleTable.Columns.EYE_COLOR, item.getName());
                person.put(PeopleTable.Columns.GENDER, item.getName());
                person.put(PeopleTable.Columns.HAIR_COLOR, item.getName());
                person.put(PeopleTable.Columns.HEIGHT, item.getName());
                person.put(PeopleTable.Columns.MASS, item.getName());
                database.insertOrThrow(PeopleTable.TABLE_NAME, null, person);
                Timber.i("dodano do bazy danych: %s", item);
            }
        }catch(SQLiteException e){
            Timber.e(e, "Błąd");
        }finally {
            database.endTransaction();
            database.close();
        }

    }

    @Override
    public void update(Person item) {

    }

    @Override
    public void remove(Person item) {

    }

    @Override
    public void remove(Specification specification) {

    }

    @Override
    public List<Person> query(Specification specification) {
        return null;
    }
}
