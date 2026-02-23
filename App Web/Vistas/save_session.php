<?php
session_start();

header('Content-Type: application/json');

if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    http_response_code(405);
    echo json_encode(['error' => 'Method not allowed']);
    exit;
}

$rawBody = file_get_contents('php://input');
$decoded = json_decode($rawBody, true);

if (!is_array($decoded)) {
    http_response_code(400);
    echo json_encode(['error' => 'Invalid JSON']);
    exit;
}

if (isset($decoded['usuario'])) {
    $_SESSION['usuario'] = $decoded['usuario'];
}

if (isset($decoded['usuario_id'])) {
    $_SESSION['usuario_id'] = $decoded['usuario_id'];
}

echo json_encode(['success' => true]);

