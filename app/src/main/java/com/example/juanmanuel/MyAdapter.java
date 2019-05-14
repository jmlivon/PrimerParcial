package com.example.juanmanuel;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    List<Producto> productos;

    public MyAdapter(List<Producto>productos){
        this.productos = productos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =null;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout,parent,false);

        MyViewHolder myViewHoleder = new MyViewHolder(v);
        return myViewHoleder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Producto p = this.productos.get(position);

        holder.tvNombre.setText(p.getNombre());
        holder.tvPrecio.setText(p.getPrecio().toString());
        holder.tvCantidad.setText(p.getCantidad().toString());
    }

    @Override
    public int getItemCount() {
        return this.productos.size();
    }

    public void setProductos(List<Producto>p){
        this.productos=p;
    }
}
