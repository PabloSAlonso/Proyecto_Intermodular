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

    private $baseUrl;


    public function __construct()
    {
        $this->baseUrl = "http://10.0.2.2:8080/apirest/rest";
    }

    private function request($method, $url, $data = null)
    {
        // Inicializa la conexión cURL a la URL completa
        $ch = curl_init($this->baseUrl . $url);
        // Hace que la respuesta se devuelva como texto
        // y no se imprima directamente en pantalla
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        // Define el método HTTP dinámicamente
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, $method);
        // Si hay datos, los enviamos en formato JSON
        if ($data) {
            // Indicamos al servidor que enviamos JSON
            curl_setopt($ch, CURLOPT_HTTPHEADER, [
                'Content-Type: application/json'
            ]);
            // Convertimos el array PHP a JSON  
            curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));
        }
        // Ejecuta la petición HTTP
        $response = curl_exec($ch);
        // Si hay errores de conexión, lanzamos excepción
        if (curl_errno($ch)) {
            throw new Exception("Error en la API: " . curl_error($ch));
        }
        // Cerramos la conexión para liberar memoria
        curl_close($ch);
        // Convertimos JSON → objeto PHP
        // Permite acceder como $empleado->nombre
        return json_decode($response);
    }


    public function getUsuarios()
    {
        return $this->request('GET', '/usuarios');
    }


    public function setUsuario($id, $nombre, $apellidos, $nickname, $email, $password, $foto_perfil, $fecha_nacimiento, $fecha_creacion_cuenta)
    {

        $data = [
            'nombre' => $nombre,
            'apellidos' => $apellidos,
            'nickname' => $nickname,
            'email' => $email,
            'password' => $password,
            'foto_perfil' => $foto_perfil,
            'fecha_nacimiento' => $fecha_nacimiento,
            'fecha_creacion' => $fecha_creacion_cuenta
        ];
        return $this->request('POST', '/usuarios', $data);
    }

    public function editar($id)
    {

        return $this->request('GET', "/usuarios/insertar/{$id}");
    }


    public function actualizar($id, $nombre, $apellidos, $nickname, $email, $password, $foto_perfil, $fecha_nacimiento, $fecha_creacion_cuenta)
    {
        $data = [
            'nombre' => $nombre,
            'apellidos' => $apellidos,
            'nickname' => $nickname,
            'email' => $email,
            'password' => $password,
            'foto_perfil' => $foto_perfil,
            'fecha_nacimiento' => $fecha_nacimiento,
            'fecha_creacion' => $fecha_creacion_cuenta
        ];
        return $this->request('PUT', "/usuarios/actualizar/{$id}", $data);
    }


    public function borrar($id)
    {
        return $this->request('DELETE', "/usuarios/borrar/{$id}");
    }
}
