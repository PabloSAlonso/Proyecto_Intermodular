<?php
// Proxy PHP para evitar problemas de CORS
// El JavaScript llama a este archivo, que llama a la API

header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS');
header('Access-Control-Allow-Headers: Content-Type');

// Si es OPTIONS, responder con OK
if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(200);
    exit;
}

$method = $_SERVER['REQUEST_METHOD'];

// Obtener la ruta de la API
$requestUri = $_SERVER['REQUEST_URI'];
$path = parse_url($requestUri, PHP_URL_PATH);

// Eliminar el prefijo del proxy ( tanto /api_proxy.php como api_proxy.php )
// Primero encontrar la posición de api_proxy.php en el path
$scriptPos = strpos($path, 'api_proxy.php');
if ($scriptPos !== false) {
    // Obtener todo lo que viene después de api_proxy.php
    $apiPath = substr($path, $scriptPos + strlen('api_proxy.php'));
} else {
    $apiPath = $path;
}

// Eliminar cualquier prefijo de directorio como /App%20Web o /App Web
$apiPath = preg_replace('#^/[^/]+(/api_proxy\.php)?#', '', $apiPath);
$apiPath = str_replace('../api_proxy.php', '', $apiPath);

// Eliminar doble barras si las hay
$apiPath = str_replace('//', '/', $apiPath);

// Asegurar que apiPath empieza con /
if (!empty($apiPath) && $apiPath[0] !== '/') {
    $apiPath = '/' . $apiPath;
}

if (empty($apiPath)) {
    http_response_code(400);
    echo json_encode(['error' => 'Ruta no especificada']);
    exit;
}

// URL base de la API
$apiBase = 'http://localhost:8080/tema5maven/rest';
$apiUrl = $apiBase . $apiPath;

// Debug: mostrar la URL que se está generando
error_log("=== DEBUG API PROXY ===");
error_log("Request URI: " . $requestUri);
error_log("Path: " . $path);
error_log("Script Pos: " . $scriptPos);
error_log("API Path: " . $apiPath);
error_log("Final API URL: " . $apiUrl);
error_log("=======================");

// Si es GET, añadir query string
if ($method === 'GET' && !empty($_SERVER['QUERY_STRING'])) {
    $apiUrl .= '?' . $_SERVER['QUERY_STRING'];
}

// Inicializar cURL
$ch = curl_init($apiUrl);

curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_CUSTOMREQUEST, $method);
curl_setopt($ch, CURLOPT_HEADER, false);

// Añadir headers
$headers = ['Content-Type: application/json'];
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

// Si es POST o PUT, añadir body
if ($method === 'POST' || $method === 'PUT') {
    $input = file_get_contents('php://input');
    curl_setopt($ch, CURLOPT_POSTFIELDS, $input);
}

// Ejecutar request
$response = curl_exec($ch);
$httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);

if (curl_errno($ch)) {
    http_response_code(502);
    echo json_encode(['error' => 'Error al conectar con la API: ' . curl_error($ch)]);
    curl_close($ch);
    exit;
}

// En PHP 8+ curl_close es deprecated y no es necesario
// curl_close($ch);

// Devolver respuesta
http_response_code($httpCode);
echo $response;

