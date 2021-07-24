package com.example.sqliteempleados;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class BorrarEmpleado extends AppCompatActivity {
    private static final String TAG = BorrarEmpleado.class.getSimpleName();
    private EditText consulxid;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_empleado);
        consulxid = (EditText) findViewById(R.id.et_codigoempborraremp);
        resultado = (TextView) findViewById(R.id.tv_resultado_borrar_empleado);


    }


    public void borrarDato(View view ) {

        String id = consulxid.getText().toString();
        BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBHospital", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        String tabla = "Empleados";
        String whereClause = "codigoemp=?";
        String[] whereArgs = new String[]{id};
        db.delete(tabla, whereClause, whereArgs);
        db.close();
        resultado.setText("Registro borrado , muchas gracias");
        Log.i(TAG, "Registro borrado , muchas gracias");
    }

    public void cerrarVentana(View view) {
        finish();
    }
}