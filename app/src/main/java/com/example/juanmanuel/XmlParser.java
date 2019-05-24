package com.example.juanmanuel;

import android.util.Xml;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    public static Boolean IsAdmin  = false;

    public  static Boolean validateLogin(String json){
        try{
            JSONObject type = new JSONObject(json);
            if (!"error".equals(type.getString("type") )){
                if("Admin".equals(type.getString("type"))){
                    IsAdmin = true;
                }
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        IsAdmin = false;
        return false;
    }

    public static List<Producto> getProductListFromXML(String xml){

        List<Producto> productos = new ArrayList<Producto>();
        Producto p = null;

        XmlPullParser xmlPullParser = Xml.newPullParser();
        try {
            xmlPullParser.setInput(new StringReader(xml));
            int event = xmlPullParser.getEventType();

            while (event != XmlPullParser.END_DOCUMENT){

                switch (event){
                    case XmlPullParser.START_TAG:
                        if ("producto".equals(xmlPullParser.getName())){
                            p = new Producto();
                        }
                        if ("nombre".equals(xmlPullParser.getName())){
                            p.setNombre(xmlPullParser.nextText());
                        }
                        if ("precio".equals(xmlPullParser.getName())){
                            p.setPrecio(new Double(xmlPullParser.nextText()));
                        }
                        if ("cantidad".equals(xmlPullParser.getName())){
                            p.setCantidad(new Integer(xmlPullParser.nextText()));
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if ("producto".equals(xmlPullParser.getName())){
                            productos.add(p);
                        }
                        break;
                }
                event = xmlPullParser.next();
            }
            return  productos;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
