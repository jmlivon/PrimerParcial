package com.example.juanmanuel;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    private RecyclerView myRecycler;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRecycler = (RecyclerView) findViewById(R.id.my_recycler);
        myRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        myRecycler.setLayoutManager(layoutManager);

        Handler myHandler = new Handler(this);
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }
}
