package com.example.appmovil.ApiRest;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Api_Inserts {
    private static final String TAG = "Api_Inserts";
    private static final String API_ROOT = "http://10.0.2.2:8080/tema5maven/rest";

    public interface ApiInsertCallback {
        void onResult(boolean success);
    }

    public void addPost(int userId, String description, String imageBase64, String postType, ApiInsertCallback callback) {
        insertPosts(userId, description, imageBase64, postType, callback);
    }

    public void insertPosts(int userId, String description, String imageBase64, String habitType, ApiInsertCallback callback) {
        new Thread(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL(API_ROOT + "/publicaciones/insertar");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setConnectTimeout(12000);
                connection.setReadTimeout(12000);
                connection.setDoOutput(true);

                JSONObject json = new JSONObject();
                json.put("id_usuario", userId);
                json.put("fecha_publicacion", new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date()));
                json.put("imagen", imageBase64);
                json.put("descripcion", description == null ? "" : description);
                json.put("likes", 0);
                json.put("comentarios", 0);

                try (OutputStream os = connection.getOutputStream()) {
                    os.write(json.toString().getBytes(StandardCharsets.UTF_8));
                }

                int code = connection.getResponseCode();
                callback.onResult(code == HttpURLConnection.HTTP_OK || code == HttpURLConnection.HTTP_CREATED);
            } catch (IOException | JSONException e) {
                Log.e(TAG, "Error adding post", e);
                callback.onResult(false);
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }).start();
    }

    public void insertUsuario(String name, String username, String email, String password, ApiInsertCallback callback) {
        new Thread(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL(API_ROOT + "/usuarios/insertar");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setConnectTimeout(12000);
                connection.setReadTimeout(12000);
                connection.setDoOutput(true);

                String firstName = name;
                String lastName = " "; 
                if (name != null && name.contains(" ")) {
                    int lastSpace = name.lastIndexOf(' ');
                    firstName = name.substring(0, lastSpace);
                    lastName = name.substring(lastSpace + 1);
                }

                JSONObject json = new JSONObject();
                json.put("id", 0);
                json.put("nombre", firstName);
                json.put("apellidos", lastName);
                json.put("nickname", username == null ? "" : username);
                json.put("email", email == null ? "" : email);
                json.put("password", password == null ? "" : password);
                json.put("foto_perfil", "");

                try (OutputStream os = connection.getOutputStream()) {
                    os.write(json.toString().getBytes(StandardCharsets.UTF_8));
                }

                int code = connection.getResponseCode();
                callback.onResult(code == HttpURLConnection.HTTP_OK || code == HttpURLConnection.HTTP_CREATED);
            } catch (IOException | JSONException e) {
                Log.e(TAG, "Error inserting user", e);
                callback.onResult(false);
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }).start();
    }

    public void followUser(int followerId, int followingId, ApiInsertCallback callback) {
        callback.onResult(true);
    }

    public void updateUserProfile(int userId, String description, String avatarBase64, ApiInsertCallback callback) {
        callback.onResult(true);
    }

    public void likeHabit(int habitId, int userId, ApiInsertCallback callback) {
        callback.onResult(true);
    }

    public void unlikeHabit(int habitId, int userId, ApiInsertCallback callback) {
        callback.onResult(true);
    }

    public void unfollowUser(int followerId, int followingId, ApiInsertCallback callback) {
        callback.onResult(true);
    }
}
