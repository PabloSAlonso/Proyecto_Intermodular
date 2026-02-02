package com.example.appmovil.Models;

import android.media.Image;

import androidx.annotation.NonNull;

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

    public Blob getFoto_perfil() {
        return foto_perfil;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public Date getFecha_creacion_cuenta() {
        return fecha_creacion_cuenta;
    }
}
