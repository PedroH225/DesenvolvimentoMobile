package com.example.ac2_treinos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "treino.db";
    private static final int DATABASE_VERSION = 1;

    // Nome da tabela e colunas – Para o caso de tabela única
    private static final String TABLE_NAME = "exercicios";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOME = "nome";
    private static final String COLUMN_DURACAO = "duracao"; // em segundos
    private static final String COLUMN_EXECUCAO = "execucao"; // em segundos

    public BancoHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOME + " TEXT, "
                + COLUMN_DURACAO + " INTEGER, "
                + COLUMN_EXECUCAO + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long inserirExercicio(String nome, int duracao, String execucao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, nome);
        values.put(COLUMN_DURACAO, duracao);
        values.put(COLUMN_EXECUCAO, execucao);
        return db.insert(TABLE_NAME, null, values);
    }

    public Cursor listarExercicios()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public int atualizarExercicio(int id, String nome, int duracao, String execucao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, nome);
        values.put(COLUMN_DURACAO, duracao);
        values.put(COLUMN_EXECUCAO, execucao);
        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public int excluirUsuario(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }


}
