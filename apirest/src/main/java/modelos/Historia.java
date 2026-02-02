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

@Path("/historias")
public class Historia implements Serializable {

    private int id_historia;
    private int id_usuario;
    private Date fecha_publicacion;
    private Blob imagen;
    private int likes;

    public Historia() {
    }

    public Historia(int id_usuario, Date fecha_publicacion, Blob imagen, int likes) {
        this.id_usuario = id_usuario;
        this.fecha_publicacion = fecha_publicacion;
        this.imagen = imagen;
        this.likes = likes;
    }

    public int getId_historia() { return id_historia; }
    public int getId_usuario() { return id_usuario; }
    public Date getFecha_publicacion() { return fecha_publicacion; }
    public Blob getImagen() { return imagen; }
    public int getLikes() { return likes; }

    String ruta_driver = "org.mariadb.jdbc.Driver";
    Conexion c = new Conexion();

    public void llamadaDriver(String ruta) throws ClassNotFoundException {
        Class.forName(ruta);
    }

    @Path("/insertar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertarHistoria(Historia h) {
        try {
            llamadaDriver(ruta_driver);
            Connection conexion = c.getConexion();

            String sql = "INSERT INTO historias "
                    + "(id_usuario, fecha_publicacion, imagen, likes) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, h.getId_usuario());
            ps.setDate(2, h.getFecha_publicacion());
            ps.setBlob(3, h.getImagen());
            ps.setInt(4, h.getLikes());

            ps.executeUpdate();

            return Response.status(Response.Status.CREATED).entity(h).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/actualizar/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarHistoria(@PathParam("id") int id, Historia h) {
        try {
            llamadaDriver(ruta_driver);
            Connection conexion = c.getConexion();

            String sql = "UPDATE historias SET imagen=?, likes=? WHERE id_historia=?";

            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setBlob(1, h.getImagen());
            ps.setInt(2, h.getLikes());
            ps.setInt(3, id);

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
    public Response eliminarHistoria(@PathParam("id") int id) {
        try {
            llamadaDriver(ruta_driver);
            Connection conexion = c.getConexion();

            String sql = "DELETE FROM historias WHERE id_historia=?";
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