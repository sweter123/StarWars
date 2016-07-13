package com.example.user.starwars.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.starwars.database.people.PeopleTable;

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
        sqLiteDatabase.execSQL("CREATE TABLE " + PeopleTable.TABLE_NAME + " (" +
                PeopleTable.Columns.NAME + " TEXT, " +
                PeopleTable.Columns.BIRTH_YEAR + " TEXT, " +
                PeopleTable.Columns.EYE_COLOR + " TEXT, " +
                PeopleTable.Columns.GENDER + " TEXT, " +
                PeopleTable.Columns.HAIR_COLOR + " TEXT, " +
                PeopleTable.Columns.HEIGHT + " TEXT, " +
                PeopleTable.Columns.MASS + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
