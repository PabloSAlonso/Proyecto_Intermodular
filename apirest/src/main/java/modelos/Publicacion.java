package modelos;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import conexion.Conexion;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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

    public int getId_publicacion() { return id_publicacion; }
    public int getId_usuario() { return id_usuario; }
    public Date getFecha_publicacion() { return fecha_publicacion; }
    public Blob getImagen() { return imagen; }
    public String getDescripcion() { return descripcion; }
    public int getLikes() { return likes; }
    public int getComentarios() { return comentarios; }

    String ruta_driver = "org.mariadb.jdbc.Driver";
    Conexion c = new Conexion();

    public void llamadaDriver(String ruta) throws ClassNotFoundException {
        Class.forName(ruta);
    }

    @Path("/insertar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertarPublicacion(Publicacion p) {
        try {
            llamadaDriver(ruta_driver);
            Connection conexion = c.getConexion();

            String sql = "INSERT INTO publicaciones "
                    + "(id_usuario, fecha_publicacion, imagen, descripcion, likes, comentarios) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, p.getId_usuario());
            ps.setDate(2, p.getFecha_publicacion());
            ps.setBlob(3, p.getImagen());
            ps.setString(4, p.getDescripcion());
            ps.setInt(5, p.getLikes());
            ps.setInt(6, p.getComentarios());

            ps.executeUpdate();

            return Response.status(Response.Status.CREATED).entity(p).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/actualizar/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarPublicacion(@PathParam("id") int id, Publicacion p) {
        try {
            llamadaDriver(ruta_driver);
            Connection conexion = c.getConexion();

            String sql = "UPDATE publicaciones SET "
                    + "imagen=?, descripcion=?, likes=?, comentarios=? "
                    + "WHERE id_publicacion=?";

            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setBlob(1, p.getImagen());
            ps.setString(2, p.getDescripcion());
            ps.setInt(3, p.getLikes());
            ps.setInt(4, p.getComentarios());
            ps.setInt(5, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/eliminar/{id}")
    @DELETE
    public Response eliminarPublicacion(@PathParam("id") int id) {
        try {
            llamadaDriver(ruta_driver);
            Connection conexion = c.getConexion();

            String sql = "DELETE FROM publicaciones WHERE id_publicacion=?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}