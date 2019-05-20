package com.example.juanmanuel;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback, MyOnItemClick {

    private RecyclerView myRecycler;
    private MyAdapter myAdapter;
    private List<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productos = new ArrayList<Producto>();

        Handler myHandler = new Handler(this);
        MyThread myThread = new MyThread(myHandler,"http://192.168.137.1:80/labo/productos.xml");
        myThread.start();

        myRecycler = (RecyclerView) findViewById(R.id.my_recycler);
        myRecycler.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        myRecycler.setLayoutManager(layoutManager);

        myAdapter = new MyAdapter(new ArrayList<Producto>(),this);
        myRecycler.setAdapter(myAdapter);
    }

    @Override
    public boolean handleMessage(Message msg) {
        // Obtengo mi lista de productos del atributo obj de msg
        this.productos=(List<Producto>)msg.obj;

        myAdapter.setProductos(this.productos);
        myAdapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public void onItemClick(int position, View v) {

        // Obtengo cantidad del producto
        int qty=this.productos.get(position).getCantidad();

        // Si se clickeo en agregar  sumamos 1
        if(R.id.ibAgregar==v.getId())
        {
            qty++;

            this.productos.get(position).setCantidad(qty);
            this.myAdapter.notifyItemChanged(position);
        }

        // Si se clickeo en quitar, si la cantidad es mayor a 0 quitamos 1
        if(R.id.ibQuitar==v.getId())
        {
            qty--;
            if(qty>=0)
            {
                this.productos.get(position).setCantidad(qty);
            }
            this.myAdapter.notifyItemChanged(position);
        }
    }
}
