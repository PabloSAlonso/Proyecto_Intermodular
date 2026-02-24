<?php

header('Content-Type: application/json; charset=utf-8');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: GET, POST, OPTIONS');
header('Access-Control-Allow-Headers: Content-Type');

if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(200);
    exit;
}

$method = $_SERVER['REQUEST_METHOD'];

$requestUri = $_SERVER['REQUEST_URI'];
$path = parse_url($requestUri, PHP_URL_PATH);

$pos = strpos($path, 'api_login.php');
if ($pos !== false) {
    $apiPath = substr($path, $pos + strlen('api_login.php'));
} else {
    $apiPath = '';
}

$apiPath = str_replace('//', '/', $apiPath);
$apiPath = trim($apiPath);

if (!empty($apiPath) && $apiPath[0] !== '/') {
    $apiPath = '/' . $apiPath;
}

if (empty($apiPath) || $apiPath === '/') {
    http_response_code(400);
    echo json_encode(['error' => 'Ruta no especificada']);
    exit;
}

$apiBase = 'http://localhost:8080/tema5maven/rest';
$apiUrl = $apiBase . $apiPath;

error_log("Request URI: " . $requestUri);
error_log("Path: " . $path);
error_log("API Path: " . $apiPath);
error_log("Final API URL: " . $apiUrl);

if ($method === 'GET' && !empty($_SERVER['QUERY_STRING'])) {
    $apiUrl .= '?' . $_SERVER['QUERY_STRING'];
}


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

if (curl_errno($ch)) {
    http_response_code(502);
    echo json_encode(['error' => 'Error al conectar con la API: ' . curl_error($ch)]);
    curl_close($ch);
    exit;
}

if (PHP_VERSION_ID < 80000) {
    curl_close($ch);
} else {
    curl_close($ch);
}

http_response_code($httpCode);
echo $response;

