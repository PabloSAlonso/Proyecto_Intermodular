<?php
require_once '../Modelos/Usuario.php';

class Controlador_usuarios
{
    public function todos()
    {
        header('Content-Type: application/json');

        $u = new Usuario();
        echo json_encode($u->getUsuarios());
    }
}