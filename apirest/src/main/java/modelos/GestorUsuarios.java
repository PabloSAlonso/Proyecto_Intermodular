package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuarios")
public class GestorUsuarios {

    private static final String DRIVER = "org.postgresql.Driver";

    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
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

    @Path("/insertar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarUsuario(Usuario u) {
        if (u == null || u.getNombre() == null || u.getApellidos() == null || u.getNickname() == null
                || u.getEmail() == null || u.getPassword() == null || u.getFecha_nacimiento() == null
                || u.getFecha_creacion_cuenta() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Datos de usuario incompletos").build();
        }

        String sql = "INSERT INTO usuarios (nombre, apellidos, nickname, email, password_hash, foto_perfil, fecha_nacimiento, fecha_creacion_cuenta) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = openConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            String passwordHashed = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(12));
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellidos());
            ps.setString(3, u.getNickname());
            ps.setString(4, u.getEmail());
            ps.setString(5, passwordHashed);
            if (u.getFoto_perfil() != null) {
                ps.setBlob(6, u.getFoto_perfil());
            } else {
                ps.setNull(6, Types.BINARY);
            }
            ps.setDate(7, u.getFecha_nacimiento());
            ps.setDate(8, u.getFecha_creacion_cuenta());

            ps.executeUpdate();
            u.setPassword(null);
            return Response.status(Response.Status.CREATED).entity(u).build();

        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                return Response.status(Response.Status.CONFLICT).entity("nickname o email ya existe").build();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL al registrar usuario").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error interno al registrar usuario").build();
        }
    }

    @Path("/actualizar/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarUsuario(@PathParam("id") int id, Usuario u) {
        if (u == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Body requerido").build();
        }

        String sql = "UPDATE usuarios SET nombre=?, apellidos=?, nickname=?, email=?, "
                + "password_hash=COALESCE(?, password_hash), foto_perfil=?, fecha_nacimiento=? WHERE id=?";

        try (Connection conexion = openConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellidos());
            ps.setString(3, u.getNickname());
            ps.setString(4, u.getEmail());

            if (u.getPassword() != null && !u.getPassword().trim().isEmpty()) {
                ps.setString(5, BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(12)));
            } else {
                ps.setNull(5, Types.VARCHAR);
            }

            if (u.getFoto_perfil() != null) {
                ps.setBlob(6, u.getFoto_perfil());
            } else {
                ps.setNull(6, Types.BINARY);
            }
            ps.setDate(7, u.getFecha_nacimiento());
            ps.setInt(8, id);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return Response.ok().build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();

        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                return Response.status(Response.Status.CONFLICT).entity("nickname o email ya existe").build();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL al actualizar usuario").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error interno al actualizar usuario").build();
        }
    }

    @Path("/eliminar/{id}")
    @DELETE
    public Response eliminarUsuario(@PathParam("id") int id) {
        String sql = "DELETE FROM usuarios WHERE id=?";

        try (Connection conexion = openConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return Response.ok().build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUsuario(LoginRequest login) {
        if (login == null || login.getEmail() == null || login.getPassword() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("email y password son obligatorios").build();
        }

        String sql = "SELECT * FROM usuarios WHERE email = ?";

        try (Connection conexion = openConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, login.getEmail());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String passwordHashed = rs.getString("password_hash");
                    if (passwordHashed != null && BCrypt.checkpw(login.getPassword(), passwordHashed)) {
                        Usuario u = new Usuario();
                        u.setId(rs.getInt("id"));
                        u.setNombre(rs.getString("nombre"));
                        u.setApellidos(rs.getString("apellidos"));
                        u.setNickname(rs.getString("nickname"));
                        u.setEmail(rs.getString("email"));
                        u.setPassword(null);
                        u.setFoto_perfil(rs.getBlob("foto_perfil"));
                        u.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
                        u.setFecha_creacion_cuenta(rs.getDate("fecha_creacion_cuenta"));

                        return Response.ok(u).build();
                    }
                    return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciales invalidas").build();
                }
                return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error interno en login").build();
        }
    }

    @Path("/obtenerTodos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodosLosUsuarios() {
        String sql = "SELECT id, nombre, apellidos, nickname, email, foto_perfil, fecha_nacimiento, fecha_creacion_cuenta "
                + "FROM usuarios ORDER BY id";

        try (Connection conexion = openConnection();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            List<Usuario> usuarios = new ArrayList<>();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setApellidos(rs.getString("apellidos"));
                u.setNickname(rs.getString("nickname"));
                u.setEmail(rs.getString("email"));
                u.setPassword(null);
                u.setFoto_perfil(rs.getBlob("foto_perfil"));
                u.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
                u.setFecha_creacion_cuenta(rs.getDate("fecha_creacion_cuenta"));
                usuarios.add(u);
            }

            return Response.ok(usuarios).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al obtener usuarios").build();
        }
    }
}
