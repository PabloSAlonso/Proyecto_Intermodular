package ejem1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

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

@Path("/usuarios")
public class GestorUsuarios {

    @Path("/insertar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarUsuario(Usuario u) {

        String sql = "INSERT INTO usuarios (nombre, apellidos, nickname, email, password, foto_perfil) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellidos());
            ps.setString(3, u.getNickname());
            ps.setString(4, u.getEmail());

            String passwordHashed = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(12));
            ps.setString(5, passwordHashed);

            if (u.getFoto_perfil() != null) {
                ps.setBytes(6, u.getFoto_perfil());
            } else {
                ps.setNull(6, Types.BINARY);
            }

            ps.executeUpdate();

            return Response.status(Response.Status.CREATED).entity("Usuario creado").build();

        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                return Response.status(Response.Status.CONFLICT)
                        .entity("nickname o email ya existe").build();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error SQL: " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error interno: " + e.getMessage()).build();
        }
    }

@Path("/login/{username}/{password}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUsuario(@PathParam("username") String username,
                                 @PathParam("password") String password2) {
        String sql = "SELECT id, nombre, apellidos, nickname, email, password, foto_perfil "
                   + "FROM usuarios WHERE nickname = ? OR email = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String passwordHashed = rs.getString("password");
                    if (BCrypt.checkpw(password2, passwordHashed)) {
                        Usuario u = new Usuario();
                        u.setId(rs.getInt("id"));
                        u.setNombre(rs.getString("nombre"));
                        u.setApellidos(rs.getString("apellidos"));
                        u.setNickname(rs.getString("nickname"));
                        u.setEmail(rs.getString("email"));
                        u.setPassword(null);
                        u.setFoto_perfil(rs.getBytes("foto_perfil"));

                        return Response.ok(u).build();
                    } else {
                        return Response.status(Response.Status.UNAUTHORIZED)
                                .entity("Contraseña incorrecta").build();
                    }
                } else {
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("Usuario no encontrado").build();
                }
            }

        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error SQL: " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error interno: " + e.getMessage()).build();
        }
    }

    @Path("/update/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUsuario(@PathParam("id") int id, Usuario u) {
        String sql = "UPDATE usuarios SET nombre = ?, apellidos = ?, nickname = ?, email = ?, foto_perfil = ? WHERE id = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellidos());
            ps.setString(3, u.getNickname());
            ps.setString(4, u.getEmail());
            
            if (u.getFoto_perfil() != null) {
                ps.setBytes(5, u.getFoto_perfil());
            } else {
                ps.setNull(5, Types.BINARY);
            }
            
            ps.setInt(6, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                return Response.ok("Usuario actualizado").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
            }

        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                return Response.status(Response.Status.CONFLICT).entity("nickname o email ya existe").build();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error SQL: " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error interno: " + e.getMessage()).build();
        }
    }

    @Path("/obtenerTodos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodosUsuarios() {
        String sql = "SELECT id, nombre, apellidos, nickname, email, foto_perfil FROM usuarios";
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setApellidos(rs.getString("apellidos"));
                u.setNickname(rs.getString("nickname"));
                u.setEmail(rs.getString("email"));
                u.setPassword(null);
                u.setFoto_perfil(rs.getBytes("foto_perfil"));
                usuarios.add(u);
            }
            return Response.ok(usuarios).build();

        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error SQL: " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.getMessage()).build();
        }
    }

    @Path("/obtener/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerUsuarioPorId(@PathParam("id") int id) {
        String sql = "SELECT id, nombre, apellidos, nickname, email, foto_perfil FROM usuarios WHERE id = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setNombre(rs.getString("nombre"));
                    u.setApellidos(rs.getString("apellidos"));
                    u.setNickname(rs.getString("nickname"));
                    u.setEmail(rs.getString("email"));
                    u.setPassword(null);
                    u.setFoto_perfil(rs.getBytes("foto_perfil"));
                    
                    return Response.ok(u).build();
                } else {
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("Usuario no encontrado").build();
                }
            }
            
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error SQL: " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error interno: " + e.getMessage()).build();
        }
    }

    @Path("/eliminar/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarUsuario(@PathParam("id") int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            int rows = ps.executeUpdate();
            if (rows > 0) {
                return Response.ok("Usuario eliminado").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Usuario no encontrado").build();
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
