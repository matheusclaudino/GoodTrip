package com.example.mclaudino.goodtrip;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class GastoListActivity extends ListActivity implements AdapterView.OnItemClickListener {
    private List<Map<String, Object>> gastos;
    private String dataAnterior = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.lista_gasto);

        String [] de = {"data", "descricao", "valor", "categoria"};
        int [] para = {R.id.data, R.id.descricao, R.id.valor, R.id.categoria};

        SimpleAdapter adapter = new SimpleAdapter(this, listarGastos(), R.layout.lista_gasto, de, para);

        adapter.setViewBinder(new GastoViewBinder());
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

        registerForContextMenu(getListView());
        /*setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaGastos()));
        ListView listView = getListView();*/

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String, Object> map = gastos.get(position);
        String descricao = (String) map.get("descricao");
        String msg = "Gasto selecionado: " + descricao;

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public List<Map<String, Object>> listarGastos(){
        gastos = new ArrayList<Map<String, Object>>();

        Map<String, Object> item = new HashMap<String, Object>();
        item.put("data", "04/02/2015");
        item.put("descricao", "Diária Hotel");
        item.put("valor", "R$ 260,00");
        item.put("categoria", R.color.categoria_hospedagem);
        gastos.add(item);

        item = new HashMap<String, Object>();
        item.put("data", "03/02/2015");
        item.put("descricao", "Wifi");
        item.put("valor", "R$ 7,00");
        item.put("categoria", R.color.categoria_outros);
        gastos.add(item);

        item = new HashMap<String, Object>();
        item.put("data", "02/02/2015");
        item.put("descricao", "Táxi Aeroporto - Hotel");
        item.put("valor", "R$ 34,00");
        item.put("categoria", R.color.categoria_transporte);
        gastos.add(item);

        item = new HashMap<String, Object>();
        item.put("data", "02/02/2015");
        item.put("descricao", "Sanduíche");
        item.put("valor", "R$ 19,90");
        item.put("categoria", R.color.categoria_alimentacao);
        gastos.add(item);

        return gastos;
    }

    private class GastoViewBinder implements SimpleAdapter.ViewBinder {

        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {

            if (view.getId() == R.id.data) {
                if (!dataAnterior.equals(data)) {
                    TextView textView = (TextView) view;
                    textView.setText(textRepresentation);
                    dataAnterior = textRepresentation;
                    view.setVisibility(View.VISIBLE);
                } else {
                    view.setVisibility(View.GONE);
                }

                return true;
            }
            if (view.getId() == R.id.categoria) {
                Integer id = (Integer) data;
                view.setBackgroundColor(getResources().getColor(id));
                return true;
            }
            return false;
        }
    }

    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.gasto_menu, menu);
    }

    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId() == R.id.remover){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            gastos.remove(info.position);
            getListView().invalidateViews();
            dataAnterior = "";
            //remover do banco
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
