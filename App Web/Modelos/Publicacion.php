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
        $this->request = new Request("https://proyecto-intermodular-kpzv.onrender.com/rest");
    }

    
}