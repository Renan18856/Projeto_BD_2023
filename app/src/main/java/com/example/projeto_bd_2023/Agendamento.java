package com.example.projeto_bd_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

public class Agendamento extends AppCompatActivity implements View.OnClickListener {
    Button btAGEAgendar;
    Spinner txtAGEHora;
    DatePicker txtAGEData;
    String email_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);

        Intent tela = getIntent();
        Bundle parametro = tela.getExtras();
        email_login = parametro.getString("email");

        btAGEAgendar = (Button) findViewById(R.id.btAGEAgendar);
        txtAGEHora = (Spinner) findViewById(R.id.txtAGEHora);
        txtAGEData = (DatePicker) findViewById(R.id.txtAGEData);

        String[] horarios = new String[] {"08:00","08:30","09:00","09:30","10:00"};
        ArrayAdapter<String> aad = new ArrayAdapter<String>(this,
                android.R.layout.simple_gallery_item, horarios);
        txtAGEHora.setAdapter(aad);

        btAGEAgendar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String data, hora;
        int mes = txtAGEData.getMonth() + 1;
        data = txtAGEData.getDayOfMonth() + "/" + mes + "/" + txtAGEData.getYear();
        hora = txtAGEHora.getSelectedItem().toString();

        //gravar os dados
        BancoController bd = new BancoController(getBaseContext());
        String resultado;

        //verificar se a data e hora estão disponíveis (consulta)
        Cursor dados = bd.consultaAgendamento(data, hora) ;
        if(dados.moveToFirst()) {
            //nao pode gravar
            resultado = "Não é possível agendar, data e hora indisponível";
        }else{
            resultado = bd.insereDadosAgendamento(email_login, data, hora);
        }
        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

    }
}