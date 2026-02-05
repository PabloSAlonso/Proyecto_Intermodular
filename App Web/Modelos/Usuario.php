<?php

require_once '../config/Request.php';

class usuario
{
    private Request $request;
    public function __construct()
    {
        $this->request = new Request("http://10.0.2.2:8080/apirest/rest");
    }

    public function getUsuarios()
    {
        return $this->request->get('/usuarios');
    }

    public function setUsuario($id, $nombre, $apellidos, $nickname, $email, $password, $foto_perfil, $fecha_nacimiento, $fecha_creacion_cuenta)
    {
        $data = [
            'nombre' => $nombre,
            'apellidos' => $apellidos,
            'nickname' => $nickname,
            'email' => $email,
            'password' => $password,
            'foto_perfil' => $foto_perfil,
            'fecha_nacimiento' => $fecha_nacimiento,
            'fecha_creacion' => $fecha_creacion_cuenta
        ];

        return $this->request->post('/usuarios', $data);
    }

    public function editar($id)
    {
        return $this->request->get("/usuarios/insertar");
    }

    public function actualizar($id, $nombre, $apellidos, $nickname, $email, $password, $foto_perfil, $fecha_nacimiento, $fecha_creacion_cuenta)
    {
        $data = [
            'nombre' => $nombre,
            'apellidos' => $apellidos,
            'nickname' => $nickname,
            'email' => $email,
            'password' => $password,
            'foto_perfil' => $foto_perfil,
            'fecha_nacimiento' => $fecha_nacimiento,
            'fecha_creacion' => $fecha_creacion_cuenta
        ];

        return $this->request->put("/usuarios/actualizar/{$id}", $data);
    }

    public function borrar($id)
    {
        return $this->request->delete("/usuarios/borrar/{$id}");
    }
}