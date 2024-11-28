package com.example.projeto_bd_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Pedidos extends AppCompatActivity implements View.OnClickListener {
    int qtdBatata = 0, qtdHamburger = 0, qtdRefrigerante = 0;
    float valorBatata = 10, valorRefrigerante = 8, valorHamburger = 20;
    float TotalGeral = 0, TotalBatata = 0, TotalHamburger = 0, TotalRefrigerante = 0;
    Button btMaisBatata, btMaisHamburger, btMaisRefrigerante;
    Button btMenosBatata, btMenosHamburger, btMenosRefrigerante;
    Button btGravarPedido;
    TextView txtQtdBatata, txtQtdHamburger, txtQtdRefrigerante,txtTotalGeral;
    String email_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        Intent tela = getIntent();
        Bundle parametro = tela.getExtras();
        email_login = parametro.getString("email");

        btMaisBatata = (Button) findViewById(R.id.btMaisBatata);
        btMaisHamburger = (Button) findViewById(R.id.btMaisHamburger);
        btMaisRefrigerante = (Button) findViewById(R.id.btMaisRefrigerante);

        btMenosBatata = (Button) findViewById(R.id.btMenosBatata);
        btMenosHamburger = (Button) findViewById(R.id.btMenosHamburger);
        btMenosRefrigerante = (Button) findViewById(R.id.btMenosRefrigerante);

        btGravarPedido = (Button) findViewById(R.id.btGravarPedido);

        txtQtdBatata = (TextView) findViewById(R.id.txtQtdBatata);
        txtQtdHamburger = (TextView) findViewById(R.id.txtQtdHamburger);
        txtQtdRefrigerante = (TextView) findViewById(R.id.txtQtdRefrigerante);
        txtTotalGeral = (TextView) findViewById(R.id.txtTotalGeral);

        btMaisBatata.setOnClickListener(this);
        btMaisHamburger.setOnClickListener(this);
        btMaisRefrigerante.setOnClickListener(this);

        btMenosBatata.setOnClickListener(this);
        btMenosHamburger.setOnClickListener(this);
        btMenosRefrigerante.setOnClickListener(this);

        btGravarPedido.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        qtdBatata = Integer.parseInt(txtQtdBatata.getText().toString());
        qtdHamburger = Integer.parseInt(txtQtdHamburger.getText().toString());
        qtdRefrigerante = Integer.parseInt(txtQtdRefrigerante.getText().toString());

        if (view.getId()==R.id.btMaisBatata){
            qtdBatata++;          // qtdBatata = qtdBatata + 1;
            txtQtdBatata.setText("" + qtdBatata);
        }
        if (view.getId()==R.id.btMaisHamburger){
            qtdHamburger++;
            txtQtdHamburger.setText("" + qtdHamburger);
        }
        if (view.getId()==R.id.btMaisRefrigerante){
            qtdRefrigerante++;
            txtQtdRefrigerante.setText("" + qtdRefrigerante);
        }
        if (view.getId()==R.id.btMenosBatata){
            qtdBatata--;          // qtdBatata = qtdBatata - 1;
            if (qtdBatata<0){
                qtdBatata = 0;
            }
            txtQtdBatata.setText("" + qtdBatata);
        }
        if (view.getId()==R.id.btMenosHamburger){
            qtdHamburger--;
            if (qtdHamburger<0){
                qtdHamburger = 0;
            }
            txtQtdHamburger.setText("" + qtdHamburger);
        }
        if (view.getId()==R.id.btMenosRefrigerante){
            qtdRefrigerante--;
            if (qtdRefrigerante<0){
                qtdRefrigerante = 0;
            }
            txtQtdRefrigerante.setText("" + qtdRefrigerante);
        }
        TotalBatata = valorBatata * qtdBatata;       // preco * quantidade
        TotalHamburger = valorHamburger * qtdHamburger;
        TotalRefrigerante = valorRefrigerante * qtdRefrigerante;
        TotalGeral = TotalBatata + TotalHamburger + TotalRefrigerante;

        txtTotalGeral.setText("R$ " + TotalGeral);

        if (view.getId()==R.id.btGravarPedido){
            String msg = "";
            if (TotalGeral == 0){
                msg = "Atenção - Não é possível gravar um pedido em branco!";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }else{
                GravarPedido();
            }
        }
    }
    public void GravarPedido() {
        BancoController bd = new BancoController(getBaseContext());
        String resultado;

        resultado = bd.insereDadosPedidos(email_login, qtdBatata, qtdHamburger, qtdRefrigerante,
                valorBatata, valorHamburger, valorRefrigerante, TotalGeral);

        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
        Limpar();
    }
    public void Limpar(){
        txtTotalGeral.setText("R$ 0.0");
        txtQtdRefrigerante.setText("0");
        txtQtdHamburger.setText("0");
        txtQtdBatata.setText("0");
    }
}