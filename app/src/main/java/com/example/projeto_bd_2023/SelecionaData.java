package com.example.projeto_bd_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.nio.BufferUnderflowException;

public class SelecionaData extends AppCompatActivity implements View.OnClickListener {
    String email_login;
    DatePicker txtSELData;
    Button btSELSeleciona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleciona_data);

        Intent tela = getIntent();
        Bundle parametro = tela.getExtras();
        email_login = parametro.getString("email");

        txtSELData = (DatePicker) findViewById(R.id.txtSELData);
        btSELSeleciona = (Button) findViewById(R.id.btSELSeleciona);
        btSELSeleciona.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String data = "";
        int mes = txtSELData.getMonth()+1;
        data = txtSELData.getDayOfMonth() + "/" + mes + "/" + txtSELData.getYear();

        //ir para a tela de consulta_lista
        Intent tela = new Intent(this,Consulta_lista.class);
        Bundle parametro = new Bundle();
        parametro.putString("email", email_login);
        parametro.putString("data", data);
        tela.putExtras(parametro);
        startActivity(tela);
    }
}