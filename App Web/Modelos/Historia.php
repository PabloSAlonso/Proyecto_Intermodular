<?php
class Historia{
    private $id_historia;
    private $id_usuario;
    private $fecha_publicacion;
    private $imagen;
    private $likes;

    public static function obtenerTodas()
    {
        $conexion = Conexion::conectar(); // Tengo que pedir los datos que me hacen falta para cada publicacion en la API
        $sql = "SELECT 
                FROM historias
                JOIN usuarios ON id_usuario = id
                ORDER BY id_publicacion DESC";

        $stmt = $conexion->prepare($sql);
        $stmt->execute();

        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }
}