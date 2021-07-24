package com.example.sqliteempleados;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder>  {

    private List<Empleados> listaEmpleados;
    int posicionseleccionada = 0;
    private static final String TAG = Adaptador.class.getSimpleName();

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
        holder.cardview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                posicionseleccionada = position;

                if (posicionseleccionada == position) {
                    holder.txtnombre.setTextColor(Color.RED);


                } else {
                    holder.txtnombre.setTextColor(Color.DKGRAY);
                }
                notifyDataSetChanged();
                //Notificamos cambios para que el contenedr se entere y refresque los datos
                Intent i = new Intent(holder.itemView.getContext(), DetalleEmpleado.class);

                i.putExtra("NUMEROEMP", listaEmpleados.get(position).getCodigoemp());
                holder.itemView.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaEmpleados.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtnombre, txtapellido, txtoficio, txtsalario;
        private CardView cardview;
        public ViewHolder(View v) {
            super(v);
            txtnombre = (TextView) v.findViewById(R.id.txtNombre);
            txtapellido=(TextView) v.findViewById(R.id.txtapellido);
            txtoficio=(TextView) v.findViewById(R.id.txtoficio);
            txtsalario=(TextView) v.findViewById(R.id.txtsalario);
            cardview = (CardView) v.findViewById(R.id.cv1);

        }
    }

}