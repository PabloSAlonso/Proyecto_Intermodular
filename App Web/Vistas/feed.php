<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feed - Klyer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f5f5f5; }
        .post-card { background: white; border-radius: 12px; margin-bottom: 20px; overflow: hidden; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
        .post-header { padding: 12px 16px; display: flex; align-items: center; gap: 10px; }
        .avatar { width: 40px; height: 40px; border-radius: 50%; object-fit: cover; }
        .post-img { width: 100%; display: block; }
        .post-body { padding: 12px 16px; }
        .post-actions { padding: 8px 16px; border-top: 1px solid #eee; display: flex; gap: 10px; }
        .btn-like { background: none; border: none; color: #0ea5e9; cursor: pointer; font-size: 14px; }
        .btn-like.liked { color: #e11d48; }
        .navbar { background: #0ea5e9; padding: 12px 20px; display: flex; justify-content: space-between; align-items: center; }
        .navbar-brand { color: white; font-size: 20px; font-weight: bold; text-decoration: none; display: flex; align-items: center; gap: 10px; }
        .navbar-brand img { width: 35px; height: 35px; }
        .nav-links { display: flex; gap: 15px; align-items: center; }
        .nav-links a { color: white; text-decoration: none; font-size: 14px; }
        .nav-links span { color: white; font-size: 14px; }
    </style>
</head>

<body>
    <!-- Navigation -->
    <nav class="navbar sticky-top">
        <a href="feed.php" class="navbar-brand">
            <img src="../src/imagenes/Klyer-logo-transparent.png" alt="Logo">KLYER
        </a>
        <div class="nav-links">
            <span id="userName">Hola</span>
            <a href="feed.php">Feed</a>
            <a href="subir_publicacion.php">Publicar</a>
            <a href="perfil.php">Mi Perfil</a>
            <a href="#" onclick="logout()">Cerrar sesión</a>
        </div>
    </nav>

    <div class="container my-4">    
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div id="publicationsContainer">
                    <p class="text-center">Cargando publicaciones...</p>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        // Usar el proxy para la API (ruta relativa)
        const API_PROXY = "../api_proxy.php";

        // Check if user is logged in
        const usuario = sessionStorage.getItem('usuario');
        if (!usuario) {
            window.location.href = 'inicio_sesion.php';
        }

        // Get user info
        const userData = usuario ? JSON.parse(usuario) : null;
        if (userData && userData.nombre) {
            document.getElementById('userName').textContent = 'Hola, ' + userData.nombre;
        }

        // Logout function
        function logout() {
            sessionStorage.removeItem('usuario');
            sessionStorage.removeItem('usuario_id');
            window.location.href = 'inicio_sesion.php';
        }

        // Load publications
        async function loadPublications() {
            try {
                const response = await fetch(API_PROXY + 'publicaciones/todas');
                
                if (!response.ok) {
                    throw new Error('Error al cargar publicaciones');
                }

                const publicaciones = await response.json();
                const container = document.getElementById('publicationsContainer');

                if (!publicaciones || publicaciones.length === 0) {
                    container.innerHTML = '<div class="alert alert-info">No hay publicaciones todavía.</div>';
                    return;
                }

                container.innerHTML = publicaciones.map(pub => `
                    <div class="post-card">
                        <div class="post-header">
                            <img src="${pub.foto_perfil || '../src/imagenes/user.webp'}" class="avatar" alt="Foto de perfil">
                            <div>
                                <strong>${pub.nombre || 'Usuario'}</strong>
                                <div class="text-muted small">${pub.nickname || ''}</div>
                            </div>
                        </div>
                        <img src="${pub.imagen}" class="post-img" alt="Imagen de publicación">
                        <div class="post-body">
                            <p class="mb-0">${pub.descripcion || ''}</p>
                        </div>
                        <div class="post-actions">
                            <button class="btn-like" data-id="${pub.id_publicacion}" onclick="toggleLike(this)">
                                ♥ ${pub.likes || 0}
                            </button>
                            <button class="btn-like">
                                💬 ${pub.comentarios || 0}
                            </button>
                        </div>
                    </div>
                `).join('');

            } catch (error) {
                console.error('Error:', error);
                document.getElementById('publicationsContainer').innerHTML = 
                    '<div class="alert alert-danger">Error al cargar las publicaciones. Asegúrate de que la API esté funcionando.</div>';
            }
        }

        // Toggle like
        function toggleLike(btn) {
            btn.classList.toggle('liked');
        }

        // Load on page load
        loadPublications();
    </script>

</body>

</html>

