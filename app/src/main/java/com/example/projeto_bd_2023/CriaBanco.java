package com.example.projeto_bd_2023;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco_exemplo_n1.db";
    private static final int VERSAO = 4;
    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE contatos ("
                + "codigo integer primary key autoincrement,"
                + "nome text,"
                + "email text)";
        db.execSQL(sql);
        sql = "CREATE TABLE usuarios ("
                + "idUser integer primary key autoincrement,"
                + "nome text,"
                + "cpf text,"
                + "email text,"
                + "senha text)";
        db.execSQL(sql);
        sql = "CREATE TABLE pedidos ("
                + "idPedido integer primary key autoincrement,"
                + "email text,"
                + "qtdBatata int,"
                + "qtdHamburger int,"
                + "qtdRefrigerante int,"
                + "valorBatata float,"
                + "valorHamburger float,"
                + "valorRefrigerante float,"
                + "totalGeral float)";
        db.execSQL(sql);
        sql = "CREATE TABLE agendamento ("
                + "idAgendamento integer primary key autoincrement,"
                + "email text,"
                + "data text,"
                + "hora text)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contatos");
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS pedidos");
        db.execSQL("DROP TABLE IF EXISTS agendamento");
        onCreate(db);
    }
}