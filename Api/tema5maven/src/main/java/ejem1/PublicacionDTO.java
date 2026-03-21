package ejem1;

import java.io.Serializable;
import java.sql.Date;

public class PublicacionDTO implements Serializable {
    private int id_publicacion;
    private int id_usuario;
    private Date fecha_publicacion;
    private byte[] imagen;
    private String descripcion;
    private int likes;
    private int comentarios;
    // Added for user sync
    private String nombre_usuario;
    private String nickname_usuario;
    private byte[] foto_usuario;

    public PublicacionDTO() {}

    // Constructor from DB row
    public PublicacionDTO(int id_publicacion, int id_usuario, Date fecha_publicacion, byte[] imagen,
                         String descripcion, int likes, int comentarios, 
                         String nombre_usuario, String nickname_usuario, byte[] foto_usuario) {
        this.id_publicacion = id_publicacion;
        this.id_usuario = id_usuario;
        this.fecha_publicacion = fecha_publicacion;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.likes = likes;
        this.comentarios = comentarios;
        this.nombre_usuario = nombre_usuario;
        this.nickname_usuario = nickname_usuario;
        this.foto_usuario = foto_usuario;
    }

    // Getters
    public int getId_publicacion() { return id_publicacion; }
    public int getId_usuario() { return id_usuario; }
    public Date getFecha_publicacion() { return fecha_publicacion; }
    public byte[] getImagen() { return imagen; }
    public String getDescripcion() { return descripcion; }
    public int getLikes() { return likes; }
    public int getComentarios() { return comentarios; }
    public String getNombre_usuario() { return nombre_usuario; }
    public String getNickname_usuario() { return nickname_usuario; }
    public byte[] getFoto_usuario() { return foto_usuario; }

    // Setters
    public void setId_publicacion(int id_publicacion) { this.id_publicacion = id_publicacion; }
    public void setId_usuario(int id_usuario) { this.id_usuario = id_usuario; }
    public void setFecha_publicacion(Date fecha_publicacion) { this.fecha_publicacion = fecha_publicacion; }
    public void setImagen(byte[] imagen) { this.imagen = imagen; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setLikes(int likes) { this.likes = likes; }
    public void setComentarios(int comentarios) { this.comentarios = comentarios; }
    public void setNombre_usuario(String nombre_usuario) { this.nombre_usuario = nombre_usuario; }
    public void setNickname_usuario(String nickname_usuario) { this.nickname_usuario = nickname_usuario; }
    public void setFoto_usuario(byte[] foto_usuario) { this.foto_usuario = foto_usuario; }
}
