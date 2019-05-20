package com.example.juanmanuel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpConnection {

    private HttpURLConnection myHttpURLConnection;

    public HttpConnection(String url)
    {
        /*
         * El constructor recibe la url de conexión y con un metodo privado
         * Inicializa el myHttpURLConnection
         */
        myHttpURLConnection = crearHttpUrlConn(url);
    }

    private HttpURLConnection crearHttpUrlConn(String strUrl) {
        URL url;
        try {
            url = new URL(strUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            return  urlConnection;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isReady(){
        return (myHttpURLConnection != null);
    }

    public byte[] getBytesDataByGET() throws IOException {
        if(this.isReady()) {
            myHttpURLConnection.setRequestMethod("GET");
            myHttpURLConnection.connect();
            int response = myHttpURLConnection.getResponseCode();
            if (response == 200) {
                InputStream is = myHttpURLConnection.getInputStream();
                return readFully(is);
            } else
                throw new IOException();
        }
        return null;
    }

    private byte[] readFully(InputStream is) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = is.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        is.close();
        return baos.toByteArray();
    }

    public String getStrDataByGET() throws IOException {
        byte[] bytes = getBytesDataByGET();
        return new String(bytes,"UTF-8");
    }
}
