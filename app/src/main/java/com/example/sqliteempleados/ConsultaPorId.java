package com.example.sqliteempleados;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class ConsultaPorId extends AppCompatActivity {
    private EditText consulxid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_por_id);
        consulxid = (EditText) findViewById(R.id.et_consultaporid);
        String id = consulxid.getText().toString();
        BaseDatosHelper usdbh = new BaseDatosHelper(this, "Empleados", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        consultarDatos(db, id);
        db.close();

    }


    private void consultarDatos(SQLiteDatabase db, String id) {
        String[] args = new String[]{id};
        String sql = "Select * from Usuarios where codigoemp =?";
        Cursor c = db.rawQuery(sql, args);
        int idIndex = c.getColumnIndexOrThrow("codigoemp");

        ArrayList<String> miArrayList = new ArrayList<String>();
        while (c.moveToNext()) {
            miArrayList.add(c.getString(1)); // ó idIndex
        }
    }

    public void cerrarVentana(View view) {
        finish();
    }
}