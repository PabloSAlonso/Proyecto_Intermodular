<?php

class Request
{
    private string $baseUrl;

    public function __construct(string $baseUrl)
    {
        $this->baseUrl = rtrim($baseUrl, '/');
    }

    private function request(string $method, string $url, array $data = null)
    {
        $ch = curl_init($this->baseUrl . $url);

        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, $method);

        if ($data !== null) {
            curl_setopt($ch, CURLOPT_HTTPHEADER, [
                'Content-Type: application/json'
            ]);
            curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));
        }

        $response = curl_exec($ch);

        if (curl_errno($ch)) {
            throw new Exception('Error en la API: ' . curl_error($ch));
        }

        curl_close($ch);

        return json_decode($response);
    }

    /* Métodos públicos para usar en cualquier sitio */

    public function get(string $url)
    {
        return $this->request('GET', $url);
    }

    public function post(string $url, array $data)
    {
        return $this->request('POST', $url, $data);
    }

    public function put(string $url, array $data)
    {
        return $this->request('PUT', $url, $data);
    }

    public function delete(string $url)
    {
        return $this->request('DELETE', $url);
    }

    // ==== USUARIOS ====
    public function login(string $email, string $password) {
        return $this->get("/usuarios/obtener/$email/$password");
    }
    public function register(array $userData) {
        return $this->post("/usuarios/insertar", $userData);
    }
    public function deleteUser(int $id) {
        return $this->delete("/usuarios/eliminar/$id");
    }

    // ==== PUBLICACIONES ====
    public function getAllPublications() {
        return $this->get('/publicaciones/todas');
    }
    public function createPublication(array $publicationData) {
        return $this->post('/publicaciones/insertar', $publicationData);
    }
    public function deletePublication(int $id) {
        return $this->delete("/publicaciones/eliminar/$id");
    }
}
