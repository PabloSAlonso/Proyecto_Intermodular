<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nueva Publicación - Klyer</title>
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
        <div class="card animate-slide-up" style="max-width: 500px; margin: 0 auto;">
            <div class="card-header">
                <h2 style="margin: 0; font-size: 1.5rem;">Nueva Publicación</h2>
            </div>

            <div id="message" class="message"></div>

            <form id="publicacionForm">
                <div class="form-group">
                    <label class="form-label" for="imagenInput">Seleccionar imagen</label>
                    <input type="file" name="imagen" accept="image/*" required id="imagenInput" class="form-control">
                </div>

                <div class="form-group">
                    <label class="form-label" for="descripcionInput">Descripción</label>
                    <textarea name="descripcion" rows="4" required id="descripcionInput" class="form-control" placeholder="¿Qué estás pensando?"></textarea>
                </div>

                <button type="submit" class="btn btn-success btn-full" id="btnPublicar">
                    Publicar
                </button>
            </form>

            <a href="perfil.php" class="volver-link">← Volver al perfil</a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        const API_PROXY = "../api_proxy.php";

        const usuario = sessionStorage.getItem('usuario');
        const usuario_id = sessionStorage.getItem('usuario_id');

        if (!usuario && !usuario_id) {
            window.location.href = 'inicio_sesion.php';
        }

        const userData = usuario ? JSON.parse(usuario) : null;
        const userId = usuario_id || (userData ? userData.id : null);

        if (userData && userData.nombre) {
            document.getElementById('userName').textContent = 'Hola, ' + userData.nombre;
        }

        function logout() {
            sessionStorage.removeItem('usuario');
            sessionStorage.removeItem('usuario_id');
            window.location.href = 'inicio_sesion.php';
        }

        function showMessage(text, type) {
            const msgEl = document.getElementById('message');
            msgEl.textContent = text;
            msgEl.className = 'message message-' + type + ' show';
        }

        function hideMessage() {
            document.getElementById('message').className = 'message';
        }

        document.getElementById('publicacionForm').addEventListener('submit', async function(e) {
            e.preventDefault();
            hideMessage();

            const imagenInput = document.getElementById('imagenInput');
            const descripcionInput = document.getElementById('descripcionInput');
            const btnPublicar = document.getElementById('btnPublicar');

            if (!imagenInput.files || !imagenInput.files[0]) {
                showMessage('Selecciona una imagen', 'error');
                return;
            }

            const file = imagenInput.files[0];
            const descripcion = descripcionInput.value.trim();

            if (!descripcion) {
                showMessage('Escribe una descripción', 'error');
                return;
            }

            if (!userId) {
                showMessage('No se encontró el usuario. Inicia sesión de nuevo.', 'error');
                return;
            }

            const reader = new FileReader();
            reader.onload = async function() {

                const base64WithPrefix = reader.result;
                const base64 = base64WithPrefix.split(',')[1];

                btnPublicar.disabled = true;
                btnPublicar.textContent = 'Publicando...';

                try {
                    const today = new Date();
                    const fecha = today.getFullYear() + '-' +
                        String(today.getMonth() + 1).padStart(2, '0') + '-' +
                        String(today.getDate()).padStart(2, '0');

                    const publicacion = {
                        id_usuario: parseInt(userId),
                        descripcion: descripcion,
                        imagen: base64, 
                        fecha_publicacion: fecha, 
                        likes: 0,
                        comentarios: 0
                    };

                    const url = `${API_PROXY}?path=/publicaciones/insertar`;
                    console.log('URL being called:', url);
                    console.log('Sending publicacion:', publicacion);

                    const response = await fetch(url, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(publicacion)
                    });

                    console.log('Response status:', response.status);

                    if (response.ok) {
                        showMessage('¡Publicación subida correctamente!', 'success');
                        setTimeout(() => {
                            window.location.href = 'feed.php';
                        }, 1500);
                    } else {
                        const errorText = await response.text();
                        showMessage('Error al publicar: ' + errorText, 'error');
                    }
                } catch (error) {
                    console.error('Error:', error);
                    showMessage('Error de conexión. Verifica que la API esté funcionando.', 'error');
                } finally {
                    btnPublicar.disabled = false;
                    btnPublicar.textContent = 'Publicar';
                }
            };

            reader.onerror = function() {
                showMessage('Error al leer la imagen', 'error');
            };

            reader.readAsDataURL(file);
        });

        document.querySelectorAll('input, textarea').forEach(el => {
            el.addEventListener('input', hideMessage);
        });
    </script>

</body>

</html>

