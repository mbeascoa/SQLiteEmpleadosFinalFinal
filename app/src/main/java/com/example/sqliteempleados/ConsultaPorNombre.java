package com.example.sqliteempleados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ConsultaPorNombre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_por_nombre);
    }


    public void cerrarVentana(View view) {
        finish();
    }
}