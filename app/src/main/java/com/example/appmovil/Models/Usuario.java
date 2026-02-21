package com.example.appmovil.Models;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

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

    public Usuario(){
        // Empty constructor
    }
    
    public Usuario(int id, String nombre, String apellidos, String nickname, String email, String password, Blob foto_perfil,Date fecha_nacimiento, Date fecha_creacion_cuenta){
        this.id = id;
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
    
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public Blob getFoto_perfil() {
        return foto_perfil;
    }
    
    public void setFoto_perfil(Blob foto_perfil) {
        this.foto_perfil = foto_perfil;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }
    
    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Date getFecha_creacion_cuenta() {
        return fecha_creacion_cuenta;
    }
    
    public void setFecha_creacion_cuenta(Date fecha_creacion_cuenta) {
        this.fecha_creacion_cuenta = fecha_creacion_cuenta;
    }
}

