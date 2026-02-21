package modelos;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;

import conexion.Conexion;
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
public class Usuario implements Serializable {

    private int id;
    private String nombre;
    private String apellidos;
    private String nickname;
    private String email;
    private String password;
    private Blob foto_perfil;
    private Date fecha_nacimiento;
    private Date fecha_creacion_cuenta;

    public Usuario() {
    }

    public Usuario(String nombre, String apellidos, String nickname, String email, String password,
            Blob foto_perfil, Date fecha_nacimiento, Date fecha_creacion_cuenta) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.foto_perfil = foto_perfil;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fecha_creacion_cuenta = fecha_creacion_cuenta;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Blob getFoto_perfil() {
        return foto_perfil;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public Date getFecha_creacion_cuenta() {
        return fecha_creacion_cuenta;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private static final String URL = "jdbc:postgresql://aws-1-eu-west-1.pooler.supabase.com:5432/postgres?sslmode=require";
    private static final String USER = "postgres.vefvxfzqkwhfetudvnlv";
    private static final String PASSWORD = "bUf*2m9N!w2mmEU";

    String ruta_driver = "org.postgresql.Driver";

    public void llamadaDriver(String ruta) throws ClassNotFoundException {
        Class.forName(ruta);
    }

    // {
    // "nombre":"",
    // "apellidos":"",
    // "nickname":"",
    // "email":"",
    // "password":"",
    // "foto_perfil":"",
    // "fecha_nacimiento":"",
    // "fecha_creacion_cuenta":"",
    // }

    @Path("/insertar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarUsuario(Usuario u) {
        try {
            llamadaDriver(ruta_driver);
            Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);

String sql = "INSERT INTO usuarios (nombre, apellidos, nickname, email, password_hash, foto_perfil, fecha_nacimiento, fecha_creacion_cuenta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conexion.prepareStatement(sql);
            // BCrypt.checkpw(passwordIntroducida, passwordGuardadaBD); Comprueba password
            String passwordHashed = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(12));
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellidos());
            ps.setString(3, u.getNickname());
            ps.setString(4, u.getEmail());
            ps.setString(5, passwordHashed);
            ps.setBlob(6, u.getFoto_perfil());
            ps.setDate(7, u.getFecha_nacimiento());
            ps.setDate(8, u.getFecha_creacion_cuenta());

            ps.executeUpdate();
            u.setPassword(null);
            return Response.status(Response.Status.CREATED).entity(u).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/actualizar/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarUsuario(@PathParam("id") int id, Usuario u) {
        try {
            llamadaDriver(ruta_driver);
            Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);

String sql = "UPDATE usuarios SET nombre=?, apellidos=?, nickname=?, email=?, password_hash=?, foto_perfil=?, fecha_nacimiento=? WHERE id=?";

            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellidos());
            ps.setString(3, u.getNickname());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getPassword());
            ps.setBlob(6, u.getFoto_perfil());
            ps.setDate(7, u.getFecha_nacimiento());
            ps.setInt(8, id);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/eliminar/{id}")
    @DELETE
    public Response eliminarUsuario(@PathParam("id") int id) {
        try {
            llamadaDriver(ruta_driver);
            Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "DELETE FROM usuarios WHERE id=?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/obtener/{email}/{password}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUsuario(@PathParam("email") String email, @PathParam("password") String password) {
        try {
            llamadaDriver(ruta_driver);
            Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);

            // First, get the user by email
            String sql = "SELECT * FROM usuarios WHERE email = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Get the hashed password from database
String passwordHashed = rs.getString("password_hash");
                
                // Use BCrypt to verify the password
                if (BCrypt.checkpw(password, passwordHashed)) {
                    Usuario u = new Usuario();
                    u.id = rs.getInt("id");
                    u.nombre = rs.getString("nombre");
                    u.apellidos = rs.getString("apellidos");
                    u.nickname = rs.getString("nickname");
                    u.email = rs.getString("email");
                    u.password = null;
                    u.foto_perfil = rs.getBlob("foto_perfil");
                    u.fecha_nacimiento = rs.getDate("fecha_nacimiento");
                    u.fecha_creacion_cuenta = rs.getDate("fecha_creacion_cuenta");

                    return Response.ok(u).build();
                } else {
                    return Response.status(Response.Status.UNAUTHORIZED).build();
                }
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}