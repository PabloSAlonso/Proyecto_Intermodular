package modelos;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

    @Path("/todas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPublicaciones() {
        String sql = "SELECT * FROM publicaciones ORDER BY id_publicacion DESC";

        try (Connection conexion = openConnection();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            publicaciones = new ArrayList<>();
            while (rs.next()) {
                Publicacion p = new Publicacion();
                p.id_publicacion = rs.getInt("id_publicacion");
                p.id_usuario = rs.getInt("id_usuario");
                p.fecha_publicacion = rs.getDate("fecha_publicacion");
                p.imagen = rs.getBlob("imagen");
                p.descripcion = rs.getString("descripcion");
                p.likes = rs.getInt("likes");
                p.comentarios = rs.getInt("comentarios");
                publicaciones.add(p);
            }

            return Response.ok(publicaciones).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al obtener publicaciones").build();
        }
    }

    @Path("/usuario/{id_usuario}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPublicacionesPorUsuario(@PathParam("id_usuario") int idUsuario) {
        String sql = "SELECT * FROM publicaciones WHERE id_usuario=? ORDER BY id_publicacion DESC";

        try (Connection conexion = openConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                publicaciones = new ArrayList<>();
                while (rs.next()) {
                    Publicacion p = new Publicacion();
                    p.id_publicacion = rs.getInt("id_publicacion");
                    p.id_usuario = rs.getInt("id_usuario");
                    p.fecha_publicacion = rs.getDate("fecha_publicacion");
                    p.imagen = rs.getBlob("imagen");
                    p.descripcion = rs.getString("descripcion");
                    p.likes = rs.getInt("likes");
                    p.comentarios = rs.getInt("comentarios");
                    publicaciones.add(p);
                }
            }

            return Response.ok(publicaciones).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al obtener publicaciones de usuario").build();
        }
    }

    @Path("/insertar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertarPublicacion(Publicacion p) {
        if (p == null || p.getId_usuario() <= 0 || p.getFecha_publicacion() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Datos de publicacion incompletos").build();
        }

        String sql = "INSERT INTO publicaciones "
                + "(id_usuario, fecha_publicacion, imagen, descripcion, likes, comentarios) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexion = openConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, p.getId_usuario());
            ps.setDate(2, p.getFecha_publicacion());
            if (p.getImagen() != null) {
                ps.setBlob(3, p.getImagen());
            } else {
                ps.setNull(3, Types.BINARY);
            }
            ps.setString(4, p.getDescripcion());
            ps.setInt(5, p.getLikes());
            ps.setInt(6, p.getComentarios());

            ps.executeUpdate();

            return Response.status(Response.Status.CREATED).entity(p).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al insertar publicacion").build();
        }
    }

    @Path("/eliminar/{id}")
    @DELETE
    public Response eliminarPublicacion(@PathParam("id") int id) {
        String sql = "DELETE FROM publicaciones WHERE id_publicacion=?";

        try (Connection conexion = openConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();

            if (filas > 0) {
                return Response.ok().build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al eliminar publicacion").build();
        }
    }

    @Path("/obtener/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPublicacion(@PathParam("id") int id) {
        String sql = "SELECT * FROM publicaciones WHERE id_publicacion=?";

        try (Connection conexion = openConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Publicacion p = new Publicacion();
                    p.id_publicacion = rs.getInt("id_publicacion");
                    p.id_usuario = rs.getInt("id_usuario");
                    p.fecha_publicacion = rs.getDate("fecha_publicacion");
                    p.imagen = rs.getBlob("imagen");
                    p.descripcion = rs.getString("descripcion");
                    p.likes = rs.getInt("likes");
                    p.comentarios = rs.getInt("comentarios");

                    return Response.ok(p).build();
                }
            }

            return Response.status(Response.Status.NOT_FOUND).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al obtener publicacion").build();
        }
    }
}
