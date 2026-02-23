package com.example.appmovil.Publicaciones;

public class Post {
    private int id;
    private String nombreUsuario;
    private String userAvatar; // Base64 del avatar del usuario
    private String imageUrl;   // Base64 de la imagen del post
    private int likes;
    private String descripcion;
    private String info;
    private int streak;
    private String postType;
    private String createdAt;
    private boolean isLiked;

    public Post(String nombreUsuario, String userAvatar, String imageUrl, String descripcion, String info, int likes, int streak) {
        this.nombreUsuario = nombreUsuario;
        this.userAvatar = userAvatar;
        this.imageUrl = imageUrl;
        this.descripcion = descripcion;
        this.info = info;
        this.likes = likes;
        this.streak = streak;
    }

    public Post(int id, String nombreUsuario, String userAvatar, String imageUrl, String descripcion, String postType, int likes, int streak, String createdAt) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.userAvatar = userAvatar;
        this.imageUrl = imageUrl;
        this.descripcion = descripcion;
        this.postType = postType;
        this.info = postType + " • ";
        this.likes = likes;
        this.streak = streak;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public String getNombreUsuario() { return nombreUsuario; }
    public String getUserAvatar() { return userAvatar; }
    public String getImageUrl() { return imageUrl; }
    public int getLikes() { return likes; }
    public String getDescripcion() { return descripcion; }
    public String getInfo() { return info; }
    public int getStreak() { return streak; }
    public String getPostType() { return postType; }
    public String getCreatedAt() { return createdAt; }
    
    public boolean isLiked() { return isLiked; }
    public void setLiked(boolean liked) { isLiked = liked; }
    public void setLikes(int likes) { this.likes = likes; }
}

