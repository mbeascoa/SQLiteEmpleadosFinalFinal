package com.example.sqliteempleados;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class BorrarEmpleado extends AppCompatActivity {
    private EditText consulxid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_empleado);
        consulxid = (EditText) findViewById(R.id.et_codigoempborraremp);
        String id = consulxid.getText().toString();
        BaseDatosHelper usdbh = new BaseDatosHelper(this, "Empleados", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        borrarDato(db, id);
        db.close();
    }


    private void borrarDato(SQLiteDatabase db, String id) {
        String tabla = "Empleados";
        String whereClause = "codigoemp=?";
        String[] whereArgs = new String[]{id};
        db.delete(tabla, whereClause, whereArgs);
    }

    public void cerrarVentana(View view) {
        finish();
    }
}