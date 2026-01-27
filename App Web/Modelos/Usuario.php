<?php
class usuario
{
    private $id;
    private $nombre;
    private $apellidos;
    private $nickname;
    private $password;
    private $email;
    private $foto_perfil;
    private $fecha_nacimiento;
    private $fecha_creacion_cuenta;


    public static function obtenerTodos()
    {
        $conexion = Conexion::conectar(); // Tengo que pedir los datos que me hacen falta para cada publicacion en la API
        $sql = "SELECT * FROM usuarios";

        $stmt = $conexion->prepare($sql);
        $stmt->execute();

        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }
}
