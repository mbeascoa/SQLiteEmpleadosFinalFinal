package com.example.sqliteempleados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ConsultaPorNombre extends AppCompatActivity {
    private EditText consulxnombre;
    private TextView codcxn, nomcxn, apecxn, oficxn,dircxn, salcxn, comcxn, numdecxn, feccxn, conta;
    List<Empleados> datosEmpleados = new  ArrayList<>();
    private int contador = 0;
    private int indice1 = 0;
    private int indice2 = 0;
    private int vuelta = 0;
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button consultar, otraconsulta, siguiente, anterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_por_nombre);

        codcxn = (TextView) findViewById(R.id.et_id_consultapornombre);
        nomcxn = (TextView) findViewById(R.id.et_nombre_consultapornombre);
        apecxn = (TextView) findViewById(R.id.et_apellidos_cosultapornombre);
        oficxn = (TextView) findViewById(R.id.et_oficio_cosultapornombre);
        dircxn= (TextView) findViewById(R.id.et_direccion_cosultapornombre);
        salcxn = (TextView) findViewById(R.id.et_salario_cosultapornombre);
        comcxn=(TextView) findViewById(R.id.et_comision_cosultapornombre);
        numdecxn=(TextView) findViewById(R.id.et_numero_departamento_cosultapornombre);
        feccxn= (TextView) findViewById(R.id.et_fechacosultapornombre);

        consulxnombre = (EditText) findViewById(R.id.et_cosultapornombre);
        conta= (TextView)findViewById(R.id.tv_contador);

        consultar= (Button) findViewById(R.id.btn_consultarpornombre);
        otraconsulta = (Button) findViewById(R.id.btn_otraconsulta_cosultapornombre);
        siguiente = (Button) findViewById(R.id.btn_siguiente);
        anterior = (Button) findViewById(R.id.btn_anterior);
        siguiente.setVisibility(View.INVISIBLE);
        anterior.setVisibility(View.INVISIBLE);


    }


    public void consultarDatos(View view ) {
        try {

            //Ocultamos el teclado al presionar el boton
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(consulxnombre.getWindowToken(), 0);

            String nomb = consulxnombre.getText().toString();
            consultar.setVisibility(View.GONE);
            //consultar.setVisibility(View.INVISIBLE);

                BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBHospital", null, 1);
                SQLiteDatabase db = usdbh.getReadableDatabase();


                String[] args = new String[]{nomb};
                String sql = "Select * from Empleados where nombre =?";
                Cursor c = db.rawQuery(sql, args);

                if (c.moveToFirst()) {

                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        int codigoemp = Integer.parseInt(c.getString(c.getColumnIndexOrThrow("codigoemp")));
                        String nombre = c.getString(c.getColumnIndexOrThrow("nombre"));
                        String apellido = c.getString(c.getColumnIndexOrThrow("apellido"));
                        String oficio = c.getString(c.getColumnIndexOrThrow("oficio"));
                        String direccion = c.getString(c.getColumnIndexOrThrow("direccion"));
                        String fechaAlta = c.getString(c.getColumnIndexOrThrow("fechaalta"));
                        int salario = Integer.parseInt(c.getString(c.getColumnIndexOrThrow("salario")));
                        int comision = Integer.parseInt(c.getString(c.getColumnIndexOrThrow("comision")));
                        int numeroDepartamento = Integer.parseInt(c.getString(c.getColumnIndexOrThrow("numerodepartamento")));

                        Empleados emp = new Empleados();
                        emp.setCodigoemp(codigoemp);
                        emp.setNombre(nombre);
                        emp.setApellido(apellido);
                        emp.setOficio(oficio);
                        emp.setDireccion(direccion);
                        emp.setFechaAlta(fechaAlta);
                        emp.setSalario(salario);
                        emp.setComision(comision);
                        emp.setNumeroDepartamento(numeroDepartamento);
                        Log.i(TAG, "creado empleado ");

                        datosEmpleados.add(emp);
                    } while (c.moveToNext());

                    if (datosEmpleados.size()<=1) {
                        conta.setText(" Se ha encontrado un único registro con ese nombre ");

                    } else {
                        siguiente.setVisibility(View.VISIBLE);
                        anterior.setVisibility(View.VISIBLE);
                    }
                    visibilidad(true);
                    c.moveToFirst();
                    obtener(c);
                    indice1 = contador + 1;
                    indice2 = datosEmpleados.size();
                    conta.setText(" Registro " + indice1 + " de un total de " + indice2);
                } else {
                    visibilidad(false);
                }

            } catch(Exception e){
                Log.d(TAG, "ERROR: " + e.toString());
            }
        }


        public void visibilidad(Boolean bool) {
        if( bool) {
            codcxn.setVisibility(View.VISIBLE);
            nomcxn.setVisibility(View.VISIBLE);
            apecxn.setVisibility(View.VISIBLE);
            oficxn.setVisibility(View.VISIBLE);
            dircxn.setVisibility(View.VISIBLE);
            salcxn.setVisibility(View.VISIBLE);
            comcxn.setVisibility(View.VISIBLE);
            numdecxn.setVisibility(View.VISIBLE);
            feccxn.setVisibility(View.VISIBLE);

        } else {
            codcxn.setVisibility(View.GONE);
            nomcxn.setVisibility(View.GONE);
            apecxn.setVisibility(View.GONE);
            oficxn.setVisibility(View.GONE);
            dircxn.setVisibility(View.GONE);
            salcxn.setVisibility(View.GONE);
            comcxn.setVisibility(View.GONE);
            numdecxn.setVisibility(View.GONE);
            feccxn.setVisibility(View.GONE);
        }


    }

    //  pasar al siguiente registro
    public void siguiente(View view) {

        try {
            String nomb = consulxnombre.getText().toString();

            BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBHospital", null, 1);
            SQLiteDatabase db = usdbh.getReadableDatabase();

            String[] args = new String[]{nomb};
            String sql = "Select * from Empleados where nombre =?";
            Cursor c = db.rawQuery(sql, args);

            vuelta +=1;
            if( vuelta <= 0) {
                vuelta = 1;
            }
            if (vuelta >= datosEmpleados.size()+1) {
                vuelta = 1;
            }

            if (c.moveToFirst()) {
             if ((vuelta <= (datosEmpleados.size())) && (vuelta>0 )) {
                    c.moveToFirst();
                    for (int i=2; i<=vuelta ; i++) {
                            c.moveToNext();
                        }
                    obtener(c);
                    }


                    conta.setText(" Registro "+ vuelta+" de un total de "+ indice2);


            } else {
                conta.setText(" No se encontaron Registros");
                visibilidad(false);
            }

        } catch (Exception e){
                    Log.d(TAG, "ERROR: " + e.toString());
                }

    }


    //pasar al anterior registro

    public void anterior(View view) {


        try {
            String nomb = consulxnombre.getText().toString();
            BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBHospital", null, 1);
            SQLiteDatabase db = usdbh.getReadableDatabase();

            String[] args = new String[]{nomb};
            String sql = "Select * from Empleados where nombre =?";
            Cursor c = db.rawQuery(sql, args);

            vuelta-=1;
            if (c.moveToFirst()) {
                if (vuelta >=datosEmpleados.size()) {
                    vuelta = datosEmpleados.size()-1;
                }
                if( vuelta == 0) {
                    vuelta = datosEmpleados.size();
                }
                if ((vuelta <= datosEmpleados.size()) && (vuelta>0 )) {
                    for (int i=2; i<=vuelta ; i++) {
                        c.moveToNext();
                    }

                    obtener(c);
                    conta.setText(" Registro "+ vuelta+" de un total de "+ indice2);

                }
            }
        } catch (Exception e){
            Log.d(TAG, "ERROR: " + e.toString());
        }

    }

    private void obtener(Cursor c){

        codcxn.setText(c.getString(c.getColumnIndexOrThrow("codigoemp")));
        nomcxn.setText(c.getString(c.getColumnIndexOrThrow("nombre")));
        apecxn.setText(c.getString(c.getColumnIndexOrThrow("apellido")));
        oficxn.setText(c.getString(c.getColumnIndexOrThrow("oficio")));
        dircxn.setText(c.getString(c.getColumnIndexOrThrow("direccion")));
        salcxn.setText(c.getString(c.getColumnIndexOrThrow("salario")));
        comcxn.setText(c.getString(c.getColumnIndexOrThrow("comision")));
        numdecxn.setText(c.getString(c.getColumnIndexOrThrow("numerodepartamento")));
        feccxn.setText(c.getString(c.getColumnIndexOrThrow("fechaalta")));

    }


    public void cerrarVentana(View view) {
        finish();
    }
    public void otraconsulta(View view) {
        consultar.setVisibility(View.VISIBLE);
    }
}