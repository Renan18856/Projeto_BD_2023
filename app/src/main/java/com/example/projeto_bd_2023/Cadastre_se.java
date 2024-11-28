package com.example.projeto_bd_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastre_se extends AppCompatActivity implements View.OnClickListener {
    EditText txtCADNome, txtCADCpf, txtCADEmail, txtCADSenha1, txtCADSenha2;
    Button btGravarCadastre_se;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastre_se);

        txtCADNome = (EditText) findViewById(R.id.txtCADNome);
        txtCADCpf  = (EditText) findViewById(R.id.txtCADCpf);
        txtCADEmail  = (EditText) findViewById(R.id.txtCADEmail);
        txtCADSenha1  = (EditText) findViewById(R.id.txtCADSenha1);
        txtCADSenha2  = (EditText) findViewById(R.id.txtCADSenha2);
        btGravarCadastre_se = (Button) findViewById(R.id.btGravarCadastre_Se);
        btGravarCadastre_se.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (validaDados()==true){
            // gravar os dados
            Salvar();
        }
    }
    public boolean validaDados() {
        boolean retorno = true;
        String  msg = "";
        if (txtCADNome.getText().length()==0){
            msg = "Atenção - Preencha corretamente o campo Nome";
            Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_LONG).show();
            retorno=false;
        }
        if (txtCADCpf.getText().length()==0){
            msg = "Atenção - Preencha corretamente o campo CPF";
            Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_LONG).show();
            retorno=false;
        }
        if (txtCADEmail.getText().length()==0){
            msg = "Atenção - Preencha corretamente o campo Email";
            Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_LONG).show();
            retorno=false;
        }
        if (txtCADSenha1.getText().length()==0 || txtCADSenha2.getText().length()==0 ){
            msg = "Atençao - Os campos de senha devem ser Preenchidos corretamente";
            Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_LONG).show();
            retorno=false;
        }
        if (!txtCADSenha1.getText().toString().equals(txtCADSenha2.getText().toString())){
            msg = "Atençao - Os campos de Senha e Confirma Senha estão diferentes!";
            Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_LONG).show();
            retorno=false;
        }
        return retorno;
    }

    public void Salvar() {
        String msg = "";
        String txtNome = txtCADNome.getText().toString();
        String txtEmail = txtCADEmail.getText().toString();
        String txtCpf = txtCADCpf.getText().toString();
        String txtSenha = txtCADSenha1.getText().toString();

        BancoController bd = new BancoController(getBaseContext());
        String resultado;

        resultado = bd.insereDadosUsuario(txtNome, txtCpf, txtEmail, txtSenha);

        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
        //limpar();
    }

}