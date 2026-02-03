Modificación de código para uso con una API
<?php
/*
=========================================================
MODIFICACIÓN DE CÓDIGO PARA USO CON UNA API
=========================================================
Esta clase sustituye el uso de PDO (base de datos directa)
por llamadas HTTP a una API REST.
En vez de usar SQL:
SELECT, INSERT, UPDATE, DELETE
Usamos peticiones HTTP:
GET, POST, PUT, DELETE
=========================================================
*/
class Empleado
{
    // URL base del servidor donde está la API
    // Se define como private para aplicar encapsulación
    private $baseUrl;
    // Constructor: se ejecuta automáticamente al crear el objeto
    // Aquí configuramos la URL de la API
    public function __construct()
    {
        $this->baseUrl = "http://192.168.1.138:8080/tema5maven/rest";
    }
    /*
-----------------------------------------------------
MÉTODO GENÉRICO request()
-----------------------------------------------------
Centraliza TODAS las peticiones HTTP usando cURL.
Evita repetir código (principio DRY).
$method → GET/POST/PUT/DELETE
$url → endpoint de la API
$data → datos opcionales (POST/PUT)
-----------------------------------------------------
*/
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
    // =================================================
    // MÉTODOS CRUD
    // Cada uno llama al método request()
    // =================================================
    // Obtener todos los empleados
    // GET /empleados
    public function getEmpleado()
    {
        return $this->request('GET', '/empleados');
    }
    // Crear un nuevo empleado
    // POST /empleados
    // Los datos viajan en el body en formato JSON
    public function setEmpleado($nombre, $apellidos, $telefono, $departamento, $imagen)
    {
        // Creamos el array con los datos del empleado
        $data = [
            'nombre' => $nombre,
            'apellidos' => $apellidos,
            'telefono' => $telefono,
            'departamento' => $departamento,
            'imagen' => $imagen
        ];
        return $this->request('POST', '/empleados', $data);
    }
    // Obtener un empleado por su id
    // GET /empleados/{id}
    public function editar($id)
    {
        // Interpolación de variable dentro del endpoint
        return $this->request('GET', "/empleados/{$id}");
    }
    // Actualizar un empleado existente
    // PUT /empleados/{id}
    public function actualizar($id, $nombre, $apellidos, $telefono, $departamento, $imagen)
    {
        $data = [
            'nombre' => $nombre,
            'apellidos' => $apellidos,
            'telefono' => $telefono,
            'departamento' => $departamento,
            'imagen' => $imagen
        ];
        return $this->request('PUT', "/empleados/{$id}", $data);
    }
    // Eliminar un empleado
    // DELETE /empleados/{id}
    public function borrar($id)
    {
        return $this->request('DELETE', "/empleados/{$id}");
    }
}
