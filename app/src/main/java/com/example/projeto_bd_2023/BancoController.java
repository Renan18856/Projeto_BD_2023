package com.example.projeto_bd_2023;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {
    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context) {
        banco = new CriaBanco(context);
    }


    public String insereDados(String txtNome, String txtEmail) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("nome", txtNome);
        valores.put("email", txtEmail);

        resultado = db.insert("contatos", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }
    public String insereDadosUsuario(String txtNome, String txtCpf, String txtEmail, String txtSenha) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("nome", txtNome);
        valores.put("cpf", txtCpf);
        valores.put("email", txtEmail);
        valores.put("senha", txtSenha);

        resultado = db.insert("usuarios", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public String insereDadosPedidos(String emailLogin, int qtdBatata,
                                     int qtdHamburger, int qtdRefrigerante, float valorBatata,
                                     float valorHamburger, float valorRefrigerante, float totalGeral) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("email", emailLogin);
        valores.put("qtdBatata", qtdBatata);
        valores.put("qtdHamburger", qtdHamburger);
        valores.put("qtdRefrigerante", qtdRefrigerante);
        valores.put("valorBatata", valorBatata);
        valores.put("valorHamburger", valorHamburger);
        valores.put("valorRefrigerante", valorRefrigerante);
        valores.put("totalGeral", totalGeral);

        resultado = db.insert("pedidos", null, valores);
        db.close();

        if (resultado == -1)
            return "Atenção - Erro ao gravar o pedidos";
        else
            return "Pedido gravado com sucesso";

    }

    public String insereDadosAgendamento(String _emailLogin, String _data, String _hora) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("email", _emailLogin);
        valores.put("data", _data);
        valores.put("hora", _hora);

        resultado = db.insert("agendamento", null, valores);
        db.close();

        if (resultado == -1)
            return "Atenção - Erro ao gravar o Agendamento";
        else
            return "Agendamento gravado com sucesso";
    }

    public Cursor carregaDadosPeloCodigo(int id) {
        Cursor cursor;
        String[] campos = { "codigo", "nome", "email" };
        String where = "codigo=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query("contatos", campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }
    public Cursor carregaDadosParaLogin(String email, String senha){
        Cursor cursor;
        //SELECT idUser, nome, cpf, email, senha FROM usuarios WHERE email = 'digitado' and senha = 'digitado'
        String[] campos = { "idUser", "nome", "cpf", "email", "senha" };
        String where = "email = '" + email + "'  and senha = '" + senha + "'";
        db = banco.getReadableDatabase();
        cursor = db.query("usuarios", campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor consultaUsuarios(String emailLogin) {
        Cursor cursor;
        //SELECT idUser, nome, cpf, email, senha FROM usuarios WHERE email = 'digitado'
        String[] campos = { "idUser", "nome", "cpf", "email", "senha" };
        String where = "email = '" + emailLogin + "'";
        db = banco.getReadableDatabase();
        cursor = db.query("usuarios", campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor consultaAgendamento(String data, String hora) {
        Cursor cursor;
        //SELECT idAgendameto, email, data, hora FROM agendamento WHERE data = 'digitado' and hora = 'digitado'
        String[] campos = { "idAgendamento", "email", "data", "hora" };
        String where = "data = '" + data + "' and hora = '" + hora + "'";
        db = banco.getReadableDatabase();
        cursor = db.query("agendamento", campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    public Cursor consultaAgendamentos(String _data) {
        Cursor cursor;
        //SELECT idAgendameto, email, data, hora FROM agendamento
        String[] campos = { "idAgendamento", "email", "data", "hora" };
        String where = "data = '" + _data+ "'";
        db = banco.getReadableDatabase();
        cursor = db.query("agendamento", campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public String alteraDados(int id, String nome, String email){

        String msg = "Dados alterados com sucesso!!!" ;

        db = banco.getReadableDatabase();

        ContentValues valores = new ContentValues() ;
        valores.put("nome" , nome ) ;
        valores.put("email", email) ;

        String condicao = "codigo = " + id;

        int linha ;
        linha = db.update("contatos", valores, condicao, null) ;

        if (linha < 1){
            msg = "Erro ao alterar os dados" ;
        }

        db.close();
        return msg;
    }

    public String alteraDadosUsuario(int idUser, String _nome, String _cpf,
                                     String _email, String _senha) {
        String msg = "Dados do Usuário alterados com sucesso!!!" ;

        db = banco.getReadableDatabase();

        ContentValues valores = new ContentValues() ;
        valores.put("nome" , _nome ) ;
        valores.put("cpf", _cpf) ;
        valores.put("email", _email) ;
        valores.put("senha", _senha) ;

        String condicao = "idUser = " + idUser;

        int linha ;
        linha = db.update("usuarios", valores, condicao, null) ;

        if (linha < 1){
            msg = "Erro ao alterar os dados" ;
        }

        db.close();
        return msg;

    }

    public String excluirDados(int id){
        String msg = "Registro Excluído" ;

        db = banco.getReadableDatabase();

        String condicao = "codigo = " + id ;

        int linhas ;
        linhas = db.delete("contatos", condicao, null) ;

        if ( linhas < 1) {
            msg = "Erro ao Excluir" ;
        }

        db.close();
        return msg;
    }


}
