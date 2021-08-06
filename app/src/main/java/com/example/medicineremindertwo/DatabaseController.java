package com.example.medicineremindertwo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseController extends SQLiteOpenHelper {
    public static final String db_name = "drugs_database";
    public static final String db_table = "drugs_names_table";

    public DatabaseController(@Nullable Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+db_table+" (id integer primary key autoincrement, name text, hour int, minutes int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+db_table);
        onCreate(db);
    }

    public boolean insertDrugData(String name, Integer hour, Integer minutes){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("hour", hour);
        contentValues.put("minutes", minutes);
        long result = sqLiteDatabase.insert(db_table,null,contentValues);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getDrugs(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+db_table, null);
        return cursor;
    }
    public Integer deleteReminder(Integer id){
        SQLiteDatabase database = this.getReadableDatabase();
        Integer result = database.delete(db_table,"id=?", new String[]{String.format("%s", id)});
        return result;
    }
}
