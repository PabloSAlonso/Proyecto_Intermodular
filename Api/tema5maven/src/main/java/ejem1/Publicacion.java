package ejem1;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;



@Path("/publicaciones")
public class Publicacion implements Serializable {

    private int id_publicacion;
    private int id_usuario;
    private Date fecha_publicacion;
    private Blob imagen;
    private String descripcion;
    private int likes;
    private int comentarios;

    private ArrayList<Publicacion> publicaciones;

    private static final String DRIVER = "org.postgresql.Driver";

    public Publicacion() {
    }

    public Publicacion(int id_usuario, Date fecha_publicacion, Blob imagen,
            String descripcion, int likes, int comentarios) {
        this.id_usuario = id_usuario;
        this.fecha_publicacion = fecha_publicacion;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.likes = likes;
        this.comentarios = comentarios;
    }

    private static String readEnv(String key) {
        String value = System.getenv(key);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalStateException("Missing environment variable: " + key);
        }
        return value;
    }

    private Connection openConnection() throws Exception {
        Class.forName(DRIVER);
        String url = readEnv("DB_URL");
        String user = readEnv("DB_USER");
        String password = readEnv("DB_PASSWORD");
        return DriverManager.getConnection(url, user, password);
    }

    public int getId_publicacion() {
        return id_publicacion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public Date getFecha_publicacion() {
        return fecha_publicacion;
    }

    public Blob getImagen() {
        return imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getLikes() {
        return likes;
    }

    public int getComentarios() {
        return comentarios;
    }
}
