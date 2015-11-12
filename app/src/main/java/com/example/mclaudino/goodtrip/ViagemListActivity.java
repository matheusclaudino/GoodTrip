package com.example.mclaudino.goodtrip;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ViagemListActivity extends ListActivity implements AdapterView.OnItemClickListener {
    private List<Map<String, Object>> viagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.lista_viagem);

        String[] de = {"imagem", "destino", "data", "total"};
        int[] para = {R.id.img_tipo_viagem, R.id.destino, R.id.data, R.id.gasto};

        SimpleAdapter adapter = new SimpleAdapter(this, listarViagens(), R.layout.lista_viagem, de, para);
        setListAdapter(adapter);
        /*setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listarViagens()));
        ListView listView = getListView();*/

        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Map<String, Object> map = viagens.get(position);

        String destino = (String) map.get("destino");
        String msg = "Viagem selecionada: " + destino;
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this, GastoListActivity.class));
    }

    private List<Map<String, Object>> listarViagens(){
        viagens = new ArrayList<Map<String, Object>>();

        Map<String, Object> item = new HashMap<String, Object>();

        item.put("imagem", R.drawable.briefcase_white);
        item.put("destino", "São Paulo");
        item.put("data", "02/02/2015 a 04/02/2015");
        item.put("total", "Gasto total R$ 314,98");
        viagens.add(item);

        item = new HashMap<String, Object>();
        item.put("imagem", R.drawable.emoticon_cool_white);
        item.put("destino", "Maceió");
        item.put("data", "14/05/2015 a 22/05/2015");
        item.put("total", "Gasto total R$ 25834,67");
        viagens.add(item);

        return viagens;
    }
}