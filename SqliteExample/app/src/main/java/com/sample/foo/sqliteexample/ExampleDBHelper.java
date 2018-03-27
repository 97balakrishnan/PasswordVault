package com.sample.foo.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by obaro on 02/04/2015.
 */
public class ExampleDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SQLiteExample.db";
    private static final int DATABASE_VERSION = 2;

    public static final String VAULT_TABLE_NAME = "vault";
    public static final String VAULT_COLUMN_ID = "_id";
    public static final String VAULT_COLUMN_TITLE = "title";
    public static final String VAULT_COLUMN_PASSWORD = "password";
    //public static final String PERSON_COLUMN_AGE = "age";

    public ExampleDBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + VAULT_TABLE_NAME +
                        "(" + VAULT_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        VAULT_COLUMN_TITLE + " TEXT, " +
                        VAULT_COLUMN_PASSWORD + " TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + VAULT_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertPerson(String title, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(VAULT_COLUMN_TITLE,title);
        contentValues.put(VAULT_COLUMN_PASSWORD,password);
        //contentValues.put(PERSON_COLUMN_AGE, age);

        db.insert(VAULT_TABLE_NAME, null, contentValues);
        return true;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, VAULT_TABLE_NAME);
        return numRows;
    }

    public boolean updatePerson(Integer id, String name, String gender, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VAULT_COLUMN_TITLE, name);
        contentValues.put(VAULT_COLUMN_PASSWORD, gender);
        //contentValues.put(PERSON_COLUMN_AGE, age);
        db.update(VAULT_TABLE_NAME, contentValues, VAULT_COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deletePerson(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(VAULT_TABLE_NAME,
                VAULT_COLUMN_ID + " = ? ",
                new String[] { Integer.toString(id) });
    }

    public Cursor getPerson(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + VAULT_TABLE_NAME + " WHERE " +
                VAULT_COLUMN_ID + "=?", new String[]{Integer.toString(id)});
        return res;
    }

    public Cursor getAllPersons() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + VAULT_TABLE_NAME, null );
        return res;
    }
}