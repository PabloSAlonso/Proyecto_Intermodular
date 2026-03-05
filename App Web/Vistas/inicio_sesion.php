<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../src/styles.css" rel="stylesheet">
</head>

<body>
    <div class="container-narrow" style="min-height: 100vh; display: flex; align-items: center; justify-content: center;">
        <div class="auth-card animate-slide-up">
            <img src="../src/imagenes/Klyer-logo-transparent.png" alt="KLYER" class="auth-logo">
            <h1 class="auth-title">KLYER</h1>
            <p class="auth-subtitle">Inicia sesión en tu cuenta</p>

            <div id="errorMessage" class="message message-error"></div>

            <form id="loginForm">
                <div class="form-group">
                    <label class="form-label" for="email">Usuario (nickname)</label>
                    <input type="text" class="form-control" placeholder="Tu usuario" required id="email">
                </div>

                <div class="form-group">
                    <label class="form-label" for="password">Contraseña</label>
                    <input type="password" class="form-control" placeholder="******" required minlength="6" id="password">
                </div>

                <button type="submit" class="btn btn-primary btn-full" id="btnIniciarSesion">
                    Iniciar sesión
                </button>
            </form>

            <div class="text-center mt-4">
                <p class="text-muted mb-0">¿No tienes cuenta? <a href="registro.php" class="link">Regístrate</a></p>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        // Usar el proxy genérico
        const API_PROXY = "../api_proxy.php";

        // If already logged in, redirect to feed
        const usuario = sessionStorage.getItem('usuario');
        if (usuario) {
            window.location.href = 'feed.php';
        }

        const form = document.getElementById('loginForm');
        const btnIniciarSesion = document.getElementById('btnIniciarSesion');
        const errorMessage = document.getElementById('errorMessage');

        function showError(message) {
            errorMessage.textContent = message;
            errorMessage.classList.add('show');
        }

        function hideError() {
            errorMessage.classList.remove('show');
        }

        form.addEventListener('submit', async function(e) {
            e.preventDefault();
            hideError();
            
            // Login usa NICKNAME, no email
            const username = document.getElementById('email').value.trim(); // Este campo ahora es para el nickname
            const password = document.getElementById('password').value;

            if (!username || !password) {
                showError('Por favor, completa todos los campos');
                return;
            }

            btnIniciarSesion.disabled = true;
            btnIniciarSesion.textContent = 'Iniciando sesión...';

            try {
                const apiPath = `/usuarios/login/${encodeURIComponent(username)}/${encodeURIComponent(password)}`;
                console.log('Login request to:', `${API_PROXY}?path=${apiPath}`);
                
                const response = await fetch(`${API_PROXY}?path=${apiPath}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });

                console.log('Response status:', response.status);
                const responseText = await response.text();
                console.log('Response text:', responseText);

                // Intentar parsear la respuesta
                try {
                    const data = JSON.parse(responseText);
                    console.log('Parsed data:', data);
                    
                    // Verificar si la respuesta contiene datos de usuario (éxito) o un error
                    // La API devuelve el usuario con id y nickname, o un error con message/error
                    const userId = data.id || data.id_usuario;
                    if (userId && data.nickname) {
                        // Login exitoso - tiene datos de usuario
                        console.log('Login success:', data);
                        
                        // Store user data
                        sessionStorage.setItem('usuario', JSON.stringify(data));
                        sessionStorage.setItem('usuario_id', userId);
                        
                        window.location.href = 'feed.php';
                    } else if (data.error || data.message) {
                        // La API devolvió un error
                        showError(data.error || data.message || 'Email o contraseña incorrectos');
                    } else {
                        // Respuesta inesperada
                        console.log('Unexpected response:', data);
                        showError('Respuesta inesperada del servidor');
                    }
                } catch (e) {
                    // No es JSON válido
                    console.log('Parse error:', e);
                    if (responseText.includes('incorrecta') || responseText.includes('no encontrado')) {
                        showError('Email o contraseña incorrectos');
                    } else {
                        showError('Error en el servidor: ' + responseText);
                    }
                }
            } catch (error) {
                console.error('Error completo:', error);
                showError('Error de conexión. Verifica que el servidor PHP esté funcionando.');
            } finally {
                btnIniciarSesion.disabled = false;
                btnIniciarSesion.textContent = 'Iniciar sesión';
            }
        });

        document.querySelectorAll('#loginForm input').forEach(input => {
            input.addEventListener('input', hideError);
        });
    </script>
</body>

</html>

