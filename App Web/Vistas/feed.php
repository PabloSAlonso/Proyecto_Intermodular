<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feed - Klyer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../src/styles.css" rel="stylesheet">
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

    <div class="container">    
        <div class="row justify-content-center">
            <div class="col-md-10 col-lg-8">
                <div id="publicationsContainer">
                    <div class="text-center text-muted">Cargando publicaciones...</div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        const API_PROXY = "../api_proxy.php";

        const usuario = sessionStorage.getItem('usuario');
        const usuario_id = sessionStorage.getItem('usuario_id');

        document.addEventListener('DOMContentLoaded', function() {
            if (!usuario && !usuario_id) {
                window.location.href = 'inicio_sesion.php';
                return;
            }

            const userData = usuario ? JSON.parse(usuario) : null;
            if (userData && userData.nombre) {
                document.getElementById('userName').textContent = 'Hola, ' + userData.nombre;
            }

            loadPublications();
        });

        async function loadPublications() {
            try {
                const response = await fetch(`${API_PROXY}?path=/publicaciones/todas`);
                if (!response.ok) {
                    throw new Error(`Error al cargar publicaciones: ${response.statusText}`);
                }

                const responseText = await response.text();
                let publicaciones;
                try {
                    publicaciones = JSON.parse(responseText);
                } catch (e) {
                    console.error("Respuesta del servidor no válida:", responseText);
                    throw new Error("La respuesta del servidor no es un JSON válido. Revisa la consola.");
                }
                
                publicaciones.reverse(); 

                const container = document.getElementById('publicationsContainer');
                if (!publicaciones || publicaciones.length === 0) {
                    container.innerHTML = '<div class="message message-info show">No hay publicaciones todavía.</div>';
                    return;
                }

                const userCache = new Map();
                const getUserData = async (userId) => {
                    if (!userId) return { nickname: 'Usuario desconocido', foto_perfil: null };
                    if (userCache.has(userId)) return userCache.get(userId);
                    
                    try {
                        const userResponse = await fetch(`${API_PROXY}?path=/usuarios/obtenerId/${userId}`);
                        if (userResponse.ok) {
                            const user = await userResponse.json();
                            userCache.set(userId, user);
                            return user;
                        }
                    } catch (e) {
                        console.error(`Error al obtener el usuario ${userId}`, e);
                    }
                    return { nickname: 'Usuario desconocido', foto_perfil: null };
                };

                const userPromises = publicaciones.map(pub => getUserData(pub.id_usuario));
                const usersData = await Promise.all(userPromises);

                const html = publicaciones.map((pub, index) => {
                    const user = usersData[index];
                    const userNickname = user.nickname || 'Usuario';
                    const userAvatar = user.foto_perfil
                        ? `data:image/jpeg;base64,${user.foto_perfil}`
                        : '../src/imagenes/user.webp';
                    
                    const imagenHtml = pub.imagen
                        ? `<img src="data:image/jpeg;base64,${pub.imagen}" class="post-image" alt="Imagen de publicación">`
                        : '';

                    return `
                        <div class="post-card">
                            <div class="post-header">
                                <img src="${userAvatar}" class="post-avatar" alt="Foto de perfil de ${userNickname}">
                                <div class="post-user">${userNickname}</div>
                            </div>
                            ${imagenHtml}
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
                        </div>`;
                }).join('');

                container.innerHTML = html;

            } catch (error) {
                console.error('Error en loadPublications:', error);
                document.getElementById('publicationsContainer').innerHTML = 
                    '<div class="message message-error show">Error al cargar las publicaciones. Revisa la consola para más detalles.</div>';
            }
        }

        function logout() {
            sessionStorage.clear();
            window.location.href = 'inicio_sesion.php';
        }


        function toggleLike(btn) {
            btn.classList.toggle('liked');
            
        }

    </script>

</body>

</html>

