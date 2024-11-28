package com.example.projeto_bd_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class Consulta_lista extends AppCompatActivity {
    ListView lista;
    String email_login, data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_lista);

        Intent tela = getIntent();
        Bundle parametro = tela.getExtras();
        email_login = parametro.getString("email");
        data = parametro.getString("data");


        List<ModeloDados> listaAgendamento = null;
        listaAgendamento = consultaTodosAgendamentos(data);

        AgendaAdapter adaptador = new AgendaAdapter(this,listaAgendamento);

        lista = (ListView) findViewById(R.id.lista);
        lista.setAdapter(adaptador);

    }

    private List<ModeloDados> consultaTodosAgendamentos(String _data) {
        List<ModeloDados> lista = new LinkedList<ModeloDados>();

        BancoController bd = new BancoController(getBaseContext());
        Cursor dados = bd.consultaAgendamentos(_data);

        if (dados.moveToFirst()){
            // encontrou conteúdo para mostrar na lista
            do {
                ModeloDados item = new ModeloDados();
                item.setIdAgendamento(dados.getInt(0));
                item.setEmail(dados.getString(1));
                item.setData(dados.getString(2));
                item.setHora(dados.getString(3));
                lista.add(item);
            } while (dados.moveToNext());
        }else{
            String msg = "Não há agendamentos efetuados!";
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        }
        return lista;
    }
}