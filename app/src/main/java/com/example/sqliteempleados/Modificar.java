package com.example.sqliteempleados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Modificar extends AppCompatActivity {
    private EditText consulxid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        consulxid = (EditText) findViewById(R.id.et_consultaporid);

        String id = consulxid.getText().toString();
        BaseDatosHelper usdbh = new BaseDatosHelper(this, "Empleados", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        actualizaDato(db, id);
        db.close();
    }


    private void actualizaDato(SQLiteDatabase db, String id) {
        ContentValues actualizaReg = new ContentValues();
        actualizaReg.put("nombre", "usuario6");
        //Actualizamos el registroenla base de datos
        db.update("Usuarios", actualizaReg, "cod=6", null);
    }

    public void cerrarVentana(View view) {
        finish();
    }
}