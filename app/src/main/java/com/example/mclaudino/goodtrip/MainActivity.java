package com.example.mclaudino.goodtrip;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText usuario_edit_text;
    private EditText senha_edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        this.usuario_edit_text = (EditText) findViewById(R.id.usuario);
        this.senha_edit_text = (EditText) findViewById(R.id.senha);
    }

    public void entrarOnClick(View view){
        String usuario = this.usuario_edit_text.getText().toString();
        String senha = this.senha_edit_text.getText().toString();

        if(usuario.equals("matheus") && senha.equals("4321")){
            Intent intent = new Intent(this, DashBoardActivity.class);
            startActivity(intent);

        } else{
            String msgErro = getString(R.string.erro_autentificacao);
            Toast.makeText(MainActivity.this,msgErro ,Toast.LENGTH_LONG ).show();

        }
    }
}
