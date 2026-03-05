<?php
// Start session only if not already started
if (session_status() === PHP_SESSION_NONE) {
    session_start();
}
require_once '../config/Request.php';

$request = new Request();
$isLoggedIn = isset($_SESSION['usuario']) || isset($_SESSION['usuario_id']);
$userName = '';
$userId = '';

// If logged in, try to get user info
if ($isLoggedIn && isset($_SESSION['usuario'])) {
    $userData = $_SESSION['usuario'];
    $userName = is_array($userData) ? ($userData['nombre'] ?? '') : '';
    $userId = is_array($userData) ? ($userData['id'] ?? '') : '';
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
    <a href="<?php echo $isLoggedIn ? 'feed.php' : 'inicio_sesion.php'; ?>" class="navbar-brand">
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
    // Check if logout function exists, if not declare it
    if (typeof logout !== 'function') {
        function logout() {
            sessionStorage.removeItem('usuario');
            sessionStorage.removeItem('usuario_id');
            window.location.href = 'inicio_sesion.php';
        }
    }

    // Also check on client side
    const usuario = sessionStorage.getItem('usuario');
    const currentPage = window.location.pathname.split('/').pop();
    
    // If not logged in and trying to access protected pages
    const protectedPages = ['feed.php', 'perfil.php', 'subir_publicacion.php', 'editar_perfil.php'];
    if (!usuario && protectedPages.includes(currentPage)) {
        window.location.href = 'inicio_sesion.php';
    }
</script>

