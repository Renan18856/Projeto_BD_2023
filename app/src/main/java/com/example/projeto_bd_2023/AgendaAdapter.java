package com.example.projeto_bd_2023;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AgendaAdapter extends ArrayAdapter<ModeloDados> {

    private Context context;
    private List<ModeloDados> listaAgendamento = null;
    public AgendaAdapter(Context context, List<ModeloDados> listaAgendamento) {
        super(context, 0, listaAgendamento);
        this.listaAgendamento = listaAgendamento;
        this.context = context;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent){
        ModeloDados agendamento = listaAgendamento.get(position);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_item, null);
        }

        TextView txtCONIdAgendamento = (TextView) view.findViewById(R.id.txtCONIdAgendamento);
        TextView txtCONEmail = (TextView) view.findViewById(R.id.txtCONEmail);
        TextView txtCONData = (TextView) view.findViewById(R.id.txtCONData);
        TextView txtCONHora = (TextView) view.findViewById(R.id.txtCONHora);

        txtCONIdAgendamento.setText(String.valueOf(agendamento.getIdAgendamento()));
        txtCONEmail.setText(String.valueOf(agendamento.getEmail()));
        txtCONData.setText(String.valueOf(agendamento.getData()));
        txtCONHora.setText(String.valueOf(agendamento.getHora()));

        return view;
    }

}