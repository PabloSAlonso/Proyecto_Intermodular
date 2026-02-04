<?php
require_once '../Modelos/Publicacion.php';

class PublicacionesController
{
    public function todas()
    {
        header('Content-Type: application/json');

        $pub = new Publicacion();
        echo json_encode($pub->getPublicaciones());
    }
}