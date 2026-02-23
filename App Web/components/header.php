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

<header class="bg-primary text-white py-2 sticky-top shadow-sm">
    <div class="container d-flex align-items-center justify-content-between">

        <div class="d-flex align-items-center">
            <a href="<?php echo $isLoggedIn ? 'feed.php' : 'inicio_sesion.php'; ?>" class="text-white text-decoration-none d-flex align-items-center">
                <img
                    src="../src/imagenes/Klyer-logo-transparent.png"
                    alt="Logo"
                    width="45"
                    height="45"
                    class="me-3">

                <h5 class="mb-0 fw-semibold"
                    style="font-family: 'Times New Roman', Times, serif;">
                    KLYER
                </h5>
            </a>
        </div>

        <div class="d-flex align-items-center gap-3">
            <?php if ($isLoggedIn): ?>
                <span class="text-white">Hola, <?php echo htmlspecialchars($userName); ?></span>
                <a href="feed.php" class="text-white text-decoration-none fw-semibold">
                    Feed
                </a>
                <span class="text-white-50">|</span>
                <a href="subir_publicacion.php" class="text-white text-decoration-none fw-semibold">
                    Publicar
                </a>
                <span class="text-white-50">|</span>
                <a href="perfil.php" class="text-white text-decoration-none fw-semibold">
                    Mi Perfil
                </a>
                <span class="text-white-50">|</span>
                <a href="#" onclick="logout(); return false;" class="text-white text-decoration-none fw-semibold">
                    Cerrar sesión
                </a>
            <?php else: ?>
                <a href="inicio_sesion.php" class="text-white text-decoration-none fw-semibold">
                    Iniciar sesión
                </a>
                <span class="text-white-50">|</span>
                <a href="registro.php" class="text-white text-decoration-none fw-semibold">
                    Registrarse
                </a>
            <?php endif; ?>
        </div>

    </div>
</header>

<script>
    const API_BASE = "http://localhost:8080/tema5maven/rest";

    function logout() {
        sessionStorage.removeItem('usuario');
        sessionStorage.removeItem('usuario_id');
        window.location.href = 'inicio_sesion.php';
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
