package com.example.sqliteempleados;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BorrarTabla extends AppCompatActivity {
    private TextView resultadoBorrarTabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_tabla);
        BaseDatosHelper usdbh = new BaseDatosHelper(this, "Empleados", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS Empleados");
        db.close();
        resultadoBorrarTabla = (TextView) findViewById(R.id.tv_resultadoborrartabla);
        resultadoBorrarTabla.setText("Se ha borrado la tabla");

        }

    public void cerrarVentana(View view) {
        finish();
    }

}