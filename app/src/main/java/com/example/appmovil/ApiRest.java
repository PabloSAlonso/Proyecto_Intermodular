package com.example.appmovil;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiRest {
    public void subirUsuario(String nombre, String nick){
        new Thread(() -> {
            try {
                // URL proyecto maven del Proyecto Intermodular
                URL url = new URL("http://192.130.0.14:8080/apirest/rest/usuarios/insertar"); //10.0.2.2
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true); //Voy a escribir en body
                JSONObject json = new JSONObject();
                json.put("nombre", nombre);
                json.put("nickname", nick);
                System.out.println(json);
                try (OutputStream os = connection.getOutputStream()){
                    os.write(json.toString().getBytes(StandardCharsets.UTF_8)); //Enviar body
                } catch (IOException i){

                }
                int code = connection.getResponseCode(); //Forzar envio
                Log.i("CODIGO APIREST", "El codigo resultante es:" + code);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
