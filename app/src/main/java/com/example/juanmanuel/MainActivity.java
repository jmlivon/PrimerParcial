package com.example.juanmanuel;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

        //HttpConnection httpConnection = new HttpConnection("http://192.168.2.221/labo/productos.xml");
        //String xmlProductos = httpConnection.getStrDataByGET();

        Handler myHandler = new Handler(this);
        MyThread myThread = new MyThread(myHandler,"http://192.168.2.221:80/labo/productos.xml");
        myThread.start();

        myRecycler = (RecyclerView) findViewById(R.id.my_recycler);
        myRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        myRecycler.setLayoutManager(layoutManager);

        myAdapter = new MyAdapter(new ArrayList<Producto>(),this);

        myRecycler.setAdapter(myAdapter);
       // myAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean handleMessage(Message msg) {
        Log.d("XMLllll",msg.obj.toString());
        myAdapter.setProductos((List<Producto>)msg.obj);
        this.productos=(List<Producto>)msg.obj;
        myAdapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public void onItemClick(int position, View v) {
        Log.d("CLickkk","clickkkkk");
        int i;
        if(R.id.ibAgregar==v.getId())
        {

            i=this.productos.get(position).getCantidad();
            i=i+1;

            this.productos.get(position).setCantidad(i);
            this.myAdapter.notifyItemChanged(position);
        }

        if(R.id.ibQuitar==v.getId())
        {

            i=this.productos.get(position).getCantidad();
            i=i-1;
            if(i>=0)
            {
                this.productos.get(position).setCantidad(i);
            }

            this.myAdapter.notifyItemChanged(position);
        }
    }
}
