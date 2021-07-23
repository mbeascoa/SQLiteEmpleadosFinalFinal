package com.example.sqliteempleados;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    private int vuelta = 1;
    private static final String TAG = MainActivity.class.getSimpleName();

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


    }


    public void consultarDatos(View view ) {
        try {
            String nomb = consulxnombre.getText().toString();
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
                    Log.i(TAG, "creado empleado " + contador);

                    datosEmpleados.add(emp);
                } while (c.moveToNext());

                c.moveToFirst();
                //int codig = datosEmpleados.get(1).getCodigoemp();

                codcxn.setText(c.getString(c.getColumnIndexOrThrow("codigoemp")));
                nomcxn.setText(c.getString(c.getColumnIndexOrThrow("nombre")));
                apecxn.setText(c.getString(c.getColumnIndexOrThrow("apellido")));
                oficxn.setText(c.getString(c.getColumnIndexOrThrow("oficio")));
                dircxn.setText(c.getString(c.getColumnIndexOrThrow("direccion")));
                salcxn.setText(c.getString(c.getColumnIndexOrThrow("salario")));
                comcxn.setText(c.getString(c.getColumnIndexOrThrow("comision")));
                numdecxn.setText(c.getString(c.getColumnIndexOrThrow("numerodepartamento")));
                feccxn.setText(c.getString(c.getColumnIndexOrThrow("fechaalta")));
                indice1= contador+1;
                indice2 = datosEmpleados.size();
                conta.setText(" Registro " + indice1 + " de un total de " + indice2);
                db.close();

            }
        } catch(Exception e){
                Log.d(TAG, "ERROR: " + e.toString());

            }
    }
    public void siguiente(View view) {


        try {
            String nomb = consulxnombre.getText().toString();
            BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBHospital", null, 1);
            SQLiteDatabase db = usdbh.getReadableDatabase();

            String[] args = new String[]{nomb};
            String sql = "Select * from Empleados where nombre =?";
            Cursor c = db.rawQuery(sql, args);


            if (c.moveToFirst()) {
                if (vuelta <= datosEmpleados.size()) {


                    switch (vuelta) {
                        // declaración case
                        // los valores deben ser del mismo tipo de la expresión
                        case 0:
                            c.moveToNext();
                            obtener(c);
                            vuelta += 1;
                            break; // break es opcional
                        case 1:
                            c.moveToNext();
                            obtener(c);
                            conta.setText(" Registro 2 de  un total de " + indice2);
                            vuelta += 1;
                            break; // break es opcional

                        case 2:
                            c.moveToNext();
                            c.moveToNext();
                            obtener(c);
                            conta.setText(" Registro 3 de  un total de " + indice2);
                            vuelta += 1;
                            break; // break es opcional
                        case 3:
                            c.moveToNext();
                            c.moveToNext();
                            c.moveToNext();
                            obtener(c);
                            conta.setText(" Registro 4 de  un total de " + indice2);
                            vuelta += 1;
                            break; // break es opcional
                        case 4:
                            c.moveToNext();
                            c.moveToNext();
                            c.moveToNext();
                            c.moveToNext();
                            obtener(c);
                            conta.setText(" Registro 5 de  un total de " + indice2);
                            vuelta += 1;
                            break; // break es opcional
                        case 5:
                            c.moveToNext();
                            c.moveToNext();
                            c.moveToNext();
                            c.moveToNext();
                            c.moveToNext();
                            obtener(c);
                            vuelta += 1;
                            break; // break es opcional
                        default:
                            conta.setText(" Navegue con el siguiente o anterior,  sólo se muestran 5 resultados");
                            break;
                    }
                }
            }
        } catch (Exception e){
                    Log.d(TAG, "ERROR: " + e.toString());
                }

    }

    public void anterior(View view) {


        try {
            String nomb = consulxnombre.getText().toString();
            BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBHospital", null, 1);
            SQLiteDatabase db = usdbh.getReadableDatabase();

            String[] args = new String[]{nomb};
            String sql = "Select * from Empleados where nombre =?";
            Cursor c = db.rawQuery(sql, args);


            if (c.moveToFirst()) {
                if ((vuelta <= datosEmpleados.size()) && (vuelta>=0 )) {
                    switch (vuelta) {
                        // declaración case
                        // los valores deben ser del mismo tipo de la expresión
                        case 1:
                            c.moveToNext();
                            c.moveToPrevious();
                            obtener(c);
                            conta.setText(" Registro 2");
                            vuelta -= 1;
                            break; // break es opcional

                        case 2:
                            c.moveToNext();
                            c.moveToNext();
                            c.moveToPrevious();
                            obtener(c);
                            conta.setText(" Registro 3");
                            vuelta -= 1;
                            break; // break es opcional
                        case 3:
                            c.moveToNext();
                            c.moveToNext();
                            c.moveToNext();
                            c.moveToPrevious();
                            obtener(c);
                            conta.setText(" Registro 4 ");
                            vuelta -= 1;
                            break; // break es opcional
                        case 4:
                            c.moveToNext();
                            c.moveToNext();
                            c.moveToNext();
                            c.moveToNext();
                            c.moveToPrevious();
                            obtener(c);
                            conta.setText(" Registro 5");
                            vuelta -= 1;
                            break; // break es opcional
                        case 5:
                            c.moveToNext();
                            c.moveToNext();
                            c.moveToNext();
                            c.moveToNext();
                            c.moveToNext();
                            obtener(c);
                            conta.setText(" Registro 6");
                            vuelta -= 1;
                            break; // break es opcional
                        default:
                            conta.setText("  Navegue con el siguiente o anterior,  sólo se muestran 5 resultados");
                            break;
                    }
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
                    /*codcxn.setText(datosEmpleados.get(contador).getCodigoemp());
                    nomcxn.setText(datosEmpleados.get(contador).getNombre());
                    apecxn.setText(datosEmpleados.get(contador).getApellido());
                    dircxn.setText(datosEmpleados.get(contador).getDireccion());
                    oficxn.setText(datosEmpleados.get(contador).getOficio());
                    salcxn.setText(datosEmpleados.get(contador).getSalario());
                    comcxn.setText(datosEmpleados.get(contador).getComision());
                    feccxn.setText(datosEmpleados.get(contador).getFechaAlta());
                    numdecxn.setText(datosEmpleados.get(contador).getNumeroDepartamento());
                    indice1= contador+1;
                    conta.setText(" Registro " + indice1 + " de un total de " + indice2);
                    contador += 1;

                     */

    }


    public void cerrarVentana(View view) {
        finish();
    }
}