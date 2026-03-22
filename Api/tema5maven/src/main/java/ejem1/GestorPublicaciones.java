package ejem1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

// Refactored: no DTOs, using Publicacion and Usuario via JOIN
@Path("/publicaciones")
public class GestorPublicaciones {

    @Path("/todas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPublicaciones() {
        String sql = "SELECT p.id AS id_publicacion, p.id_usuario, p.fecha_publicacion, p.foto_publicacion, "
                   + "p.description, p.likes, p.comentarios, "
                   + "u.nickname as nickname_usuario, u.foto_perfil as foto_usuario "
                   + "FROM publicaciones p "
                   + "JOIN usuarios u ON p.id_usuario = u.id "
                   + "ORDER BY id_publicacion DESC";
        ArrayList<Publicacion> publicaciones = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Publicacion p = new Publicacion(
                    rs.getInt("id_usuario"),
                    rs.getDate("fecha_publicacion"),
                    rs.getBytes("foto_publicacion"),
                    rs.getString("description"),
                    rs.getInt("likes"),
                    rs.getInt("comentarios")
                );
                p.setId_publicacion(rs.getInt("id_publicacion"));
                p.setNickname_usuario(rs.getString("nickname_usuario"));
                p.setFoto_usuario(rs.getBytes("foto_usuario"));
                publicaciones.add(p);
            }
            return Response.ok(publicaciones).build();

        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error SQL: " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.getMessage()).build();
        }
    }

    @Path("/usuario/{id_usuario}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPublicacionesPorUsuario(@PathParam("id_usuario") int idUsuario) {
        String sql = "SELECT p.id, p.id_usuario, p.fecha_publicacion, p.foto_publicacion, "
                   + "p.description, p.likes, p.comentarios, "
                   + "u.nickname as nickname_usuario, u.foto_perfil as foto_usuario "
                   + "FROM publicaciones p "
                   + "JOIN usuarios u ON p.id_usuario = u.id "
                   + "WHERE p.id_usuario = ? "
                   + "ORDER BY p.id DESC";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                ArrayList<Publicacion> publicaciones = new ArrayList<>();
                while (rs.next()) {
                Publicacion p = new Publicacion(
                    rs.getInt("id_usuario"),
                    rs.getDate("fecha_publicacion"),
                    rs.getBytes("foto_publicacion"),
                    rs.getString("description"),
                    rs.getInt("likes"),
                    rs.getInt("comentarios")
                );
                p.setId_publicacion(rs.getInt("id"));
                    p.setNickname_usuario(rs.getString("nickname_usuario"));
                    p.setFoto_usuario(rs.getBytes("foto_usuario"));
                    publicaciones.add(p);
                }
                return Response.ok(publicaciones).build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error SQL: " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error interno: " + e.getMessage()).build();
        }
    }

    @Path("/obtener/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPublicacion(@PathParam("id") int id) {
        String sql = "SELECT p.id, p.id_usuario, p.fecha_publicacion, p.foto_publicacion, "
                   + "p.description, p.likes, p.comentarios, "
                   + "u.nickname as nickname_usuario, u.foto_perfil as foto_usuario "
                   + "FROM publicaciones p "
                   + "JOIN usuarios u ON p.id_usuario = u.id "
                   + "WHERE p.id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Publicacion p = new Publicacion(
                        rs.getInt("id_usuario"),
                        rs.getDate("fecha_publicacion"),
                        rs.getBytes("foto_publicacion"),
                        rs.getString("description"),
                        rs.getInt("likes"),
                        rs.getInt("comentarios")
                    );
                    p.setId_publicacion(rs.getInt("id"));
                    p.setNickname_usuario(rs.getString("nickname_usuario"));
                    p.setFoto_usuario(rs.getBytes("foto_usuario"));
                    return Response.ok(p).build();
                } else {
                    return Response.status(Response.Status.NOT_FOUND).build();
                }
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error SQL: " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.getMessage()).build();
        }
    }

    @Path("/debugOne")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response debugOne() {
        String sql = "SELECT p.id, p.id_usuario, p.fecha_publicacion, p.foto_publicacion, "
                   + "p.description, p.likes, p.comentarios, "
                   + "u.nickname as nickname_usuario, u.foto_perfil as foto_usuario "
                   + "FROM publicaciones p "
                   + "JOIN usuarios u ON p.id_usuario = u.id "
                   + "ORDER BY p.id DESC LIMIT 1";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                Publicacion p = new Publicacion(
                    rs.getInt("id_usuario"),
                    rs.getDate("fecha_publicacion"),
                    rs.getBytes("foto_publicacion"),
                    rs.getString("description"),
                    rs.getInt("likes"),
                    rs.getInt("comentarios"));
                p.setId_publicacion(rs.getInt("id"));
                p.setNickname_usuario(rs.getString("nickname_usuario"));
                p.setFoto_usuario(rs.getBytes("foto_usuario"));
                return Response.ok(p).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error SQL: " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.getMessage()).build();
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
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, p.getId_usuario());
                ps.setDate(2, new java.sql.Date(p.getFecha_publicacion().getTime()));
                if (p.getImagen() != null && p.getImagen().length > 0) {
                    ps.setBytes(3, p.getImagen());
                } else {
                    ps.setNull(3, java.sql.Types.BINARY);
                }
                ps.setString(4, p.getDescripcion());
                ps.setInt(5, p.getLikes());
                ps.setInt(6, p.getComentarios());
                ps.executeUpdate();
                return Response.status(Response.Status.CREATED).entity(p).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.getMessage()).build();
        }
    }

    @Path("/actualizar/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarPublicacion(@PathParam("id") int id, Publicacion p) {
        if (p == null || p.getId_usuario() <= 0 || p.getFecha_publicacion() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Datos de publicacion incompletos").build();
        }

        String sql = "UPDATE publicaciones "
                + "SET id_usuario = ?, fecha_publicacion = ?, foto_publicacion = ?, description = ?, likes = ?, comentarios = ? "
                + "WHERE id = ?";

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, p.getId_usuario());
                ps.setDate(2, new java.sql.Date(p.getFecha_publicacion().getTime()));
                if (p.getImagen() != null && p.getImagen().length > 0) {
                    ps.setBytes(3, p.getImagen());
                } else {
                    ps.setNull(3, java.sql.Types.BINARY);
                }
                ps.setString(4, p.getDescripcion());
                ps.setInt(5, p.getLikes());
                ps.setInt(6, p.getComentarios());
                ps.setInt(7, id);
                int filas = ps.executeUpdate();
                if (filas > 0) {
                    return Response.ok(p).build();
                } else {
                    return Response.status(Response.Status.NOT_FOUND).build();
                }
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.getMessage()).build();
        }
    }

    @Path("/eliminar/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarPublicacion(@PathParam("id") int id) {
        String sql = "DELETE FROM publicaciones WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                return Response.ok("Publicación eliminada").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Publicación no encontrada").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error SQL: " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error interno: " + e.getMessage()).build();
        }
    }
}
