package com.example.ac1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "filmes_db";
    private static final Integer DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "filmes";
    private static final String COLUMN_ID= "id";
    private static final String COLUMN_TITULO= "titulo";
    private static final String COLUMN_ANO= "ano";
    private static final String COLUMN_NOTA= "nota";
    private static final String COLUMN_GENERO= "genero";
    private static final String COLUMN_VISUALIZACAO= "visualizacao";


    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_TITULO + " TEXT,"
            + COLUMN_ANO + " INTEGER,"
            + COLUMN_NOTA + " DECILMAL,"
            + COLUMN_GENERO + " TEXT,"
            + COLUMN_VISUALIZACAO + " BOOLEAN DEFAULT FALSE)";
    db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long inserirFilme(String titulo, Integer ano, Double nota, String genero, Boolean visualizacao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITULO, titulo);
        values.put(COLUMN_ANO, ano);
        values.put(COLUMN_NOTA, nota);
        values.put(COLUMN_GENERO, genero);
        values.put(COLUMN_VISUALIZACAO, visualizacao);
        return db.insert(TABLE_NAME, null, values);

    }

    public long inserirFilme(Integer id, String titulo, Integer ano, Double nota, String genero, Boolean visualizacao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITULO, titulo);
        values.put(COLUMN_ANO, ano);
        values.put(COLUMN_NOTA, nota);
        values.put(COLUMN_GENERO, genero);
        values.put(COLUMN_VISUALIZACAO, visualizacao);
        return db.update(TABLE_NAME, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }

    public Integer excluirFilme(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }

    public Cursor listarFilmes() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public Cursor findFilmeById(Integer id) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + id, null);
    }
}



