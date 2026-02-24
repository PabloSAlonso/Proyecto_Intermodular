<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background: linear-gradient(135deg, #e0f2fe 0%, #f0f9ff 50%, #ffffff 100%); min-height: 100vh; display: flex; align-items: center; justify-content: center; padding: 20px; }
        .login-card { background: white; border-radius: 24px; padding: 48px 40px; box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1); max-width: 400px; width: 100%; }
        .brand-logo { width: 64px; height: 64px; display: block; margin: 0 auto 16px; }
        .brand-title { text-align: center; font-size: 32px; font-weight: 700; margin: 0 0 8px; color: #0ea5e9; }
        .brand-subtitle { text-align: center; color: #64748b; margin: 0 0 32px; }
        .form-group { margin-bottom: 20px; }
        .form-label { display: block; font-size: 13px; font-weight: 600; margin-bottom: 8px; }
        .form-control { width: 100%; padding: 14px 16px; font-size: 15px; border: 2px solid #e2e8f0; border-radius: 12px; }
        .form-control:focus { border-color: #0ea5e9; outline: none; box-shadow: 0 0 0 4px rgba(14, 165, 233, 0.15); }
        .btn-submit { width: 100%; padding: 16px; font-size: 16px; font-weight: 600; color: white; background: linear-gradient(135deg, #0ea5e9, #0284c7); border: none; border-radius: 12px; cursor: pointer; }
        .btn-submit:hover { transform: translateY(-2px); }
        .btn-submit:disabled { opacity: 0.7; cursor: not-allowed; }
        .login-section { text-align: center; margin-top: 28px; }
        .login-section a { color: #0ea5e9; font-weight: 600; text-decoration: none; }
        .message { padding: 14px; border-radius: 12px; margin-bottom: 24px; font-size: 14px; display: none; }
        .message.show { display: block; }
        .message.error { background: #fef2f2; border: 1px solid #fecaca; color: #dc2626; }
    </style>
</head>

<body>

    <div class="login-card">
        <img src="../src/imagenes/Klyer-logo-transparent.png" alt="KLYER" class="brand-logo">
        <h1 class="brand-title">KLYER</h1>
        <p class="brand-subtitle">Inicia sesión en tu cuenta</p>

        <div id="errorMessage" class="message error"></div>

        <form id="loginForm">
            <div class="form-group">
                <label class="form-label">Usuario (nickname)</label>
                <input type="text" class="form-control" placeholder="Tu usuario" required id="email">
            </div>

            <div class="form-group">
                <label class="form-label">Contraseña</label>
                <input type="password" class="form-control" placeholder="******" required minlength="6" id="password">
            </div>

            <button type="submit" class="btn-submit" id="btnIniciarSesion">
                Iniciar sesión
            </button>
        </form>

        <div class="login-section">
            <p>¿No tienes cuenta? <a href="registro.php">Regístrate</a></p>
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
