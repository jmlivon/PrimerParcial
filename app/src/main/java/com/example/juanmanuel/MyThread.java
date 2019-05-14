package com.example.juanmanuel;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;

public class MyThread extends Thread {
    public Handler handler;
    public String url;
    public int imagenTexto;

    public MyThread(Handler handler, String url, int imagenTexto){
        this.handler = handler;
        this.url = url;
        this.imagenTexto = imagenTexto;
    }
    @Override
    public void run(){

        HttpConnection myConn = new HttpConnection(url);
        Message m = new Message();
        m.arg1 = this.imagenTexto;
        m.obj="";

        try{
            if (this.imagenTexto == 1){
                String response = null;
                response = myConn.getStrDataByGET();
                m.obj = response;
            }else {
                m.obj = myConn.getBytesDataByGET();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        this.handler.sendMessage(m);
    }
}
