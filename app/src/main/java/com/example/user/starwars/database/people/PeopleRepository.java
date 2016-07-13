package com.example.user.starwars.database.people;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.starwars.Person;
import com.example.user.starwars.database.people.specification.PeopleSpecification;
import com.example.user.starwars.database.Repository;
import com.example.user.starwars.database.Specification;

import java.util.Collections;
import java.util.LinkedList;
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
        SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();
        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();
        try {
            writableDatabase.beginTransaction();
            for (Person item : items) {
                Cursor cursor = readableDatabase.rawQuery("SELECT * FROM " + PeopleTable.TABLE_NAME + " WHERE " + PeopleTable.Columns.NAME + "=?", new String[]{item.getName()});
                if (!cursor.moveToFirst()){
                    ContentValues person = new ContentValues();
                    person.put(PeopleTable.Columns.NAME, item.getName());
                    person.put(PeopleTable.Columns.BIRTH_YEAR, item.getBirthYear());
                    person.put(PeopleTable.Columns.EYE_COLOR, item.getEyeColor());
                    person.put(PeopleTable.Columns.GENDER, item.getGender());
                    person.put(PeopleTable.Columns.HAIR_COLOR, item.getHairColor());
                    person.put(PeopleTable.Columns.HEIGHT, item.getHeight());
                    person.put(PeopleTable.Columns.MASS, item.getMass());
                    writableDatabase.insertOrThrow(PeopleTable.TABLE_NAME, null, person);
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
        final SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery(((PeopleSpecification)specification).toSqlQuery(),new String[]{});
        List<Person> items = new LinkedList<>();
        while(cursor.moveToNext()){
            Person item = new Person();
            item.setName(cursor.getString(cursor.getColumnIndex(PeopleTable.Columns.NAME)));
            item.setBirthYear(cursor.getString(cursor.getColumnIndex(PeopleTable.Columns.BIRTH_YEAR)));
            item.setEyeColor(cursor.getString(cursor.getColumnIndex(PeopleTable.Columns.EYE_COLOR)));
            item.setGender(cursor.getString(cursor.getColumnIndex(PeopleTable.Columns.GENDER)));
            item.setHairColor(cursor.getString(cursor.getColumnIndex(PeopleTable.Columns.HAIR_COLOR)));
            item.setHeight(cursor.getString(cursor.getColumnIndex(PeopleTable.Columns.HEIGHT)));
            item.setMass(cursor.getString(cursor.getColumnIndex(PeopleTable.Columns.MASS)));
            items.add(item);
        }
        cursor.close();
        readableDatabase.close();
        return items;
    }
}
