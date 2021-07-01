package com.example.trabajo_practico_labv;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class EjecutarHttp
{

    public byte[] obtenerRespuesta (String urlString)
    {

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (200 == urlConnection.getResponseCode())
            {
                InputStream is = urlConnection.getInputStream();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int lenght = 0;

                while ((lenght = is.read(buffer)) != -1)
                {
                    baos.write(buffer, 0, lenght);
                }

                is.close();

                Log.d("Respuesta server", baos.toString());

                return baos.toByteArray();
            } else {
                throw new RuntimeException("Error en la Comunicacion" + urlConnection.getResponseCode());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

}
