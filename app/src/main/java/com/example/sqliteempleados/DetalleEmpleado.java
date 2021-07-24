package com.example.sqliteempleados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetalleEmpleado extends AppCompatActivity {

    private static final String TAG = DetalleEmpleado.class.getSimpleName();
    private TextView coddet, nomdet, apedet, ofidet, saldet, comdet, dirdet, fecdet, numdepdet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_empleado);
        coddet = (TextView) findViewById(R.id.et_codigo_detalle);
         nomdet = (TextView) findViewById(R.id.et_nombre_detalle);
         apedet = (TextView) findViewById(R.id.et_apellido_detalle);
         ofidet= (TextView) findViewById(R.id.et_oficio_detalle);
        saldet = (TextView) findViewById(R.id.et_salario_detalle);
        comdet = (TextView) findViewById(R.id.et_comision_detalle);
        dirdet= (TextView) findViewById(R.id.et_direccion_detalle);
        fecdet = (TextView) findViewById(R.id.et_fechaalta_detalle);
        numdepdet = (TextView) findViewById(R.id.et_numdepartam_detalle);

        //Recogemos los parámetros enviados por el primer Activity a través del método getExras
        Bundle bundle = getIntent().getExtras();
        int codigoId = bundle.getInt("NUMEROEMP");
        String codigo = String.valueOf(codigoId);
        Log.i(TAG, "onCreate Detalle Empleado  : " + codigo);
        leerdetalle(codigo);

    }



    public void leerdetalle(String codigo) {
        try {

            BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBHospital", null, 1);
            SQLiteDatabase db = usdbh.getReadableDatabase();


            String[] args = new String[]{codigo};
            String sql = "Select * from Empleados where codigoemp =?";
            Cursor c = db.rawQuery(sql, args);
            c.moveToFirst();

            coddet.setText(c.getString(c.getColumnIndexOrThrow("codigoemp")));
            nomdet.setText(c.getString(c.getColumnIndexOrThrow("nombre")));
            apedet.setText(c.getString(c.getColumnIndexOrThrow("apellido")));
            ofidet.setText(c.getString(c.getColumnIndexOrThrow("oficio")));
            dirdet.setText(c.getString(c.getColumnIndexOrThrow("direccion")));
            saldet.setText(c.getString(c.getColumnIndexOrThrow("salario")));
            comdet.setText(c.getString(c.getColumnIndexOrThrow("comision")));
            numdepdet.setText(c.getString(c.getColumnIndexOrThrow("numerodepartamento")));
            fecdet.setText(c.getString(c.getColumnIndexOrThrow("fechaalta")));
            db.close();
        } catch (Exception e) {
            Log.d(TAG, "ERROR: " + e.toString());
        }
    }


    public void cerrarVentana(View view) {
        finish();
    }
}