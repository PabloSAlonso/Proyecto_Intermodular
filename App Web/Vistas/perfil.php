<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi Perfil - Klyer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f5f5f5; }
        .perfil-container { max-width: 600px; margin: 40px auto; padding: 20px; }
        .perfil-header { background: white; border-radius: 16px; padding: 30px; text-align: center; box-shadow: 0 2px 8px rgba(0,0,0,0.1); margin-bottom: 30px; }
        .foto-perfil { width: 120px; height: 120px; border-radius: 50%; object-fit: cover; margin-bottom: 15px; border: 3px solid #0ea5e9; }
        .perfil-header h2 { margin: 10px 0; color: #333; }
        .perfil-header p { color: #666; }
        .btn-editar { display: inline-block; padding: 10px 20px; background: #0ea5e9; color: white; text-decoration: none; border-radius: 8px; margin: 5px; }
        .btn-publicar { display: inline-block; padding: 10px 20px; background: #10b981; color: white; text-decoration: none; border-radius: 8px; margin: 5px; }
        .publicaciones { display: grid; grid-template-columns: repeat(3, 1fr); gap: 10px; }
        .card-post { background: white; border-radius: 8px; overflow: hidden; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        .card-post img { width: 100%; aspect-ratio: 1; object-fit: cover; }
        .card-post p { padding: 10px; font-size: 12px; color: #666; margin: 0; }
        .navbar { background: #0ea5e9; padding: 12px 20px; display: flex; justify-content: space-between; align-items: center; }
        .navbar-brand { color: white; font-size: 20px; font-weight: bold; text-decoration: none; display: flex; align-items: center; gap: 10px; }
        .navbar-brand img { width: 35px; height: 35px; }
        .nav-links { display: flex; gap: 15px; align-items: center; }
        .nav-links a { color: white; text-decoration: none; font-size: 14px; }
        .nav-links span { color: white; font-size: 14px; }
    </style>
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

    <div class="perfil-container">
        <div class="perfil-header">
            <img id="userPhoto" src="../src/imagenes/user.webp" class="foto-perfil" alt="Foto de perfil">
            <h2 id="userNickname">Cargando...</h2>
            <p id="userDescripcion"></p>

            <a href="editar_perfil.php" class="btn-editar">Editar perfil</a>
            <a href="subir_publicacion.php" class="btn-publicar">+ Nueva publicación</a>
        </div>

        <h3 class="mb-3">Mis Publicaciones</h3>
        <div class="publicaciones" id="publicationsContainer">
            <p class="text-center col-12">Cargando publicaciones...</p>
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
                    container.innerHTML = '<p class="text-center col-12">No hay publicaciones todavía.</p>';
                    return;
                }

                container.innerHTML = publicaciones.map(pub => `
                    <div class="card-post">
                        <img src="data:image/jpeg;base64,${pub.imagen}" alt="Imagen">
                        <p>${pub.descripcion || ''}</p>
                    </div>
                `).join('');

            } catch (error) {
                console.error('Error:', error);
                document.getElementById('publicationsContainer').innerHTML = 
                    '<p class="text-center col-12">Error al cargar las publicaciones.</p>';
            }
        }

        // Load on page load
        loadProfile();
        loadUserPublications();
    </script>

</body>

</html>
