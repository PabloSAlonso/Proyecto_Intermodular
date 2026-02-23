<?php

header('Content-Type: application/json; charset=utf-8');

if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    http_response_code(405);
    echo json_encode(['error' => 'Metodo no permitido']);
    exit;
}

$rawBody = file_get_contents('php://input');
if ($rawBody === false || trim($rawBody) === '') {
    http_response_code(400);
    echo json_encode(['error' => 'Cuerpo de peticion vacio']);
    exit;
}

$decoded = json_decode($rawBody, true);
if (!is_array($decoded)) {
    http_response_code(400);
    echo json_encode(['error' => 'JSON invalido']);
    exit;
}

$apiUrl = 'http://localhost:8080/tema5maven/rest/usuarios/insertar';

$ch = curl_init($apiUrl);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_HTTPHEADER, ['Content-Type: application/json']);
curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($decoded));

$responseBody = curl_exec($ch);

if ($responseBody === false) {
    $curlError = curl_error($ch);
    curl_close($ch);
    http_response_code(502);
    echo json_encode(['error' => 'No se pudo contactar con la API REST', 'detalle' => $curlError]);
    exit;
}

$statusCode = (int) curl_getinfo($ch, CURLINFO_HTTP_CODE);
curl_close($ch);

if ($statusCode <= 0) {
    http_response_code(502);
    echo json_encode(['error' => 'La API REST no devolvio codigo HTTP valido']);
    exit;
}

http_response_code($statusCode);
echo $responseBody;

