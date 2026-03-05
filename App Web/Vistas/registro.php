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
    <link href="../src/styles.css" rel="stylesheet">
</head>

<body>
    <div class="container-narrow" style="min-height: 100vh; display: flex; align-items: center; justify-content: center;">
        <div class="auth-card animate-slide-up">
            <!-- Brand -->
            <img src="../src/imagenes/Klyer-logo-transparent.png" alt="KLYER" class="auth-logo">
            <h1 class="auth-title">KLYER</h1>
            <p class="auth-subtitle">Únete a la comunidad</p>

            <!-- Messages -->
            <div id="errorMessage" class="message message-error"></div>
            <div id="successMessage" class="message message-success"></div>

            <form id="registroForm">
                <div class="form-group">
                    <label class="form-label" for="nombre">Nombre <span style="color: var(--danger);">*</span></label>
                    <input type="text" class="form-control" id="nombre" placeholder="Tu nombre" required>
                </div>

                <div class="form-group">
                    <label class="form-label" for="apellidos">Apellidos <span style="color: var(--danger);">*</span></label>
                    <input type="text" class="form-control" id="apellidos" placeholder="Tus apellidos" required>
                </div>

                <div class="form-group">
                    <label class="form-label" for="nick">Usuario <span style="color: var(--danger);">*</span></label>
                    <input type="text" class="form-control" id="nick" placeholder="Nombre de usuario" required>
                    <p class="form-hint">Solo letras, números y guiones bajos</p>
                </div>

                <div class="form-group">
                    <label class="form-label" for="email">Email <span style="color: var(--danger);">*</span></label>
                    <input type="email" class="form-control" id="email" placeholder="tu@email.com" required>
                </div>

                <div class="form-group">
                    <label class="form-label" for="pass">Contraseña <span style="color: var(--danger);">*</span></label>
                    <input type="password" class="form-control" id="pass" placeholder="Mínimo 6 caracteres" required minlength="6">
                </div>

                <button type="submit" class="btn btn-primary btn-full" id="btnEnviar">
                    Crear cuenta
                </button>
            </form>

            <div class="text-center mt-4">
                <p class="text-muted mb-0">¿Ya tienes cuenta? <a href="inicio_sesion.php" class="link">Iniciar sesión</a></p>
            </div>
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

