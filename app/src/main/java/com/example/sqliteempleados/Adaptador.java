package com.example.sqliteempleados;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder>  {

    private List<Empleados> listaEmpleados;

    public Adaptador(List<Empleados> ListaEmpleado) {

        this.listaEmpleados = ListaEmpleado;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_datos, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String nombre = listaEmpleados.get(position).getNombre();
        holder.txtnombre.setText(nombre);
        String apellido = listaEmpleados.get(position).getApellido();
        holder.txtapellido.setText(apellido);
        String oficio = listaEmpleados.get(position).getOficio();
        holder.txtoficio.setText(oficio);
        int salario = listaEmpleados.get(position).getSalario();
        holder.txtsalario.setText(String.valueOf(salario));

    }

    @Override
    public int getItemCount() {
        return listaEmpleados.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtnombre, txtapellido, txtoficio, txtsalario;
        public ViewHolder(View v) {
            super(v);
            txtnombre = (TextView) v.findViewById(R.id.txtNombre);
            txtapellido=(TextView) v.findViewById(R.id.txtapellido);
            txtoficio=(TextView) v.findViewById(R.id.txtoficio);
            txtsalario=(TextView) v.findViewById(R.id.txtsalario);

        }
    }

}