package com.example.projeto_bd_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText txtLOGEmail, txtLOGSenha;
    Button   btLOGCadastro, btLOGAcessar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtLOGEmail = (EditText) findViewById(R.id.txtLOGEmail);
        txtLOGSenha = (EditText) findViewById(R.id.txtLOGSenha);
        btLOGCadastro= (Button) findViewById(R.id.btLOGCadastro);
        btLOGAcessar=  (Button) findViewById(R.id.btLOGAcessar);

        btLOGCadastro.setOnClickListener(this);
        btLOGAcessar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btLOGAcessar) {
            if (validaDados()){
                // carregando a tela de Acessar
                Intent tela = new Intent(this, Menu.class);
                Bundle parametro = new Bundle();
                parametro.putString("email", txtLOGEmail.getText().toString());
                tela.putExtras(parametro);
                startActivity(tela);
            }
        }
        if (view.getId() == R.id.btLOGCadastro) {
            // carregando a tela de Cadastre-se
            Intent tela = new Intent(this, Cadastre_se.class);
            startActivity(tela);
        }
    }
    public boolean validaDados() {
        String msg = "";
        boolean retorno = true;
        if (txtLOGEmail.getText().length() == 0) {
            msg = "Senhor usuário, o campo E-MAIL deve ser preenchido!";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            retorno = false;
        } else {
            if (txtLOGSenha.getText().length() == 0) {
                msg = "Senhor usuário, o campo SENHA deve ser preenchido!";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                retorno = false;
            } else {
                // verificar se o email e senha estão cadastrados no banco de dados
                BancoController bd = new BancoController(getBaseContext());

                Cursor dados = bd.carregaDadosParaLogin(txtLOGEmail.getText().toString(), txtLOGSenha.getText().toString());

                if (dados.moveToFirst()) {
                    // dados encontrados
                    retorno = true;
                } else {
                    msg = "O E-mail digitado ou a senha digitada não estão cadastrados!";
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    retorno = false;
                }
            }
        }
        return retorno;
    }
}