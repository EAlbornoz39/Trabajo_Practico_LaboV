package com.example.trabajo_practico_labv;

import android.os.Handler;
import android.os.Message;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class HiloHttp extends Thread{

    Handler handler;
    String urlImg;
    boolean img;

    public HiloHttp (Handler handler, String urlImg){

    }

    public HiloHttp(String urlImg, Handler handler, boolean img) {
        this.handler = handler;
        this.urlImg = urlImg;
        this.img = img;
    }

    @Override
    public void run() {
        try{
            EjecutarHttp ejecutarHttp = new EjecutarHttp();
            byte[] url = ejecutarHttp.obtenerRespuesta(this.urlImg);
            Message msg = new Message();
            msg.obj = url;
            handler.sendMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
