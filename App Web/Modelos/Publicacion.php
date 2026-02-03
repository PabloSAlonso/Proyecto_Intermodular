<?php

require_once '../config/Request.php';

class Publicacion
{
    private $id_publicacion;
    private $id_usuario;
    private $fecha_publicacion;
    private $imagen;
    private $descripcion;
    private $likes;
    private $comentarios;

    private Request $request;

    public function __construct()
    {
        $this->request = new Request("http://10.0.2.2:8080/apirest/rest");
    }

    public function getPublicaciones()
    {
        return $this->request->get('/publicaciones');
    }


    public function getPublicacionesPorUsuario($id_usuario)
    {
        return $this->request->get("/publicaciones/usuario/{$id_usuario}");
    }


    public function insertar($id_usuario, $imagen, $descripcion)
    {
        $data = [
            'id_usuario' => $id_usuario,
            'imagen'     => $imagen,
            'descripcion'=> $descripcion
        ];

        return $this->request->post('/publicaciones/insertar', $data);
    }


    public function getPublicacion($id_publicacion)
    {
        return $this->request->get("/publicaciones/{$id_publicacion}");
    }


    public function actualizar($id_publicacion, $imagen, $descripcion)
    {
        $data = [
            'imagen'      => $imagen,
            'descripcion' => $descripcion
        ];

        return $this->request->put("/publicaciones/actualizar/{$id_publicacion}", $data);
    }


    public function eliminar($id_publicacion)
    {
        return $this->request->delete("/publicaciones/eliminar/{$id_publicacion}");
    }
}