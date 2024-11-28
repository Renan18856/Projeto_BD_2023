package com.example.projeto_bd_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Menu extends AppCompatActivity implements View.OnClickListener {
    TextView txtMENUser;
    ImageButton btMENContatos, btMENPedidos, btMENMeusDados;
    ImageButton btMENAgendamento, btMENConsulaAgenda;
    String email_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent tela = getIntent();
        Bundle parametro = tela.getExtras();
        email_login = parametro.getString("email");

        txtMENUser = (TextView) findViewById(R.id.txtMENUser);
        btMENContatos = (ImageButton) findViewById(R.id.btMENContatos);
        btMENPedidos = (ImageButton) findViewById(R.id.btMENPedidos);
        btMENMeusDados = (ImageButton) findViewById(R.id.btMENMeusDados);
        btMENAgendamento = (ImageButton) findViewById(R.id.btMENAgendamento);
        btMENConsulaAgenda = (ImageButton) findViewById(R.id.btMENConsultaAgendamento);

        txtMENUser.setText("Usuário : " + email_login);
        btMENContatos.setOnClickListener(this);
        btMENPedidos.setOnClickListener(this);
        btMENMeusDados.setOnClickListener(this);
        btMENAgendamento.setOnClickListener(this);
        btMENConsulaAgenda.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btMENContatos) {
            Intent tela = new Intent(this, MainActivity.class);
            startActivity(tela);
        }
        if (view.getId()==R.id.btMENPedidos) {
            Intent tela = new Intent(this, Pedidos.class);
            Bundle parametro = new Bundle();
            parametro.putString("email", email_login);
            tela.putExtras(parametro);
            startActivity(tela);
        }
        if (view.getId()==R.id.btMENMeusDados) {
            Intent tela = new Intent(this, MeusDados.class);
            Bundle parametro = new Bundle();
            parametro.putString("email", email_login);
            tela.putExtras(parametro);
            startActivity(tela);
        }
        if (view.getId()==R.id.btMENAgendamento) {
            Intent tela = new Intent(this, Agendamento.class);
            Bundle parametro = new Bundle();
            parametro.putString("email", email_login);
            tela.putExtras(parametro);
            startActivity(tela);
        }
        if (view.getId()==R.id.btMENConsultaAgendamento) {
            Intent tela = new Intent(this, SelecionaData.class);
            Bundle parametro = new Bundle();
            parametro.putString("email", email_login);
            tela.putExtras(parametro);
            startActivity(tela);
        }
    }
}