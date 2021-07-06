package com.example.sqliteempleados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AltaEmpleado extends AppCompatActivity {

    private EditText mCodigo, mNombre, mApellido, mOficio, mDireccion, mFecha, mSalario, mComision, mNumeroDepartamento;
    private TextView tv_ResultadoAlta;
    SQLiteDatabase db=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_empleado);
        mCodigo= (EditText) findViewById(R.id.et_NumeroEmpleado_Alta);
        mNombre = (EditText) findViewById(R.id.et_NombreAlta);
        mApellido= (EditText) findViewById(R.id.et_apellido);
        mOficio= (EditText) findViewById(R.id.et_oficio);
        mDireccion= (EditText) findViewById(R.id.et_direccion);
        mFecha= (EditText) findViewById(R.id.et_fecha);
        mSalario= (EditText) findViewById(R.id.et_salario);
        mComision= (EditText) findViewById(R.id.et_comision);
        mNumeroDepartamento= (EditText) findViewById(R.id.et_NumeroDepartamento);

        BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBHospital", null, 1);
        //Abrimos la base de datos 'DBContabilidad' en modo escritura
        db= usdbh.getWritableDatabase();

        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("codigoemp",mCodigo.getText().toString());
        nuevoRegistro.put("nombre", mNombre.getText().toString());
        nuevoRegistro.put( "apellido" , mApellido.getText().toString());
        nuevoRegistro.put("oficio", mOficio.getText().toString());
        nuevoRegistro.put( "direccion", mDireccion.getText().toString());
        nuevoRegistro.put("fechaalta", mFecha.getText().toString());
        nuevoRegistro.put("salario", mSalario.getText().toString());
        nuevoRegistro.put("comision", mComision.getText().toString());
        nuevoRegistro.put("numerodepartamento", mNumeroDepartamento.getText().toString());

        //Insertamos el registro en la base de datos
        // primer parametro; tabla de la base de datos, Facturas
        //segundo parametro, siempre nulo menos en los autoincrementales

        db.insert("Empleados", null, nuevoRegistro);
        //El segundo parámetro lo obviaremos por el momento ya que tan sólo se
        //hace necesario en casos muy puntuales
        //(por ejemplo para poder insertar registros completamente vacíos)


        this.tv_ResultadoAlta.setText("ALTA CORRECTA");
        Toast.makeText(this, "Resgistro dado de alta correctamente", Toast.LENGTH_LONG).show();


    }

    public void cerrarVentana(View view) {
        finish();
    }
}