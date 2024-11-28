package com.example.projeto_bd_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MeusDados extends AppCompatActivity implements View.OnClickListener {
    String email_login;
    int idUser=0;
    Button btMEUAlterarMeusDados;
    EditText txtMEUNome, txtMEUCpf, txtMEUEmail, txtMEUSenha1, txtMEUSenha2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_dados);

        Intent tela = getIntent();
        Bundle parametro = tela.getExtras();
        email_login = parametro.getString("email");

        btMEUAlterarMeusDados = (Button) findViewById(R.id.btMEUAlterarDados);
        btMEUAlterarMeusDados.setOnClickListener(this);

        txtMEUNome = (EditText) findViewById(R.id.txtMEUNome);
        txtMEUEmail = (EditText) findViewById(R.id.txtMEUEmail);
        txtMEUCpf = (EditText) findViewById(R.id.txtMEUCpf);
        txtMEUSenha1 = (EditText) findViewById(R.id.txtMEUSenha1);
        txtMEUSenha2 = (EditText) findViewById(R.id.txtMEUSenha2);

        BancoController bd = new BancoController(getBaseContext());

        Cursor dados = bd.consultaUsuarios(email_login) ;

        if(dados.moveToFirst()) {
            idUser = dados.getInt(0);
            txtMEUNome.setText(dados.getString(1));
            txtMEUCpf.setText(dados.getString(2));
            txtMEUEmail.setText(dados.getString(3));
            txtMEUSenha1.setText(dados.getString(4));
            txtMEUSenha2.setText(dados.getString(4));
        }

    }

    @Override
    public void onClick(View view) {
        if (validaDados()==true){
            // alterar os dados
            Alterar();
        }
    }

    private void Alterar() {
        BancoController bd = new BancoController(getBaseContext());
        String msg = bd.alteraDadosUsuario(idUser, txtMEUNome.getText().toString(),
                txtMEUCpf.getText().toString(), txtMEUEmail.getText().toString(),
                txtMEUSenha1.getText().toString()) ;

        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
    }

    public boolean validaDados() {
        boolean retorno = true;
        String  msg = "";
        if (txtMEUNome.getText().length()==0){
            msg = "Atenção - Preencha corretamente o campo Nome";
            Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_LONG).show();
            retorno=false;
        }
        if (txtMEUCpf.getText().length()==0){
            msg = "Atenção - Preencha corretamente o campo CPF";
            Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_LONG).show();
            retorno=false;
        }
        if (txtMEUEmail.getText().length()==0){
            msg = "Atenção - Preencha corretamente o campo Email";
            Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_LONG).show();
            retorno=false;
        }
        if (txtMEUSenha1.getText().length()==0 || txtMEUSenha2.getText().length()==0 ){
            msg = "Atençao - Os campos de senha devem ser Preenchidos corretamente";
            Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_LONG).show();
            retorno=false;
        }
        if (!txtMEUSenha1.getText().toString().equals(txtMEUSenha2.getText().toString())){
            msg = "Atençao - Os campos de Senha e Confirma Senha estão diferentes!";
            Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_LONG).show();
            retorno=false;
        }
        return retorno;
    }
}