<?php
session_start();

if (isset($_SESSION['usuario']) || isset($_SESSION['usuario_id'])) {
    header('Location: feed.php');
    exit;
}
?>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear cuenta | KLYER</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        :root {
            --primary: #0ea5e9;
            --primary-dark: #0284c7;
        }
        body {
            background: linear-gradient(135deg, #e0f2fe 0%, #f0f9ff 50%, #ffffff 100%);
            min-height: 100vh;
            margin: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 40px 20px;
        }
        .register-card {
            background: white;
            border-radius: 24px;
            padding: 48px 40px;
            box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }
        .brand-logo { width: 64px; height: 64px; display: block; margin: 0 auto 16px; }
        .brand-title { text-align: center; font-size: 32px; font-weight: 700; margin: 0 0 8px; }
        .brand-subtitle { text-align: center; color: #64748b; margin: 0 0 32px; }
        .form-group { margin-bottom: 20px; }
        .form-label { display: block; font-size: 13px; font-weight: 600; margin-bottom: 8px; }
        .form-control { width: 100%; padding: 14px 16px; font-size: 15px; border: 2px solid #e2e8f0; border-radius: 12px; }
        .form-control:focus { border-color: var(--primary); outline: none; box-shadow: 0 0 0 4px rgba(14, 165, 233, 0.15); }
        .btn-submit { width: 100%; padding: 16px; font-size: 16px; font-weight: 600; color: white; background: linear-gradient(135deg, var(--primary), var(--primary-dark)); border: none; border-radius: 12px; cursor: pointer; }
        .btn-submit:hover { transform: translateY(-2px); }
        .btn-submit:disabled { opacity: 0.7; cursor: not-allowed; }
        .message { padding: 14px; border-radius: 12px; margin-bottom: 24px; font-size: 14px; display: none; }
        .message.show { display: block; }
        .message.error { background: #fef2f2; border: 1px solid #fecaca; color: #dc2626; }
        .message.success { background: #f0fdf4; border: 1px solid #bbf7d0; color: #16a34a; }
        .login-section { text-align: center; margin-top: 28px; padding-top: 24px; border-top: 1px solid #e2e8f0; }
        .login-section a { color: var(--primary); font-weight: 600; text-decoration: none; }
        .hint { font-size: 12px; color: #64748b; margin-top: 6px; }
        .required { color: #ef4444; }
    </style>
</head>

<body>

    <div class="register-card">
        <!-- Brand -->
        <img src="../src/imagenes/Klyer-logo-transparent.png" alt="KLYER" class="brand-logo">
        <h1 class="brand-title">KLYER</h1>
        <p class="brand-subtitle">Únete a la comunidad</p>

        <!-- Messages -->
        <div id="errorMessage" class="message error"></div>
        <div id="successMessage" class="message success"></div>

        <form id="registroForm">
            <div class="form-group">
                <label class="form-label" for="nombre">Nombre <span class="required">*</span></label>
                <input type="text" class="form-control" id="nombre" placeholder="Tu nombre" required>
            </div>

            <div class="form-group">
                <label class="form-label" for="apellidos">Apellidos <span class="required">*</span></label>
                <input type="text" class="form-control" id="apellidos" placeholder="Tus apellidos" required>
            </div>

            <div class="form-group">
                <label class="form-label" for="nick">Usuario <span class="required">*</span></label>
                <input type="text" class="form-control" id="nick" placeholder="Nombre de usuario" required>
                <p class="hint">Solo letras, números y guiones bajos</p>
            </div>

            <div class="form-group">
                <label class="form-label" for="email">Email <span class="required">*</span></label>
                <input type="email" class="form-control" id="email" placeholder="tu@email.com" required>
            </div>

            <div class="form-group">
                <label class="form-label" for="pass">Contraseña <span class="required">*</span></label>
                <input type="password" class="form-control" id="pass" placeholder="Mínimo 6 caracteres" required minlength="6">
            </div>

            <button type="submit" class="btn-submit" id="btnEnviar">
                Crear cuenta
            </button>
        </form>

        <div class="login-section">
            <p>¿Ya tienes cuenta? <a href="inicio_sesion.php">Iniciar sesión</a></p>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        // Usar el proxy genérico
        const API_PROXY = "../api_proxy.php";
        
        const form = document.getElementById('registroForm');
        const btnEnviar = document.getElementById('btnEnviar');
        const errorMessage = document.getElementById('errorMessage');
        const successMessage = document.getElementById('successMessage');

        function showError(message) {
            errorMessage.textContent = message;
            errorMessage.classList.add('show');
            successMessage.classList.remove('show');
        }

        function showSuccess(message) {
            successMessage.textContent = message;
            successMessage.classList.add('show');
            errorMessage.classList.remove('show');
        }

        function hideMessages() {
            errorMessage.classList.remove('show');
            successMessage.classList.remove('show');
        }

        function validateEmail(email) {
            return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
        }

        function validateNickname(nickname) {
            return /^[a-zA-Z0-9_]+$/.test(nickname);
        }

        form.addEventListener('submit', async function(e) {
            e.preventDefault();
            hideMessages();

            const nombre = document.getElementById('nombre').value.trim();
            const apellidos = document.getElementById('apellidos').value.trim();
            const nick = document.getElementById('nick').value.trim();
            const email = document.getElementById('email').value.trim();
            const pass = document.getElementById('pass').value;

            // Validations
            if (!nombre || !apellidos || !nick || !email || !pass) {
                showError('Por favor, completa todos los campos.');
                return;
            }

            if (nombre.length < 2) {
                showError('El nombre debe tener al menos 2 caracteres.');
                return;
            }

            if (apellidos.length < 2) {
                showError('Los apellidos deben tener al menos 2 caracteres.');
                return;
            }

            if (!validateNickname(nick)) {
                showError('El usuario solo puede contener letras, números y guiones bajos.');
                return;
            }

            if (nick.length < 3 || nick.length > 50) {
                showError('El usuario debe tener entre 3 y 50 caracteres.');
                return;
            }

            if (!validateEmail(email)) {
                showError('Ingresa un correo electrónico válido.');
                return;
            }

            if (pass.length < 6) {
                showError('La contraseña debe tener al menos 6 caracteres.');
                return;
            }

            // Enviar datos incluyendo apellidos (requerido por la API)
            const userData = {
                nombre: nombre,
                apellidos: apellidos,
                nickname: nick,
                email: email,
                password: pass
            };

            btnEnviar.disabled = true;
            btnEnviar.textContent = 'Creando cuenta...';

            try {
                const apiPath = '/usuarios/insertar';
                console.log('Enviando datos a:', `${API_PROXY}?path=${apiPath}`);
                console.log('Datos:', JSON.stringify(userData));
                
                const response = await fetch(`${API_PROXY}?path=${apiPath}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(userData)
                });

                console.log('Response status:', response.status);
                const responseText = await response.text();
                console.log('Response text:', responseText);

                if (response.ok) {
                    showSuccess('¡Cuenta creada! Redirigiendo al login...');
                    setTimeout(() => {
                        window.location.href = 'inicio_sesion.php';
                    }, 2000);
                } else {
                    const errorText = responseText.toLowerCase();
                    if (errorText.includes('nickname') || errorText.includes('unique')) {
                        showError('El nombre de usuario ya está en uso.');
                    } else if (errorText.includes('email') || errorText.includes('unique')) {
                        showError('El correo ya está registrado.');
                    } else {
                        showError('Error al crear la cuenta: ' + responseText);
                    }
                }
            } catch (error) {
                console.error('Error completo:', error);
                showError('Error de conexión. Verifica que el servidor PHP esté funcionando.');
            } finally {
                btnEnviar.disabled = false;
                btnEnviar.textContent = 'Crear cuenta';
            }
        });

        document.querySelectorAll('#registroForm input').forEach(input => {
            input.addEventListener('input', hideMessages);
        });
    </script>

</body>

</html>
