package com.example.aula_bancodedados.bancodedados;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoDeDados {
    private SQLiteDatabase db;
    private static final BancoDeDados bancoDeDados = new BancoDeDados();

    public static BancoDeDados getInstance() {
        return bancoDeDados;
    }

    public void abrirBanco(Context context) {
        if (db == null) {
            db = context.openOrCreateDatabase("dbAula.db", Context.MODE_PRIVATE, null);
        }
    }

    public void fecharBanco() {
        try {
            if (db != null) {
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void execSql(String sql) {
        try {
            if (db != null) {
                db.execSQL(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cursor execBusca(String sql) {
        try {
            if (db != null) {
                return db.rawQuery(sql, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
