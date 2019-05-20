package com.example.juanmanuel;

import android.os.Handler;
import android.os.Message;
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
        String response = null;
        HttpConnection myConn = new HttpConnection(url);
        Message m = new Message();
        try{
            // Guardo en response el xml obtenido sin parsear
            response = myConn.getStrDataByGET();
            // Guardo en el atributo obj de Message el xml parseado que se obtuvo de la conexión
            m.obj = XmlParser.getProductListFromXML(response);
        }catch (IOException e) {
            e.printStackTrace();
        }

        this.handler.sendMessage(m);
    }
}