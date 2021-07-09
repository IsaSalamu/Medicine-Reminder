package com.example.medicineremindertwo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DrugsDatabase extends SQLiteOpenHelper {
    public static final String DB_name = "drugs_database";
    public static final String DB_table = "drugs_table";
    public static final String DB_drugs_table = "all_drugs_table";


    public DrugsDatabase(@Nullable Context context) {
        super(context, DB_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+DB_table+"(id integer  primary key autoincrement, name text, number_of_drugs integer, start_time integer, starting_date integer, predefined_time text)");
        db.execSQL("create table "+DB_drugs_table+"(id integer  primary key autoincrement, name text, predetermined_time text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DB_table);
        db.execSQL("DROP TABLE IF EXISTS "+DB_drugs_table);
        onCreate(db);
    }

    public boolean insertDrugs(String name, Integer number_of_days, Integer number_of_drugs, Integer start_time, Integer starting_date, String predefined_time){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues dataHolder = new ContentValues();
        dataHolder.put("name", name);
        dataHolder.put("number_of_days", number_of_days);
        dataHolder.put("number_of_drugs", number_of_drugs);
        dataHolder.put("start_time", start_time);
        dataHolder.put("starting_date", starting_date);
        dataHolder.put("predefined_time", predefined_time);
        database.insert(DB_table, null, dataHolder);
        return true;
    }

    public boolean insertAllDrugs(String name, String predefined_time){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues dataHolder = new ContentValues();
        dataHolder.put("name", name);
        dataHolder.put("predefined_time", predefined_time);
        database.insert(DB_drugs_table, null, dataHolder);
        return true;
    }

    public Cursor getDrugs(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from "+DB_drugs_table, null);
        return cursor;
    }

}
