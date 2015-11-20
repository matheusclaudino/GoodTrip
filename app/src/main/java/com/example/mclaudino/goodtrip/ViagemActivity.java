package com.example.mclaudino.goodtrip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Calendar;
import java.util.zip.Inflater;

public class ViagemActivity extends Activity {
    private DataBaseHelper helper;
    private EditText destino, qtdPessoas, orcamento;
    private RadioGroup radioGroup;
    private int ano, mes, dia;
    private Button dataChegada, dataSaida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_viagem);

        //pegando data atual do sistema
        Calendar calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);

        //criando referências para as views que contém os dados informados pelo usuário
        dataChegada = (Button) findViewById(R.id.data_chegada);
        dataSaida = (Button) findViewById(R.id.data_saida);

        destino = (EditText) findViewById(R.id.destino);
        qtdPessoas = (EditText) findViewById(R.id.qtd_pessoas);
        orcamento = (EditText) findViewById(R.id.orcamento);
        radioGroup = (RadioGroup) findViewById(R.id.tipo_viagem);

        //prepara acesso ao banco de dados
        helper = new DataBaseHelper(this);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.viagem_menu, menu);
        return true;
    }

    public boolean onMenuItemSelected(int featuredId, MenuItem item){
        switch (item.getItemId()){
            case R.id.novo_gasto:
                startActivity(new Intent(this, GastoActivity.class));
                return true;

            case R.id.remover:
                //remover do banco de dados
                return true;

            default:
                return super.onMenuItemSelected(featuredId, item);
        }
    }
}
