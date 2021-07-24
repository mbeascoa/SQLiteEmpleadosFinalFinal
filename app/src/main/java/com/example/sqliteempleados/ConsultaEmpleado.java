package com.example.sqliteempleados;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ConsultaEmpleado extends AppCompatActivity {

    private RecyclerView miRecicler;
    private RecyclerView.Adapter miAdapter;
    List<Empleados> listadoDeEmpleados = new ArrayList<>();

    private static final String TAG = ConsultaEmpleado.class.getSimpleName();
    List<Empleados> datosEmpleados = new  ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_empleado);
        // Buscamos el control para cargar los datos
        miRecicler = (RecyclerView) findViewById(R.id.RV1);

        // Añadimos que  el tamaño del RecyclerView no se cambiará.
        // que tiene hijos (items) que tienen anchura y altura fijas. Permite // que el RecyclerView optimice mejor 
        miRecicler.setHasFixedSize(true);

        miRecicler.setLayoutManager(new LinearLayoutManager(this));
        //Especificamos el adaptador con la lista a visualizar
        listadoDeEmpleados = recuperarEmpleados();
        miAdapter = new Adaptador(listadoDeEmpleados);
        miRecicler.setAdapter(miAdapter);

    }

    private List<Empleados> recuperarEmpleados() {

        try {
            BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBHospital", null, 1);

            SQLiteDatabase db = usdbh.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM Empleados", null);
            //Nos aseguramos de que existe al menos un registro
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
              //int mCodigoemp,mSalario, mComision,mNumeroDepartamento;
              // String mNombre, mApellido, mOficio, mDireccion,mFechaAlta;
                do {
                    int codigoemp = Integer.parseInt(c.getString(c.getColumnIndexOrThrow("codigoemp")));
                    String nombre= c.getString(c.getColumnIndexOrThrow("nombre"));
                    String apellido= c.getString(c.getColumnIndexOrThrow("apellido")) ;
                    String oficio=c.getString(c.getColumnIndexOrThrow("oficio"));
                    String direccion=c.getString(c.getColumnIndexOrThrow("direccion"));
                    String fechaalta=c.getString(c.getColumnIndexOrThrow("fechaalta"));
                    int salario= Integer.parseInt(c.getString(c.getColumnIndexOrThrow("salario")));
                    int comision=Integer.parseInt(c.getString(c.getColumnIndexOrThrow("comision")));
                    int numerodepartamento=Integer.parseInt(c.getString(c.getColumnIndexOrThrow("numerodepartamento"))) ;
                    Empleados emp = new Empleados(codigoemp, nombre, apellido, oficio, direccion, fechaalta, salario, comision, numerodepartamento);
                    datosEmpleados.add(emp);
                    Log.d(TAG, emp.toString());
                    //Toast.makeText(this, emp.toString(), Toast.LENGTH_SHORT).show();
                } while (c.moveToNext());
            }

        } catch (Exception e) {
            System.out.println(e.toString());

        }
        return datosEmpleados;
    }


    public void cerrarVentana(View view) {
        finish();
    }


}
