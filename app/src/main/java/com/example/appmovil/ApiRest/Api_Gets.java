package com.example.appmovil.ApiRest;

import android.util.Log;

import com.example.appmovil.Dao.User;
import com.example.appmovil.Publicaciones.Habit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Api_Gets {
    private static final String API_ROOT = "https://proyecto-intermodular-kpzv.onrender.com/rest";
    private static final String TAG = "Api_Gets";

    public interface ApiCallback {
        void onResult(boolean success, int userId);
    }

    public interface UserCallback {
        void onResult(User user);
    }

    public interface StatsCallback {
        void onResult(int followers, int following, int habits);
    }

    public interface HabitsCallback {
        void onResult(ArrayList<Habit> habits);
    }

    public interface UsersCallback {
        void onResult(ArrayList<User> users);
    }

    public interface BooleanCallback {
        void onResult(boolean result);
    }

    public void getUser(String emailOrNick, String password, ApiCallback callback) {
        new Thread(() -> {
            try {
                ArrayList<User> users = fetchUsers();
                User matched = null;
                String probe = emailOrNick == null ? "" : emailOrNick.trim().toLowerCase(Locale.ROOT);

                for (User user : users) {
                    String username = user.getUsername() == null ? "" : user.getUsername().toLowerCase(Locale.ROOT);
                    String email = user.getEmail() == null ? "" : user.getEmail().toLowerCase(Locale.ROOT);
                    if (probe.equals(username) || probe.equals(email)) {
                        matched = user;
                        break;
                    }
                }

                if (matched == null || matched.getEmail() == null || matched.getEmail().isEmpty()) {
                    callback.onResult(false, -1);
                    return;
                }

                HttpURLConnection connection = null;
                try {
                    URL url = new URL(API_ROOT + "/usuarios/login");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("Accept", "application/json");
                    connection.setConnectTimeout(12000);
                    connection.setReadTimeout(12000);
                    connection.setDoOutput(true);

                    JSONObject body = new JSONObject();
                    body.put("email", matched.getEmail());
                    body.put("password", password);

                    try (OutputStream os = connection.getOutputStream()) {
                        os.write(body.toString().getBytes(StandardCharsets.UTF_8));
                    }

                    int code = connection.getResponseCode();
                    callback.onResult(code == HttpURLConnection.HTTP_OK, code == HttpURLConnection.HTTP_OK ? matched.getId() : -1);
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "Error en login", e);
                callback.onResult(false, -1);
            }
        }).start();
    }

    public void getSocialFeed(int userId, HabitsCallback callback) {
        getHabits(callback);
    }

    public void searchUsers(String query, UsersCallback callback) {
        new Thread(() -> {
            try {
                ArrayList<User> users = fetchUsers();
                if (query == null || query.trim().isEmpty()) {
                    callback.onResult(users);
                    return;
                }

                String q = query.toLowerCase(Locale.ROOT).trim();
                ArrayList<User> filtered = new ArrayList<>();
                for (User user : users) {
                    String name = user.getName() == null ? "" : user.getName().toLowerCase(Locale.ROOT);
                    String username = user.getUsername() == null ? "" : user.getUsername().toLowerCase(Locale.ROOT);
                    String email = user.getEmail() == null ? "" : user.getEmail().toLowerCase(Locale.ROOT);
                    if (name.contains(q) || username.contains(q) || email.contains(q)) {
                        filtered.add(user);
                    }
                }
                callback.onResult(filtered);
            } catch (Exception e) {
                Log.e(TAG, "Error searching users", e);
                callback.onResult(new ArrayList<>());
            }
        }).start();
    }

    public void getHabits(HabitsCallback callback) {
        new Thread(() -> callback.onResult(fetchHabitsFromEndpoint(API_ROOT + "/publicaciones/todas"))).start();
    }

    public void getUserById(int userId, UserCallback callback) {
        new Thread(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL(API_ROOT + "/usuarios/obtenerId/" + userId);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");
                connection.setConnectTimeout(12000);
                connection.setReadTimeout(12000);

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    JSONObject obj = new JSONObject(readResponse(connection));
                    callback.onResult(parseUser(obj));
                } else {
                    callback.onResult(null);
                }
            } catch (Exception e) {
                Log.e(TAG, "Error getting user by ID", e);
                callback.onResult(null);
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }).start();
    }

    public void getUserStats(int userId, StatsCallback callback) {
        getHabitsByUserId(userId, habits -> callback.onResult(0, 0, habits == null ? 0 : habits.size()));
    }

    public void getHabitsByUserId(int userId, HabitsCallback callback) {
        new Thread(() -> callback.onResult(fetchHabitsFromEndpoint(API_ROOT + "/publicaciones/usuario/" + userId))).start();
    }

    public void checkIfLiked(int habitId, int userId, BooleanCallback callback) {
        callback.onResult(false);
    }

    public void checkIfFollowing(int followerId, int followingId, BooleanCallback callback) {
        callback.onResult(false);
    }

    public void getFollowers(int userId, UsersCallback callback) {
        callback.onResult(new ArrayList<>());
    }

    public void getFollowing(int userId, UsersCallback callback) {
        callback.onResult(new ArrayList<>());
    }

    private ArrayList<User> fetchUsers() throws IOException, JSONException {
        HttpURLConnection connection = null;
        ArrayList<User> users = new ArrayList<>();

        try {
            URL url = new URL(API_ROOT + "/usuarios/obtenerTodos");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(12000);
            connection.setReadTimeout(12000);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                JSONArray array = new JSONArray(readResponse(connection));
                for (int i = 0; i < array.length(); i++) {
                    users.add(parseUser(array.getJSONObject(i)));
                }
            }
            return users;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private ArrayList<Habit> fetchHabitsFromEndpoint(String endpoint) {
        HttpURLConnection connection = null;
        ArrayList<Habit> habits = new ArrayList<>();

        try {
            Map<Integer, User> usersById = new HashMap<>();
            for (User user : fetchUsers()) {
                usersById.put(user.getId(), user);
            }

            URL url = new URL(endpoint);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(12000);
            connection.setReadTimeout(12000);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                JSONArray array = new JSONArray(readResponse(connection));
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    int userId = obj.optInt("id_usuario", -1);
                    User user = usersById.get(userId);
                    String username = user != null ? user.getUsername() : ("user" + userId);
                    String avatar = user != null ? user.getAvatarUrl() : "";

                    habits.add(new Habit(
                            obj.optInt("id_publicacion", 0),
                            username,
                            avatar,
                            obj.optString("imagen", ""),
                            obj.optString("descripcion", ""),
                            "Publicacion",
                            obj.optInt("likes", 0),
                            obj.optInt("comentarios", 0),
                            obj.optString("fecha_publicacion", "")
                    ));
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Error loading publications", e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return habits;
    }

    private User parseUser(JSONObject obj) {
        User user = new User(
                obj.optInt("id", 0),
                obj.optString("nombre", ""),
                obj.optString("nickname", ""),
                obj.optString("foto_perfil", "")
        );
        user.setEmail(obj.optString("email", ""));
        user.setDescription("");
        return user;
    }

    private String readResponse(HttpURLConnection connection) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }
}
