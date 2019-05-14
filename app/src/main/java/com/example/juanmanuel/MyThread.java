package com.example.juanmanuel;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

public class MyThread extends Thread {
    public Handler handler;
    public String url;

    public MyThread(Handler handler, String url){
        this.handler = handler;
        this.url = url;
    }
    @Override
    public void run(){
        Log.d("Entro a run","runnnn");
        String response = null;
        HttpConnection myConn = new HttpConnection(url);
        Message m = new Message();
        try{
            response = myConn.getStrDataByGET();
            Log.d("responseeeee",response);
            m.obj = XmlParser.obtenerProductos(response);
        }catch (IOException e) {
            e.printStackTrace();
        }

        this.handler.sendMessage(m);
    }
}
