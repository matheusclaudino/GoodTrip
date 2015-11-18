package com.example.mclaudino.goodtrip;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViagemListActivity extends ListActivity implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener {

    private List<Map<String, Object>> viagens;
    private AlertDialog alertDialog;
    private AlertDialog dialogConfirmacao;
    private int viagemSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.lista_viagem);

        //Progress Bar
        //String[] de = {"imagem", "destino", "data", "total", "barraProgresso"};
        //int[] para = {R.id.img_tipo_viagem, R.id.destino, R.id.data, R.id.gasto, R.id.barraProgresso};

        String[] de = {"imagem", "destino", "data", "total"};
        int[] para = {R.id.img_tipo_viagem, R.id.destino, R.id.data, R.id.gasto};

        SimpleAdapter adapter = new SimpleAdapter(this, listarViagens(), R.layout.lista_viagem, de, para);
        setListAdapter(adapter);
        /*setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listarViagens()));
        ListView listView = getListView();*/

        getListView().setOnItemClickListener(this);

        this.alertDialog = criaAlertDialog();
        this.dialogConfirmacao = criaDialogConfirmacao();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Map<String, Object> map = viagens.get(position);

        String destino = (String) map.get("destino");
        String msg = "Viagem selecionada: " + destino;
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();

        this.viagemSelecionada = position;
        alertDialog.show();
        //startActivity(new Intent(this, GastoListActivity.class));
    }

    private List<Map<String, Object>> listarViagens(){
        viagens = new ArrayList<Map<String, Object>>();

        Map<String, Object> item = new HashMap<String, Object>();

        item.put("imagem", R.drawable.briefcase_white);
        item.put("destino", "São Paulo");
        item.put("data", "02/02/2015 a 04/02/2015");
        item.put("total", "Gasto total R$ 314,98");
        //Progress Bar
        //item.put("barraProgresso", new Double[]{500.0, 450.0, 314.98});
        viagens.add(item);

        item = new HashMap<String, Object>();
        item.put("imagem", R.drawable.emoticon_cool_white);
        item.put("destino", "Maceió");
        item.put("data", "14/05/2015 a 22/05/2015");
        item.put("total", "Gasto total R$ 25834,67");
        //Progress Bar
        //item.put("barraProgresso", new Double[]{30000.0, 28000.0, 25834.67});
        viagens.add(item);

        return viagens;
    }

    private AlertDialog criaAlertDialog(){
        final CharSequence[] itens = {
                getString(R.string.editar),
                getString(R.string.novo_gasto),
                getString(R.string.gastos_realizados),
                getString(R.string.remover_viagem) };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.opcoes);
        builder.setItems(itens, this);

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int item) {
     switch (item){
         case 0:
             startActivity(new Intent(this, ViagemActivity.class));
             break;

         case 1:
             startActivity(new Intent(this, GastoActivity.class));
             break;

         case 2:
             startActivity(new Intent(this, GastoListActivity.class));
             break;

         case 3:
             dialogConfirmacao.show();
             break;

         case DialogInterface.BUTTON_POSITIVE:
             viagens.remove(this.viagemSelecionada);
             getListView().invalidateViews();
             break;

         case DialogInterface.BUTTON_NEGATIVE:
             dialogConfirmacao.dismiss();
             break;
     }
    }

    private AlertDialog criaDialogConfirmacao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.confirmacao_exclusao_viagem);
        builder.setPositiveButton(getString(R.string.sim), this);
        builder.setNegativeButton(getString(R.string.nao), this);

        return builder.create();
    }

    //Progress Bar
    public boolean setViewValue(View view, Object data, String textRepresentation){

            if(view.getId() == R.id.barraProgresso){
                Double valores[] = (Double[]) data;
                ProgressBar progressBar = (ProgressBar) view;
                progressBar.setMax(valores[0].intValue());
                progressBar.setSecondaryProgress(valores[1].intValue());
                progressBar.setProgress(valores[2].intValue());

                return true;
            }
        return false;
    }

}
