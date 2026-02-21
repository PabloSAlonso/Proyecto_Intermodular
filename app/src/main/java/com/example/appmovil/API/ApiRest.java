package com.example.appmovil.API;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import com.example.appmovil.Models.Publicacion;
import com.example.appmovil.Models.Usuario;

public class ApiRest {
    
    // API Base URL - Update this to your Render API URL
    private static final String BASE_URL = "https://proyecto-intermodular-kpzv.onrender.com";
    
    // SharedPreferences keys
    private static final String PREFS_NAME = "klyer_prefs";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_USER_NICKNAME = "user_nickname";
    private static final String KEY_USER_NOMBRE = "user_nombre";
    private static final String KEY_USER_APELLIDOS = "user_apellidos";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";

    /**
     * Save user data to SharedPreferences after successful login
     */
    public static void saveUserSession(Context context, Usuario usuario) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_USER_ID, usuario.getId());
        editor.putString(KEY_USER_EMAIL, usuario.getEmail());
        editor.putString(KEY_USER_NICKNAME, usuario.getNickname());
        editor.putString(KEY_USER_NOMBRE, usuario.getNombre());
        editor.putString(KEY_USER_APELLIDOS, usuario.getApellidos());
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.apply();
    }

    /**
     * Get logged in user ID
     */
    public static int getUserId(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(KEY_USER_ID, -1);
    }

    /**
     * Get logged in user email
     */
    public static String getUserEmail(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_USER_EMAIL, "");
    }

    /**
     * Get logged in user nickname
     */
    public static String getUserNickname(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_USER_NICKNAME, "");
    }

    /**
     * Get logged in user nombre
     */
    public static String getUserNombre(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_USER_NOMBRE, "");
    }

    /**
     * Get logged in user apellidos
     */
    public static String getUserApellidos(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_USER_APELLIDOS, "");
    }

    /**
     * Check if user is logged in
     */
    public static boolean isLoggedIn(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    /**
     * Logout - clear session
     */
    public static void logout(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * Get full user object from session
     */
    public static Usuario getLoggedUser(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        
        if (!prefs.getBoolean(KEY_IS_LOGGED_IN, false)) {
            return null;
        }
        
        Usuario usuario = new Usuario();
        usuario.setId(prefs.getInt(KEY_USER_ID, -1));
        usuario.setEmail(prefs.getString(KEY_USER_EMAIL, ""));
        usuario.setNickname(prefs.getString(KEY_USER_NICKNAME, ""));
        usuario.setNombre(prefs.getString(KEY_USER_NOMBRE, ""));
        usuario.setApellidos(prefs.getString(KEY_USER_APELLIDOS, ""));
        
        return usuario;
    }

    // ==================== CALLBACKS ====================

    public interface LoginCallback {
        void onLoginResult(boolean success, Usuario usuario, String errorMessage);
    }

    public interface RegisterCallback {
        void onRegisterResult(boolean success, String errorMessage);
    }

    public interface PublicationsCallback {
        void onPublicationsResult(boolean success, ArrayList<Publicacion> publicaciones, String errorMessage);
    }

    public interface PublicationCallback {
        void onPublicationResult(boolean success, String errorMessage);
    }

    // ==================== LOGIN ====================

    /**
     * Login user with email and password
     */
    public void loginUsuario(String email, String password, LoginCallback callback) {
        new Thread(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL(BASE_URL + "/rest/usuarios/obtener/" + email + "/" + password);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");
                connection.setConnectTimeout(15000);
                connection.setReadTimeout(15000);

                int responseCode = connection.getResponseCode();
                Log.d("ApiRest", "Login response code: " + responseCode);

                if (responseCode == 200) {
                    String response = readResponse(connection);
                    Usuario usuario = parseUsuario(response);
                    if (usuario != null) {
                        callback.onLoginResult(true, usuario, null);
                    } else {
                        callback.onLoginResult(false, null, "Error al procesar la respuesta del servidor");
                    }
                } else if (responseCode == 404) {
                    callback.onLoginResult(false, null, "Usuario no encontrado. Verifica tu correo electrónico");
                } else if (responseCode == 401) {
                    callback.onLoginResult(false, null, "Contraseña incorrecta");
                } else if (responseCode == 500) {
                    callback.onLoginResult(false, null, "Error del servidor. Intenta más tarde");
                } else {
                    callback.onLoginResult(false, null, "Error: " + responseCode);
                }
            } catch (Exception e) {
                Log.e("ApiRest", "Login error", e);
                callback.onLoginResult(false, null, "Error de conexión. Verifica tu conexión a internet");
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }).start();
    }

    // ==================== REGISTER ====================

    /**
     * Register a new user
     */
    public void registrarUsuario(String nombre, String apellidos, String nickname, 
                                   String email, String password, RegisterCallback callback) {
        new Thread(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL(BASE_URL + "/rest/usuarios/insertar");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(15000);
                connection.setReadTimeout(15000);

                JSONObject json = new JSONObject();
                json.put("nombre", nombre);
                json.put("apellidos", apellidos);
                json.put("nickname", nickname);
                json.put("email", email);
                json.put("password", password);
                json.put("fecha_nacimiento", new java.sql.Date(System.currentTimeMillis()).toString());
                json.put("fecha_creacion_cuenta", new java.sql.Date(System.currentTimeMillis()).toString());

                try (OutputStream os = connection.getOutputStream()) {
                    os.write(json.toString().getBytes(StandardCharsets.UTF_8));
                }

                int responseCode = connection.getResponseCode();
                String response = readResponse(connection);
                Log.d("ApiRest", "Register response code: " + responseCode);
                Log.d("ApiRest", "Register response: " + response);

                if (responseCode == 201 || responseCode == 200) {
                    callback.onRegisterResult(true, null);
                } else if (responseCode == 409) {
                    // Conflict - email or nickname already exists
                    callback.onRegisterResult(false, "El correo electrónico o nombre de usuario ya está en uso");
                } else if (responseCode == 400) {
                    // Bad request
                    callback.onRegisterResult(false, "Datos inválidos. Verifica que todos los campos sean correctos");
                } else {
                    callback.onRegisterResult(false, "Error al registrar usuario: " + responseCode);
                }
            } catch (Exception e) {
                Log.e("ApiRest", "Register error", e);
                callback.onRegisterResult(false, "Error de conexión. Verifica tu conexión a internet");
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }).start();
    }

    // ==================== PUBLICATIONS ====================

    /**
     * Get all publications
     */
    public void obtenerPublicaciones(PublicationsCallback callback) {
        new Thread(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL(BASE_URL + "/rest/publicaciones/todas");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");

                int responseCode = connection.getResponseCode();
                Log.d("ApiRest", "Publications response code: " + responseCode);

                if (responseCode == 200) {
                    String response = readResponse(connection);
                    ArrayList<Publicacion> publicaciones = parsePublicaciones(response);
                    callback.onPublicationsResult(true, publicaciones, null);
                } else {
                    callback.onPublicationsResult(false, null, "Error al obtener publicaciones");
                }
            } catch (Exception e) {
                Log.e("ApiRest", "Publications error", e);
                callback.onPublicationsResult(false, null, "Error de conexión: " + e.getMessage());
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }).start();
    }

    /**
     * Get publications by user ID
     */
    public void obtenerPublicacionesPorUsuario(int idUsuario, PublicationsCallback callback) {
        new Thread(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL(BASE_URL + "/rest/publicaciones/usuario/" + idUsuario);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");

                int responseCode = connection.getResponseCode();

                if (responseCode == 200) {
                    String response = readResponse(connection);
                    ArrayList<Publicacion> publicaciones = parsePublicaciones(response);
                    callback.onPublicationsResult(true, publicaciones, null);
                } else {
                    callback.onPublicationsResult(false, null, "Error al obtener publicaciones");
                }
            } catch (Exception e) {
                Log.e("ApiRest", "Publications by user error", e);
                callback.onPublicationsResult(false, null, "Error de conexión: " + e.getMessage());
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }).start();
    }

    /**
     * Create a new publication
     */
    public void crearPublicacion(int idUsuario, String descripcion, String imagenBase64, 
                                  PublicationCallback callback) {
        new Thread(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL(BASE_URL + "/rest/publicaciones/insertar");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                JSONObject json = new JSONObject();
                json.put("id_usuario", idUsuario);
                json.put("descripcion", descripcion);
                json.put("fecha_publicacion", new java.sql.Date(System.currentTimeMillis()).toString());
                json.put("likes", 0);
                json.put("comentarios", 0);
                
                // Add image as Base64 if provided
                if (imagenBase64 != null && !imagenBase64.isEmpty()) {
                    json.put("imagenBase64", imagenBase64);
                }

                try (OutputStream os = connection.getOutputStream()) {
                    os.write(json.toString().getBytes(StandardCharsets.UTF_8));
                }

                int responseCode = connection.getResponseCode();
                Log.d("ApiRest", "Create publication response code: " + responseCode);

                if (responseCode == 201 || responseCode == 200) {
                    callback.onPublicationResult(true, null);
                } else {
                    callback.onPublicationResult(false, "Error al crear publicación");
                }
            } catch (Exception e) {
                Log.e("ApiRest", "Create publication error", e);
                callback.onPublicationResult(false, "Error de conexión: " + e.getMessage());
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }).start();
    }

    /**
     * Delete a publication
     */
    public void eliminarPublicacion(int idPublicacion, PublicationCallback callback) {
        new Thread(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL(BASE_URL + "/rest/publicaciones/eliminar/" + idPublicacion);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("DELETE");

                int responseCode = connection.getResponseCode();

                if (responseCode == 200) {
                    callback.onPublicationResult(true, null);
                } else {
                    callback.onPublicationResult(false, "Error al eliminar publicación");
                }
            } catch (Exception e) {
                Log.e("ApiRest", "Delete publication error", e);
                callback.onPublicationResult(false, "Error de conexión: " + e.getMessage());
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }).start();
    }

    // ==================== HELPER METHODS ====================

    private String readResponse(HttpURLConnection connection) throws IOException {
        InputStream is;
        if (connection.getResponseCode() >= 200 && connection.getResponseCode() < 300) {
            is = connection.getInputStream();
        } else {
            is = connection.getErrorStream();
        }
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response.toString();
    }

    private Usuario parseUsuario(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            Usuario usuario = new Usuario();
            usuario.setId(jsonObject.optInt("id", -1));
            usuario.setNombre(jsonObject.optString("nombre", ""));
            usuario.setApellidos(jsonObject.optString("apellidos", ""));
            usuario.setNickname(jsonObject.optString("nickname", ""));
            usuario.setEmail(jsonObject.optString("email", ""));
            usuario.setPassword(null); // Never return password
            return usuario;
        } catch (Exception e) {
            Log.e("ApiRest", "Error parsing usuario", e);
            return null;
        }
    }

    private ArrayList<Publicacion> parsePublicaciones(String json) {
        ArrayList<Publicacion> publicaciones = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Publicacion publicacion = new Publicacion();
                publicacion.setId_publicacion(jsonObject.optInt("id_publicacion", 0));
                publicacion.setId_usuario(jsonObject.optInt("id_usuario", 0));
                publicacion.setDescripcion(jsonObject.optString("descripcion", ""));
                publicacion.setLikes(jsonObject.optInt("likes", 0));
                publicacion.setComentarios(jsonObject.optInt("comentarios", 0));
                
                // Parse date
                String fechaStr = jsonObject.optString("fecha_publicacion", "");
                if (!fechaStr.isEmpty()) {
                    publicacion.setFecha_publicacion(Date.valueOf(fechaStr));
                }
                
                // Parse image as Base64
                String imagenBase64 = jsonObject.optString("imagenBase64", "");
                if (imagenBase64.isEmpty()) {
                    // Try alternate field name
                    imagenBase64 = jsonObject.optString("imagen", "");
                }
                publicacion.setImagenBase64(imagenBase64);
                
                publicaciones.add(publicacion);
            }
        } catch (Exception e) {
            Log.e("ApiRest", "Error parsing publicaciones", e);
        }
        return publicaciones;
    }

    /**
     * Convert bitmap to Base64 string
     */
    public static String bitmapToBase64(Bitmap bitmap) {
        if (bitmap == null) return null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    /**
     * Convert Base64 string to bitmap
     */
    public static Bitmap base64ToBitmap(String base64String) {
        if (base64String == null || base64String.isEmpty()) return null;
        try {
            byte[] decodedBytes = Base64.decode(base64String, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        } catch (Exception e) {
            Log.e("ApiRest", "Error converting base64 to bitmap", e);
            return null;
        }
    }
}

