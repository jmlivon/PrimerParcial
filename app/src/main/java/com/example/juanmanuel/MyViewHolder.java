package com.example.juanmanuel;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tvNombre;
    public TextView tvPrecio;
    public TextView tvCantidad;
    public ImageButton ibAdd;
    public ImageButton ibRemove;
    public MyOnItemClick listener;
    public int position;

    public MyViewHolder(@NonNull View itemView, MyOnItemClick listener) {
        super(itemView);
        this.listener = listener;
        this.tvNombre = (TextView) itemView.findViewById(R.id.tvNombreProducto);
        this.tvPrecio = (TextView) itemView.findViewById(R.id.tvPrecio);
        this.tvCantidad = (TextView) itemView.findViewById(R.id.tvCantidad);
        this.ibAdd = (ImageButton) itemView.findViewById(R.id.ibAgregar);
        this.ibRemove = (ImageButton) itemView.findViewById(R.id.ibQuitar);
        this.ibAdd.setOnClickListener(this);
        this.ibRemove.setOnClickListener(this);
        itemView.setOnClickListener(this);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void onClick(View v) {
        this.listener.onItemClick(this.position,v);
    }
}
