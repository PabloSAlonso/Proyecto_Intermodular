package ejem1;

import java.sql.Connection;
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
public class GestorPublicaciones {

    private final String url = "jdbc:postgresql://aws-1-eu-west-1.pooler.supabase.com:5432/postgres?sslmode=require";
    private final String user = "postgres.vefvxfzqkwhfetudvnlv";
    private final String password = "bUf*2m9N!w2mmEU";

    @Path("/todas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPublicaciones() {
        String sql = "SELECT * FROM publicaciones ORDER BY id DESC";
        ArrayList<Publicacion> publicaciones = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Publicacion p = new Publicacion();
                    p.setId_publicacion(rs.getInt("id"));
                    p.setId_usuario(rs.getInt("id_usuario"));
                    p.setFecha_publicacion(rs.getDate("fecha_publicacion"));
                    p.setImagen(rs.getBytes("foto_publicacion"));
                    p.setDescripcion(rs.getString("description"));
                    p.setLikes(rs.getInt("likes"));
                    p.setComentarios(rs.getInt("comentarios"));
                    publicaciones.add(p);
                }
                return Response.ok(publicaciones).build();

            } catch (Exception e) {
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Error SQL: " + e.getMessage()).build();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: Driver PostgreSQL no encontrado").build();
        }
    }

    @Path("/usuario/{id_usuario}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPublicacionesPorUsuario(@PathParam("id_usuario") int idUsuario) {
        String sql = "SELECT * FROM publicaciones WHERE id_usuario=? ORDER BY id DESC";
        ArrayList<Publicacion> publicaciones = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, idUsuario);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Publicacion p = new Publicacion();
                        p.setId_publicacion(rs.getInt("id"));
                        p.setId_usuario(rs.getInt("id_usuario"));
                        p.setFecha_publicacion(rs.getDate("fecha_publicacion"));
                        p.setImagen(rs.getBytes("foto_publicacion"));
                        p.setDescripcion(rs.getString("description"));
                        p.setLikes(rs.getInt("likes"));
                        p.setComentarios(rs.getInt("comentarios"));
                        publicaciones.add(p);
                    }
                }
                return Response.ok(publicaciones).build();

            } catch (Exception e) {
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Error SQL: " + e.getMessage()).build();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: Driver PostgreSQL no encontrado").build();
        }
    }

    @Path("/insertar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertarPublicacion(Publicacion p) {
        if (p == null || p.getId_usuario() <= 0 || p.getFecha_publicacion() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Datos de publicacion incompletos").build();
        }

        String sql = "INSERT INTO publicaciones "
                + "(id_usuario, fecha_publicacion, foto_publicacion, description, likes, comentarios) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Class.forName("org.postgresql.Driver");

            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, p.getId_usuario());

                ps.setDate(2, new java.sql.Date(p.getFecha_publicacion().getTime()));

                if (p.getImagen() != null && p.getImagen().length > 0) {
                    ps.setBytes(3, p.getImagen());
                } else {
                    ps.setNull(3, Types.BINARY);
                }

                ps.setString(4, p.getDescripcion());
                ps.setInt(5, p.getLikes());
                ps.setInt(6, p.getComentarios());

                ps.executeUpdate();
                return Response.status(Response.Status.CREATED).entity(p).build();

            } catch (Exception e) {
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Error SQL: " + e.getMessage()).build();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: Driver PostgreSQL no encontrado").build();
        }
    }

    @Path("/eliminar/{id}")
    @DELETE
    public Response eliminarPublicacion(@PathParam("id") int id) {
        String sql = "DELETE FROM publicaciones WHERE id=?";

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, id);
                int filas = ps.executeUpdate();
                if (filas > 0) {
                    return Response.ok().build();
                } else {
                    return Response.status(Response.Status.NOT_FOUND).build();
                }

            } catch (Exception e) {
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Error SQL: " + e.getMessage()).build();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: Driver PostgreSQL no encontrado").build();
        }
    }

    @Path("/obtener/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPublicacion(@PathParam("id") int id) {
        String sql = "SELECT * FROM publicaciones WHERE id=?";

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Publicacion p = new Publicacion();
                        p.setId_publicacion(rs.getInt("id"));
                        p.setId_usuario(rs.getInt("id_usuario"));
                        p.setFecha_publicacion(rs.getDate("fecha_publicacion"));
                        p.setImagen(rs.getBytes("foto_publicacion"));
                        p.setDescripcion(rs.getString("description"));
                        p.setLikes(rs.getInt("likes"));
                        p.setComentarios(rs.getInt("comentarios"));
                        return Response.ok(p).build();
                    } else {
                        return Response.status(Response.Status.NOT_FOUND).build();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Error SQL: " + e.getMessage()).build();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: Driver PostgreSQL no encontrado").build();
        }
    }
}