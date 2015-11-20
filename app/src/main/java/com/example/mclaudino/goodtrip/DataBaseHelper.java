package com.example.mclaudino.goodtrip;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by M. Claudino on 20/11/2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String BANCO_DADOS = "boaviagem";
    private static final int VERSAO = 1;

    public DataBaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE viagem(_id INTEGER PRIMARY KEY," +
                                        " destino TEXT," +
                                        "tipo_viagem INTEGER" +
                                        "data_chegada DATE," +
                                        "data_saida DATE," +
                                        "orcamento DOUBLE," +
                                        "qtd_pessoas INTEGER);");

        db.execSQL("CREATE TABLE gasto(_id INTEGER PRIMARY KEY," +
                                        "categoria TEXT," +
                                        "data DATE," +
                                        "valor DOUBLE," +
                                        "descricao TEXT" +
                                        "local TEXT" +
                                        "viagem_id INTEGER" +
                                        "FOREIGN KEY(viagem_id) REFERENCES viagem(_id);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
