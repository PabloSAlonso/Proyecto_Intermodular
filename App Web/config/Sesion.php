<?php

class Sesion
{
    /**
     * Iniciar la sesión si no está iniciada
     */
    public static function iniciar()
    {
        if (session_status() === PHP_SESSION_NONE) {
            session_start();
        }
    }

    /**
     * Verificar si el usuario está logueado
     */
    public static function estaLogueado()
    {
        self::iniciar();
        return isset($_SESSION['usuario']) && !empty($_SESSION['usuario']);
    }

    /**
     * Obtener el usuario de la sesión
     */
    public static function obtenerUsuario()
    {
        self::iniciar();
        if (self::estaLogueado()) {
            return $_SESSION['usuario'];
        }
        return null;
    }

    /**
     * Obtener el ID del usuario
     */
    public static function obtenerIdUsuario()
    {
        $usuario = self::obtenerUsuario();
        if ($usuario && isset($usuario['id'])) {
            return $usuario['id'];
        }
        return null;
    }

    /**
     * Guardar el usuario en la sesión
     */
    public static function guardarUsuario($usuario)
    {
        self::iniciar();
        $_SESSION['usuario'] = $usuario;
    }

    /**
     * Cerrar la sesión
     */
    public static function cerrar()
    {
        self::iniciar();
        session_unset();
        session_destroy();
    }

    /**
     * Establecer un mensaje flash (mensaje temporal que se muestra una vez)
     */
    public static function setFlash($mensaje, $tipo = 'info')
    {
        self::iniciar();
        $_SESSION['flash'] = [
            'mensaje' => $mensaje,
            'tipo' => $tipo
        ];
    }

    /**
     * Obtener y eliminar el mensaje flash
     */
    public static function getFlash()
    {
        self::iniciar();
        if (isset($_SESSION['flash'])) {
            $flash = $_SESSION['flash'];
            unset($_SESSION['flash']);
            return $flash;
        }
        return null;
    }

    /**
     * Redirigir a una URL
     */
    public static function redirigir($url)
    {
        header("Location: $url");
        exit();
    }

    /**
     * Verificar si hay un mensaje flash pendiente
     */
    public static function tieneFlash()
    {
        self::iniciar();
        return isset($_SESSION['flash']);
    }
}
