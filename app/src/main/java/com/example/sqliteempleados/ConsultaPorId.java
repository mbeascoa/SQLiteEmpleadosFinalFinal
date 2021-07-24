package com.example.sqliteempleados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ConsultaPorId extends AppCompatActivity {
    private EditText consulxid;
    private TextView nomcxi, apecxi, oficxi,dircxi, salcxi, comcxi, numdecxi, feccxi;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_por_id);
        nomcxi = (TextView) findViewById(R.id.et_nombre_consultaporid);
        apecxi = (TextView) findViewById(R.id.et_apellido_consultaporid);
        oficxi = (TextView) findViewById(R.id.et_oficio_consultaporid);
        dircxi= (TextView) findViewById(R.id.et_direccion_consultaporid);
        salcxi = (TextView) findViewById(R.id.et_salario_consultaporid);
        comcxi=(TextView) findViewById(R.id.et_comision_consultaporid);
        numdecxi=(TextView) findViewById(R.id.et_numero_departamento_consultaporid);
        feccxi= (TextView) findViewById(R.id.et_fechacosultaporid);

        consulxid = (EditText) findViewById(R.id.et_consultaporid);


    }


    public void consultarDatos(View view) {
        try {
            String id = consulxid.getText().toString();
            //Escondemos el teclado al pulsa el boton consultar
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(consulxid.getWindowToken(), 0);

            BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBHospital", null, 1);
            SQLiteDatabase db = usdbh.getReadableDatabase();


            String[] args = new String[]{id};
            String sql = "Select * from Empleados where codigoemp =?";
            Cursor c = db.rawQuery(sql, args);
            c.moveToFirst();

            nomcxi.setText(c.getString(c.getColumnIndexOrThrow("nombre")));
            apecxi.setText(c.getString(c.getColumnIndexOrThrow("apellido")));
            oficxi.setText(c.getString(c.getColumnIndexOrThrow("oficio")));
            dircxi.setText(c.getString(c.getColumnIndexOrThrow("direccion")));
            salcxi.setText(c.getString(c.getColumnIndexOrThrow("salario")));
            comcxi.setText(c.getString(c.getColumnIndexOrThrow("comision")));
            numdecxi.setText(c.getString(c.getColumnIndexOrThrow("numerodepartamento")));
            feccxi.setText(c.getString(c.getColumnIndexOrThrow("fechaalta")));
            db.close();
        } catch (Exception e) {
            Log.d(TAG, "ERROR: " + e.toString());
        }
    }

    public void cerrarVentana(View view) {
        finish();
    }
}