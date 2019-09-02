package com.example.boletos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TicketsDatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Tickets";
    public static final int DB_VERSION = 1;

    TicketsDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TICKETS("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "RUTA TEXT, "
        + "PRECIO INTEGER, "
        + "DIA INTEGER, "
        + "MES INTEGER, "
        + "ANIO INTEGER, "
        + "BOLETO INTEGER); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
