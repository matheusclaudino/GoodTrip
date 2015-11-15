package com.example.mclaudino.goodtrip;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.util.Calendar;

public class GastoActivity extends Activity {
    //atributos
    private int ano, mes, dia;
    private Button data_gasto;
    private Spinner categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gasto);

        //Criando e preenchendo o spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categorias, android.R.layout.simple_spinner_dropdown_item);
        categoria = (Spinner) findViewById(R.id.categoria);
        categoria.setAdapter(adapter);

        //Pegando a data atual do sistema
        Calendar calendar = Calendar.getInstance();
        ano = calendar.get( Calendar.YEAR );
        mes = calendar.get ( Calendar.MONTH );
        dia = calendar.get ( Calendar.DAY_OF_MONTH );

        //Setando data atual para o botão
        data_gasto = (Button) findViewById(R.id.data);
        data_gasto.setText(dia + "/" + ( mes+1 ) + "/" + ano);
    }

    public void selecionarData(View view){
        //método depreciado, alterar pelo substituto.
        showDialog(view.getId());
    }

    //método acionado assim que o botão é pressionado, chamado pelo método showDialog, retornando um DataPickerDialog
    protected Dialog onCreateDialog(int id){
        if(R.id.data == id){
            return new DatePickerDialog(this, listener, ano, mes, dia);
        }
        return null;
    }

    //método acionado assim que a data é alterada, e altera o texto do botão para a nova data selecionada
    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            ano = year;
            mes = monthOfYear;
            dia = dayOfMonth;

            data_gasto.setText(dia + "/" + (mes + 1) + "/" + ano);
        }

    };


    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.gasto_activity_menu, menu);
        return true;
    }

    public boolean onMenuItemSelected(int featuredId, MenuItem item){
        switch(item.getItemId()){
            case R.id.novo_gasto:
                startActivity(new Intent(this, GastoActivity.class));
                return true;

            case R.id.remover_gasto:
                //remover do banco
                return true;

            default:
                return super.onMenuItemSelected(featuredId, item);
        }
    }
}
