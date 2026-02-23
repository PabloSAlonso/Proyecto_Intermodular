package ejem1;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import jakarta.ws.rs.Path;

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

    public int getId_publicacion() {
        return id_publicacion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public Date getFecha_publicacion() {
        return fecha_publicacion;
    }

    public Blob getImagen() {
        return imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getLikes() {
        return likes;
    }

    public int getComentarios() {
        return comentarios;
    }

    public void setId_publicacion(int id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public void setComentarios(int comentarios) {
        this.comentarios = comentarios;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha_publicacion(Date fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setImagen(Blob imagen) {
        this.imagen = imagen;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
