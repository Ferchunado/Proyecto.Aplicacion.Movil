package com.example.test5.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "tabla_Tareas_1.db";
    public static final  String  TABLE_TAREAS = "tareasAsignadas";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_TAREAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo_Tarea TEXT NOT NULL," +
                "fecha_limite INTEGER NOT NULL," +
                "prioridad TEXT NOT NULL," +
                "responsable TEXT NOT NULL," +
                "descripcion TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_TAREAS);
        onCreate(sqLiteDatabase);
    }
}
