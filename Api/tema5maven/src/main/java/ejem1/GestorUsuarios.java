package ejem1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuarios")
public class GestorUsuarios {
    private final String url = "jdbc:postgresql://aws-1-eu-west-1.pooler.supabase.com:5432/postgres?sslmode=require";
    private final String user = "postgres.vefvxfzqkwhfetudvnlv";
    private final String password = "bUf*2m9N!w2mmEU";

    @Path("/insertar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarUsuario(Usuario u) {

        String sql = "INSERT INTO usuarios (nombre, apellidos, nickname, email, password, foto_perfil) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Class.forName("org.postgresql.Driver");

            try (Connection conn = DriverManager.getConnection(url, user, password);
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
                e.printStackTrace();

                if ("23505".equals(e.getSQLState())) {
                    return Response.status(Response.Status.CONFLICT)
                            .entity("nickname o email ya existe").build();
                }

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Error SQL: " + e.getMessage()).build();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: Driver PostgreSQL no encontrado").build();
        } catch (Exception e) {
            e.printStackTrace();
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
               + "FROM usuarios WHERE nickname = ?";
    try {
        Class.forName("org.postgresql.Driver");

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

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
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error SQL: " + e.getMessage()).build();
        }

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error: Driver PostgreSQL no encontrado").build();
    } catch (Exception e) {
        e.printStackTrace();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error interno: " + e.getMessage()).build();
    }
}
}