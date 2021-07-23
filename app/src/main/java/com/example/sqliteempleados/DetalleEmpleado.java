package com.example.sqliteempleados;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import android.database.Cursor;
import android.view.View;


import java.util.ArrayList;

public class DetalleEmpleado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_empleado);
        BaseDatosHelper usdbh = new BaseDatosHelper(this, "BDHospital", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        //borrarDato(db);
        db.close();

    }





    private void consultarDatos(SQLiteDatabase db) {
        String[] args = new String[]{"usuario6"};
        String sql = "Select * from Usuarios where nombre =?";
        Cursor c = db.rawQuery(sql, args);
        int idIndex = c.getColumnIndexOrThrow("cod");
        ArrayList<String> miArrayList = new ArrayList<String>();
        while (c.moveToNext()) {
            miArrayList.add(c.getString(1)); // ó idIndex
        }
    }


    public void cerrarVentana(View view) {
        finish();
    }
}