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
    boolean verificado = false;
    String codigoemp, nombre, apellido, oficio, direccion, fechaalta , salario, comision,numerodepartamento;
    SQLiteDatabase db=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_empleado);

        mCodigo = (EditText) findViewById(R.id.et_NumeroEmpleado_Alta);
        mNombre = (EditText) findViewById(R.id.et_Nombre_Alta);
        mApellido = (EditText) findViewById(R.id.et_Apellido_Alta);
        mOficio = (EditText) findViewById(R.id.et_Oficio_Alta);
        mDireccion = (EditText) findViewById(R.id.et_Direccion_Alta);
        mFecha = (EditText) findViewById(R.id.et_Fecha_Alta);
        mSalario = (EditText) findViewById(R.id.et_Salario_Alta);
        mComision = (EditText) findViewById(R.id.et_Comision_Alta);
        mNumeroDepartamento = (EditText) findViewById(R.id.et_NumeroDepartamento_Alta);
    }

        public void grabarBaseDatos(View view) {

            while (!verificado){
                codigoemp = mCodigo.getText().toString();
                nombre = mNombre.getText().toString();
                apellido = mApellido.getText().toString();
                oficio = mOficio.getText().toString();
                direccion = mDireccion.getText().toString();
                fechaalta = mFecha.getText().toString();
                salario = mSalario.getText().toString();
                comision = mComision.getText().toString();
                numerodepartamento = mNumeroDepartamento.getText().toString();
            if (codigoemp ==null) {
             mCodigo.setText("Introduzca un código del empleado, no puede ser un valor nulo");
             verificado = false;
            }
            if (nombre == null) {
             mNombre.setText("Introduzca un nombre del empleado, no puede ser un valor nulo");
             verificado = false;
            }
            if(apellido == null){
             mApellido.setText("Introduzca un apellido del empleado, no puede ser un valor nulo");
             verificado = false;
            }
            if(oficio == null){
             mOficio.setText("Introduzca un oficio del empleado, no puede ser un valor nulo");
             verificado = false;
            }
            if(direccion == null){
             mDireccion.setText("Introduzca una direccion del empleado, no puede ser un valor nulo");
             verificado = false;
            }
            if(salario == null){
             mSalario.setText("Introduzca un salario del empleado, no puede ser un valor nulo");
             verificado = false;
            }
            if(comision == null){
             mComision.setText("Introduzca una comisión del empleado, no puede ser un valor nulo");
             verificado = false;
            }
            if(numerodepartamento == null){
             mNumeroDepartamento.setText("Introduzca un número de departamento del empleado, no puede ser un valor nulo");
             verificado = false;
            }
            if(numerodepartamento != null && comision != null && salario != null && direccion != null && oficio != null && apellido != null && nombre != null && codigoemp != null){
             verificado = true;
            }
        } //while ...

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
        //if(db != null){

        db.insert("Empleados", null, nuevoRegistro);
        //El segundo parámetro lo obviaremos por el momento ya que tan sólo se
        //hace necesario en casos muy puntuales
        //(por ejemplo para poder insertar registros completamente vacíos)

        this.tv_ResultadoAlta.setText("ALTA CORRECTA");
        Toast.makeText(this, "Resgistro dado de alta correctamente", Toast.LENGTH_LONG).show();
      //  }

    }

    public void cerrarVentana(View view) {
        finish();
    }
}