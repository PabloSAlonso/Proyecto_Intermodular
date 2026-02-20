<?php
require_once '../Modelos/Publicacion.php';

class Controlador_publicaciones
{
    public function todas()
    {
        header('Content-Type: application/json');

        $pub = new Publicacion();
        echo json_encode($pub->getPublicaciones());
    }

    public function todasPorUsuario()
    {
        header('Content-Type: application/json');
        $usuario = new Usuario();
        $pub = new Publicacion();
        echo json_encode($pub->getPublicacionesPorUsuario($usuario->id));
    }
}
