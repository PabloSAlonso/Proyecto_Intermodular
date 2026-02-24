<?php

ini_set('display_errors', 0);
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS');
header('Access-Control-Allow-Headers: Content-Type, Authorization');

if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(200);
    exit;
}

$apiBase = 'http://localhost:8080/tema5maven/rest'; 

if (!isset($_GET['path'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Ruta de API no especificada. Usa ?path=/tu/ruta.']);
    exit;
}

$apiUrl = $apiBase . $_GET['path'];

$method = $_SERVER['REQUEST_METHOD'];

if ($method === 'GET' && !empty($_SERVER['QUERY_STRING'])) {
    $apiUrl .= '?' . $_SERVER['QUERY_STRING'];
}

error_log("[PROXY] Request URI: " . $_SERVER['REQUEST_URI'] . " | Method: " . $method);
error_log("[PROXY] Calculated API URL: " . $apiUrl);

$ch = curl_init($apiUrl);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_CUSTOMREQUEST, $method);
curl_setopt($ch, CURLOPT_HEADER, false);

$headers = ['Content-Type: application/json'];
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

if ($method === 'POST' || $method === 'PUT') {
    $input = file_get_contents('php://input');
    curl_setopt($ch, CURLOPT_POSTFIELDS, $input);
}

$response = curl_exec($ch);
$httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);

$curlError = curl_error($ch);
curl_close($ch);

if ($curlError) {
    http_response_code(502); 
    echo json_encode(['error' => 'Error al conectar con la API', 'detalle' => $curlError]);
    exit;
}

http_response_code($httpCode);
echo $response;
