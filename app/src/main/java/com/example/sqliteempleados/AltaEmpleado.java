package com.example.sqliteempleados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AltaEmpleado extends AppCompatActivity {

    private EditText mCodigo, mNombre, mApellido, mOficio, mDireccion, mFecha, mSalario, mComision, mNumeroDepartamento;
    private TextView tv_ResultadoAlta;
    boolean verificado = false;
    String codigoemp, nombre, apellido, oficio, direccion, fechaalta , salario, comision,numerodepartamento;
    SQLiteDatabase db=null;
    private Validaciones objetoValidar;  //objeto de nuestra clase validaciones
    private static final String TAG = MainActivity.class.getSimpleName();

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
            try {
                objetoValidar = new Validaciones();

                codigoemp = mCodigo.getText().toString();
                nombre = mNombre.getText().toString();
                apellido = mApellido.getText().toString();
                oficio = mOficio.getText().toString();
                direccion = mDireccion.getText().toString();
                fechaalta = mFecha.getText().toString();
                salario = mSalario.getText().toString();
                comision = mComision.getText().toString();
                numerodepartamento = mNumeroDepartamento.getText().toString();


                //validamos que los editext no esten vacíos
                if (!objetoValidar.Vacio(mCodigo) && !objetoValidar.Vacio(mNombre)
                        && !objetoValidar.Vacio(mApellido) && !objetoValidar.Vacio(mOficio)
                        && !objetoValidar.Vacio(mDireccion) && !objetoValidar.Vacio(mFecha)
                        && !objetoValidar.Vacio(mSalario) && !objetoValidar.Vacio(mComision)) {
                    //validamos que el campo numeroAlta tiene un valor string que puede castearse a numero
                    if (!objetoValidar.isNumeric(salario) && !objetoValidar.isNumeric(comision)
                            && !objetoValidar.isNumeric(numerodepartamento)) {
                        Toast.makeText(this, "Introduzca un número , por favor", Toast.LENGTH_SHORT).show();
                    } else {
                        mostrarDialogoConfirmacion(view);
                    }

                }
            } catch (Exception e) {
                Log.d(TAG, "ERROR: " + e.toString());
            }
        }

    public void mostrarDialogoConfirmacion(View view) {
        DialogoConfirmacion confirmacion  = new DialogoConfirmacion();
        confirmacion.show(getFragmentManager(), "Cuadro confirmación");
    }

    public void accionAceptar() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

            inputMethodManager.hideSoftInputFromWindow(mNumeroDepartamento.getWindowToken(), 0);

            mensajePersonalizado("Insertando Registro, gracias");
            BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBHospital", null, 1);
            //Abrimos la base de datos 'DBContabilidad' en modo escritura
            db = usdbh.getWritableDatabase();

            ContentValues nuevoRegistro = new ContentValues();
            nuevoRegistro.put("codigoemp", mCodigo.getText().toString());
            nuevoRegistro.put("nombre", mNombre.getText().toString());
            nuevoRegistro.put("apellido", mApellido.getText().toString());
            nuevoRegistro.put("oficio", mOficio.getText().toString());
            nuevoRegistro.put("direccion", mDireccion.getText().toString());
            nuevoRegistro.put("fechaalta", mFecha.getText().toString());
            nuevoRegistro.put("salario", mSalario.getText().toString());
            nuevoRegistro.put("comision", mComision.getText().toString());
            nuevoRegistro.put("numerodepartamento", mNumeroDepartamento.getText().toString());

            //Insertamos el registro en la base de datos
            // primer parametro; tabla de la base de datos, Facturas
            //segundo parametro, siempre nulo menos en los autoincrementales
            if (db != null) {
                db.insert("Empleados", null, nuevoRegistro);
                //El segundo parámetro lo obviaremos por el momento ya que tan sólo se
                //hace necesario en casos muy puntuales
                //(por ejemplo para poder insertar registros completamente vacíos)

                this.tv_ResultadoAlta.setText("ALTA CORRECTA");
                Toast.makeText(this, "Resgistro dado de alta correctamente", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Log.d(TAG, "ERROR: " + e.toString());

        }

    }

    public void accionCancelar() {
        mensajePersonalizado("Cancelando Envio");
    }

    public void mensajePersonalizado(String opcion) {
        Toast mensaje = new Toast(getApplicationContext());

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.capa_toast,
                (ViewGroup) findViewById(R.id.lytLayout));

        TextView txtMsg = (TextView)layout.findViewById(R.id.txtMensaje);
        txtMsg.setText(opcion);

        mensaje.setDuration(Toast.LENGTH_SHORT);
        mensaje.setView(layout);
        mensaje.show();
    }

    public void cerrarVentana(View view) {
        finish();
    }
}
