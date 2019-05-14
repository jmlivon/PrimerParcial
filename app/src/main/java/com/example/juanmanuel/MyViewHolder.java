package com.example.juanmanuel;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView tvNombre;
    public TextView tvPrecio;
    public TextView tvCantidad;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        /*this.tvNombre = (TextView) itemView.findViewById(R.id.nombreProducto);
        this.tvPrecio = (TextView) itemView.findViewById(R.id.cantidad);
        this.tvCantidad = (TextView) itemView.findViewById(R.id.precio);*/
    }
}
