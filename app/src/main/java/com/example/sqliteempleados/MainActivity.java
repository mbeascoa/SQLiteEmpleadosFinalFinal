package com.example.sqliteempleados;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  private Button btncrear, btnconsultar, btnalta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btncrear = (Button)findViewById(R.id.btn_crearbasedatos);
        btnconsultar= (Button) findViewById(R.id.btn_consultar_empleados);
        btnalta = (Button) findViewById(R.id.btn_altaempleados);




    }

    public void alta(View view){
        Intent i = new Intent (this, AltaEmpleado.class);
        startActivity(i);

    }

    public void consulta(View view){
        Intent i = new Intent (this, ConsultaEmpleado.class);
        startActivity(i);

    }
    public void crear(View view){
        Intent i = new Intent(this,CrearBaseDatos.class );
        startActivity(i);
    }
}
