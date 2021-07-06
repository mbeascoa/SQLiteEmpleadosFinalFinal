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
    ArrayList<String> arrayEmpleados = new ArrayList<>();
    ArrayList<String> DatosEmpleados = new ArrayList<>();
    private static final String TAG = MainActivity.class.getSimpleName();

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
       // DatosEmpleados = recuperarEmpleados();
       // miAdapter = new Adaptador(DatosEmpleados);
        miRecicler.setAdapter(miAdapter);

    }

    /*private List<Empleados> recuperarEmpleados() {
        ArrayList<String> arrayEmpleados = new ArrayList<>();

        try {
            //String[] args = new String[]{null};
            String codigo = "";


            BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBContabilidad", null, 1);

            SQLiteDatabase db = usdbh.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT codigo FROM Facturas", null);
            //Nos aseguramos de que existe al menos un registro
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    codigo = c.getString(0);
                    Log.d(TAG, "recuperarFactura numero de código: "+ codigo);
                    Toast.makeText(this, "recuperarFactura numero de código: "+ codigo, Toast.LENGTH_SHORT).show();
                    arrayFactura.add(codigo);
                } while (c.moveToNext());
            }

            this.mResultadoC.setText("Código usuario:" + codigo);
            Toast.makeText(this, "Código Usuario " + codigo, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            System.out.println(e.toString());

        }
        return arrayFactura;
    }
*/

    public void cerrarVentana(View view) {
        finish();
    }


}
