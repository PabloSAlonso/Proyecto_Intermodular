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
                // Load user data
                const userResponse = await fetch(`${API_PROXY}?path=/usuarios/obtenerId/${userId}`);
                if (userResponse.ok) {
                    // Usar text() primero para evitar errores de sintaxis si devuelve HTML
                    const responseText = await userResponse.text();
                    let user;
                    try {
                        user = JSON.parse(responseText);
                    } catch (e) {
                        console.warn("Respuesta no JSON al cargar usuario, usando datos de sesión.");
                        throw new Error("Respuesta inválida");
                    }

                    document.getElementById('userNickname').textContent = user.nickname || user.nombre || 'Usuario';
                    document.getElementById('userName').textContent = 'Hola, ' + (user.nombre || 'Usuario');
                    if (user.foto_perfil) {
                        // Asumiendo que la API devuelve la imagen como un string base64
                        document.getElementById('userPhoto').src = 'data:image/jpeg;base64,' + user.foto_perfil;
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
                        </div>
                    </div>
                `).join('');

            } catch (error) {
                console.error('Error:', error);
                document.getElementById('publicationsContainer').innerHTML = 
                    '<p class="text-center text-muted col-12">Error al cargar las publicaciones.</p>';
            }
        }

        // Load on page load
        loadProfile();
        loadUserPublications();
    </script>

</body>

</html>

