package com.example.mclaudino.goodtrip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.zip.Inflater;

public class ViagemActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_viagem);
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
