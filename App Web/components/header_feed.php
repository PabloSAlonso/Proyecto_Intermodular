<?php
// Start session only if not already started
if (session_status() === PHP_SESSION_NONE) {
    session_start();
}
require_once '../config/Request.php';

$request = new Request();
$isLoggedIn = isset($_SESSION['usuario']) || isset($_SESSION['usuario_id']);
$userName = '';

// If logged in, get user info
if ($isLoggedIn && isset($_SESSION['usuario'])) {
    $userData = $_SESSION['usuario'];
    $userName = is_array($userData) ? ($userData['nombre'] ?? '') : '';
} elseif ($isLoggedIn && isset($_SESSION['usuario_id'])) {
    $userId = $_SESSION['usuario_id'];
    try {
        $userData = $request->getUserById($userId);
        $userName = is_array($userData) ? ($userData['nombre'] ?? '') : '';
    } catch (Exception $e) {
        // Ignore errors
    }
}
?>

<nav class="navbar sticky-top">
    <a href="feed.php" class="navbar-brand">
        <img src="../src/imagenes/Klyer-logo-transparent.png" alt="Logo">KLYER
    </a>
    <div class="nav-links">
        <?php if ($isLoggedIn): ?>
            <span>Hola, <?php echo htmlspecialchars($userName); ?></span>
            <a href="feed.php">Feed</a>
            <a href="subir_publicacion.php">Publicar</a>
            <a href="perfil.php">Mi Perfil</a>
            <a href="#" onclick="logout(); return false;">Cerrar sesión</a>
        <?php else: ?>
            <a href="inicio_sesion.php">Iniciar sesión</a>
            <a href="registro.php">Registrarse</a>
        <?php endif; ?>
    </div>
</nav>

<script>
    const API_BASE = "http://localhost:8080/tema5maven/rest";

    function logout() {
        sessionStorage.removeItem('usuario');
        sessionStorage.removeItem('usuario_id');
        window.location.href = 'inicio_sesion.php';
    }

    // Check session on client side
    const usuario = sessionStorage.getItem('usuario');
    if (!usuario) {
        window.location.href = 'inicio_sesion.php';
    }
</script>

