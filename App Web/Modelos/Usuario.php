<?php

require_once '../config/Request.php';

class Usuario
{
    public $id, $nombre, $apellidos, $nickname, $email, $password, $foto_perfil, $fecha_nacimiento, $fecha_creacion_cuenta;

    public function __construct($id, $nombre, $apellidos, $nickname, $email, $password, $foto_perfil, $fecha_nacimiento, $fecha_creacion_cuenta)
    {
        $this->id = $id;
        $this->nombre = $nombre;
        $this->apellidos = $apellidos;
        $this->nickname = $nickname;
        $this->email = $email;
        $this->password = $password;
        $this->foto_perfil = $foto_perfil;
        $this->fecha_nacimiento = $fecha_nacimiento;
        $this->fecha_creacion_cuenta = $fecha_creacion_cuenta;
    }
    
}
 