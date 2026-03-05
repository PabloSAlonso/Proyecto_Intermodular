<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Perfil - Klyer</title>
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
                <h2 style="margin: 0; font-size: 1.5rem;">Editar Perfil</h2>
            </div>

            <div id="message" class="message"></div>

            <form id="editProfileForm">
                <div class="form-group">
                    <label class="form-label" for="nombre">Nombre</label>
                    <input type="text" class="form-control" name="nombre" id="nombre" required>
                </div>

                <div class="form-group">
                    <label class="form-label" for="apellidos">Apellidos</label>
                    <input type="text" class="form-control" name="apellidos" id="apellidos" required>
                </div>

                <div class="form-group">
                    <label class="form-label" for="nickname">Nickname</label>
                    <input type="text" class="form-control" name="nickname" id="nickname" required>
                </div>

                <div class="form-group">
                    <label class="form-label" for="email">Email</label>
                    <input type="email" class="form-control" name="email" id="email" required>
                </div>

                <div class="form-group">
                    <label class="form-label" for="password">Nueva contraseña (opcional)</label>
                    <input type="password" class="form-control" name="password" id="password" placeholder="Dejar en blanco para no cambiar">
                </div>

                <button type="submit" class="btn btn-primary btn-full">
                    Guardar cambios
                </button>
            </form>

            <a href="perfil.php" class="volver-link">
                ← Volver al perfil
            </a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        const API_PROXY = "../api_proxy.php";

        const usuario_id = sessionStorage.getItem('usuario_id');
        const usuario = sessionStorage.getItem('usuario');

        if (!usuario_id) {
            window.location.href = 'inicio_sesion.php';
        }

        function logout() {
            sessionStorage.clear();
            window.location.href = 'inicio_sesion.php';
        }

        function showMessage(text, type) {
            const msgEl = document.getElementById('message');
            msgEl.textContent = text;
            msgEl.className = 'message message-' + type + ' show';
        }

        async function loadProfileData() {
            try {
                const response = await fetch(`${API_PROXY}?path=/usuarios/obtenerId/${usuario_id}`);
                if (response.ok) {
                    const user = await response.json();
                    document.getElementById('nombre').value = user.nombre || '';
                    document.getElementById('apellidos').value = user.apellidos || '';
                    document.getElementById('nickname').value = user.nickname || '';
                    document.getElementById('email').value = user.email || '';
                    document.getElementById('userName').textContent = 'Hola, ' + (user.nombre || 'Usuario');
                } else {
                    showMessage('No se pudieron cargar los datos del perfil.', 'error');
                }
            } catch (error) {
                showMessage('Error de conexión al cargar el perfil.', 'error');
            }
        }

        document.getElementById('editProfileForm').addEventListener('submit', async function(e) {
            e.preventDefault();

            const btn = e.target.querySelector('button');
            btn.disabled = true;
            btn.textContent = 'Guardando...';

            const nombre = document.getElementById('nombre').value;
            const apellidos = document.getElementById('apellidos').value;
            const nickname = document.getElementById('nickname').value;
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;

            const dataToUpdate = {
                id: parseInt(usuario_id),
                nombre: nombre,
                apellidos: apellidos,
                nickname: nickname,
                email: email,
            };

            if (password) {
                dataToUpdate.password = password;
            }

            try {
                const response = await fetch(`${API_PROXY}?path=/usuarios/actualizar/${usuario_id}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(dataToUpdate)
                });

                if (response.ok) {
                    showMessage('Perfil actualizado correctamente.', 'success');
                    // Actualizar datos en sessionStorage si es necesario
                    const updatedUser = { ...JSON.parse(usuario), nombre: nombre, nickname: nickname };
                    sessionStorage.setItem('usuario', JSON.stringify(updatedUser));
                    
                    setTimeout(() => {
                        window.location.href = 'perfil.php';
                    }, 1500);
                } else {
                    const errorText = await response.text();
                    let errorMessage = 'Error al actualizar el perfil.';
                    try {
                        const errorJson = JSON.parse(errorText);
                        if (errorJson.error) {
                            errorMessage += ' ' + errorJson.error;
                        }
                    } catch (e) {
                        errorMessage += ' ' + errorText;
                    }
                    showMessage(errorMessage, 'error');
                }
            } catch (error) {
                showMessage('Error de conexión al actualizar el perfil.', 'error');
            } finally {
                btn.disabled = false;
                btn.textContent = 'Guardar cambios';
            }
        });

        document.addEventListener('DOMContentLoaded', function() {
            if (!usuario_id) {
                window.location.href = 'inicio_sesion.php';
            } else {
                loadProfileData();
            }
        });

    </script>

</body>

</html>

