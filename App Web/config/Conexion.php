<?php

class Conexion
{

    public static function conectar()
    {
        try {
            $conexion = new PDO(
                "mysql:host=sql.freedb.tech;dbname=freedb_klyer;charset=utf8",
                "freedb_pablillo2k",
                "bUf*2m9N!w2mmEU"
            );
            return $conexion;
        } catch (PDOException $e) {
            die("Error de conexión: " . $e->getMessage());
        }
    }
}
