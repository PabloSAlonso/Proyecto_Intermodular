package modelos;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;

import conexion.Conexion;
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

    String ruta_driver = "org.mariadb.jdbc.Driver";
    Conexion c = new Conexion();

    public void llamadaDriver(String ruta) throws ClassNotFoundException {
        Class.forName(ruta);
    }

    @Path("/insertar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertarUsuario(Usuario u) {
        try {
            llamadaDriver(ruta_driver);
            Connection conexion = c.getConexion();

            String sql = "INSERT INTO usuarios (nombre, apellidos, nickname, email, password, foto_perfil, fecha_nacimiento, fecha_creacion_cuenta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
            Connection conexion = c.getConexion();

            String sql = "UPDATE usuarios SET nombre=?, apellidos=?, nickname=?, email=?, password=?, foto_perfil=?, fecha_nacimiento=? WHERE id=?";

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
            Connection conexion = c.getConexion();

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
    public Response obtenerUsuario(@PathParam("email") String email, @PathParam("password") String password) {
        try {
            llamadaDriver(ruta_driver);
            Connection conexion = c.getConexion();

            String sql = "SELECT * FROM usuarios WHERE email=? AND password = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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
                return Response.status(Response.Status.NOT_FOUND).build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}