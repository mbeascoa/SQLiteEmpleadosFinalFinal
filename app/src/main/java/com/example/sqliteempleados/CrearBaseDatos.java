package com.example.sqliteempleados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CrearBaseDatos extends AppCompatActivity {

    SQLiteDatabase db=null;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_base_datos);
        resultado = (TextView) findViewById(R.id.tv_tablecreada);

        BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBHospital", null, 1);
        //Abrimos la base de datos 'DBContabilidad' en modo escritura
        db= usdbh.getWritableDatabase();

        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("codigoemp",1);
        nuevoRegistro.put("nombre", "Miguel");
        nuevoRegistro.put( "apellido" , "Beascoa");
        nuevoRegistro.put("oficio", "Ingeniero");
        nuevoRegistro.put( "direccion", "Arturo Soria");
        nuevoRegistro.put("fechaalta", "10/12/2021");
        nuevoRegistro.put("salario", 250000);
        nuevoRegistro.put("comision", 2500);
        nuevoRegistro.put("numerodepartamento", 10);

        //Insertamos el registro en la base de datos
        // primer parametro; tabla de la base de datos, Facturas
        //segundo parametro, siempre nulo menos en los autoincrementales

        db.insert("Empleados", null, nuevoRegistro);
        //El segundo parámetro lo obviaremos por el momento ya que tan sólo se
        //hace necesario en casos muy puntuales
        //(por ejemplo para poder insertar registros completamente vacíos)


        this.resultado.setText("ALTA CORRECTA");
        Toast.makeText(this, "Un resgistro inicial dado de alta correctamente", Toast.LENGTH_LONG).show();


    }

    public void cerrarVentana(View view) {
        finish();
    }

    public List<Empleados> DatosClientes() {

        List<Empleados> Lista = new ArrayList<>();
        Lista.add(new Empleados(1, "Miguel", "Beascoa", "Ingeniero", "Arturo Soria", "10/12/1962", 250000, 2500, 10 ));
        Lista.add(new Empleados(2, "Gil", "Sanches", "Informatico", "Paseo de la Castellana", "10/11/1963", 120000, 2000, 10));
        return Lista;
    }




}