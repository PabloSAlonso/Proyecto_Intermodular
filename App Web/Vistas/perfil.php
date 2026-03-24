<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi Perfil - Klyer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../src/styles.css" rel="stylesheet">
</head>

<body>

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

    <div class="container">
        <div class="profile-header">
            <img id="userPhoto" src="../src/imagenes/user.webp" class="profile-avatar" alt="Foto de perfil">
            <h2 id="userNickname" class="profile-name">Cargando...</h2>
            <p id="userDescripcion" class="profile-bio"></p>

            <div class="profile-actions">
                <a href="editar_perfil.php" class="btn btn-primary">Editar perfil</a>
                <a href="subir_publicacion.php" class="btn btn-success">+ Nueva publicación</a>
                <button class="btn btn-danger" onclick="deleteAccount()" style="background: var(--danger); color: white; border: none; border-radius: var(--radius-md);">Eliminar cuenta</button>
            </div>
        </div>

        <h3 class="mb-4">Mis Publicaciones</h3>
        <div class="publications-grid" id="publicationsContainer">
            <p class="text-center text-muted col-12">Cargando publicaciones...</p>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        // Usar el proxy para la API (ruta relativa)
        const API_PROXY = "../api_proxy.php";

        // Check if user is logged in
        const usuario = sessionStorage.getItem('usuario');
        const usuario_id = sessionStorage.getItem('usuario_id');
        
        if (!usuario && !usuario_id) {
            window.location.href = 'inicio_sesion.php';
        }

        // Get user info
        const userData = usuario ? JSON.parse(usuario) : null;
        
        // Intentar obtener ID de varias fuentes y asegurar que no sea "undefined" string
        let userId = usuario_id;
        if (!userId || userId === "undefined") {
            if (userData) {
                userId = userData.id || userData.id_usuario;
            }
        }

        // Logout function
        function logout() {
            sessionStorage.removeItem('usuario');
            sessionStorage.removeItem('usuario_id');
            window.location.href = 'inicio_sesion.php';
        }

        // Load user profile
        async function loadProfile() {
            if (!userId) {
                document.getElementById('userNickname').textContent = 'Usuario';
                return;
            }

            try {
                // Load user data via API using the canonical endpoint
                if (userId) {
                    const userResponse = await fetch(`${API_PROXY}?path=/usuarios/obtener/${userId}`);
                    if (userResponse.ok) {
                        const user = await userResponse.json();
                    // Use nickname_usuario first, then nickname or nombre as fallback
                    document.getElementById('userNickname').textContent = user.nickname_usuario || user.nickname || user.nombre || 'Usuario';
                        document.getElementById('userName').textContent = 'Hola, ' + (user.nombre || 'Usuario');
                        if (user.foto_perfil) {
                            document.getElementById('userPhoto').src = 'data:image/jpeg;base64,' + user.foto_perfil;
                        }
                    }
                }
            } catch (error) {
                console.error('Error loading user:', error);
                const fallbackName = (userData && (userData.nickname || userData.nombre)) ? (userData.nickname || userData.nombre) : 'Usuario';
                document.getElementById('userNickname').textContent = fallbackName;
                document.getElementById('userName').textContent = 'Hola, ' + fallbackName;
            }
        }

        // Load user publications
        async function loadUserPublications() {
            if (!userId) {
                document.getElementById('publicationsContainer').innerHTML = '<p class="text-center">No se pudo cargar el ID de usuario</p>';
                return;
            }

            try {
                const response = await fetch(`${API_PROXY}?path=/publicaciones/usuario/${userId}`);
                
                if (!response.ok) {
                    throw new Error('Error al cargar publicaciones');
                }

                const responseText = await response.text();
                let publicaciones;
                try {
                    publicaciones = JSON.parse(responseText);
                } catch (e) {
                    throw new Error("La respuesta del servidor no es un JSON válido.");
                }

                const container = document.getElementById('publicationsContainer');

                // Invertir para mostrar las más recientes primero
                publicaciones.reverse();

                if (!publicaciones || publicaciones.length === 0) {
                    container.innerHTML = '<p class="text-center text-muted col-12">No hay publicaciones todavía.</p>';
                    return;
                }

                container.innerHTML = publicaciones.map(pub => `
                    <div class="publication-item">
                        <img src="data:image/jpeg;base64,${pub.imagen}" alt="Imagen">
                        <div class="publication-overlay">
                            <span>♥ ${pub.likes || 0}</span>
                            <span>💬 ${pub.comentarios || 0}</span>
                            <button onclick="deletePublication(${pub.id_publicacion}, event)" style="background: var(--danger); color: white; border: none; border-radius: 50%; width: 32px; height: 32px; cursor: pointer; font-size: 16px;">×</button>
                        </div>
                    </div>
                `).join('');

            } catch (error) {
                console.error('Error:', error);
                document.getElementById('publicationsContainer').innerHTML = 
                    '<p class="text-center text-muted col-12">Error al cargar las publicaciones.</p>';
            }
        }

        async function deletePublication(pubId, event) {
            event.stopPropagation();
            if (!confirm('¿Estás seguro de que quieres eliminar esta publicación?')) return;
            try {
                const response = await fetch(`${API_PROXY}?path=/publicaciones/eliminar/${pubId}`, { method: 'DELETE' });
                if (response.ok) {
                    loadUserPublications();
                } else {
                    alert('Error al eliminar la publicación.');
                }
            } catch (error) {
                alert('Error de conexión al eliminar la publicación.');
            }
        }

        async function deleteAccount() {
            if (!confirm('¿Estás seguro de que quieres eliminar tu cuenta? Esta acción no se puede deshacer.')) return;
            try {
                const response = await fetch(`${API_PROXY}?path=/usuarios/eliminar/${userId}`, { method: 'DELETE' });
                if (response.ok) {
                    sessionStorage.clear();
                    window.location.href = 'inicio_sesion.php';
                } else {
                    alert('Error al eliminar la cuenta.');
                }
            } catch (error) {
                alert('Error de conexión al eliminar la cuenta.');
            }
        }

        // Load on page load
        loadProfile();
        loadUserPublications();
    </script>

</body>

</html>

