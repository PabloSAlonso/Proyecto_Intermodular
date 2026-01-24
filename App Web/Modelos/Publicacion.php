<?php
class Publicacion
{
    private $id_publicacion;
    private $id_usuario;
    private $fecha_publicacion;
    private $imagen;
    private $descripcion;
    private $likes;
    private $comentarios;

    public static function obtenerTodas()
    {
        $conexion = Conexion::conectar(); // Tengo que pedir los datos que me hacen falta para cada publicacion
        $sql = "SELECT 
                FROM publicaciones
                JOIN usuarios ON id_usuario = id
                ORDER BY id_publicacion DESC";

        $stmt = $conexion->prepare($sql);
        $stmt->execute();

        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }
}
