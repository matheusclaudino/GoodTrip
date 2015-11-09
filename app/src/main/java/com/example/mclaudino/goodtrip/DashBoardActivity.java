package com.example.mclaudino.goodtrip;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DashBoardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
    }

    public void selecionarOpcao(View view){

        TextView textView = (TextView) view;
        String opcao = "Opção: " + textView.getText().toString();
        Toast.makeText(DashBoardActivity.this, opcao, Toast.LENGTH_SHORT).show();
    }
}
