package modelos;

import java.awt.Image;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.Conexion;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
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
    private Image foto_perfil;
    private Date fecha_nacimiento;
    private Date fecha_creacion_cuenta;

    public Usuario(String nombre, String apellidos, String nickname, String email, String password,
            Image foto_perfil, Date fecha_nacimiento, Date fecha_creacion_cuenta) {
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

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Image getFoto_perfil() {
        return foto_perfil;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public Date getFecha_creacion_cuenta() {
        return fecha_creacion_cuenta;
    }

    public void llamadaDriver(String ruta) throws ClassNotFoundException {
        Class.forName(ruta);
    }

    String ruta_driver = "org.mariadb.jdbc.Driver";

    Conexion c = new Conexion();

    @Path("/insertar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertarUsuario(Usuario u) {
        try {
            llamadaDriver(ruta_driver);
            try (Connection conexion = c.getConexion()) {
                Statement st = conexion.createStatement();
                st.executeUpdate(
                        "INSERT INTO usuarios(nombre, apellidos, nickname, email, password, foto_perfil, fecha_nacimiento, fecha_creacion_cuenta) VALUES");

                u = new Usuario(nombre, apellidos, nickname, email, password, foto_perfil, fecha_nacimiento,
                        fecha_creacion_cuenta);

                return Response.ok(u).build();
            } catch (SQLException s) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } catch (ClassNotFoundException c) {
            // TODO: handle exception
        }
    }

}
