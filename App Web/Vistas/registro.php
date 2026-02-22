<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear cuenta | KLYER</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="../src/styles.css">
    <style>
        :root {
            --primary: #0ea5e9;
            --primary-dark: #0284c7;
            --primary-light: #e0f2fe;
            --dark: #0f172a;
            --dark-secondary: #1e293b;
            --gray: #64748b;
            --gray-light: #f1f5f9;
            --white: #ffffff;
            --error: #ef4444;
            --success: #10b981;
        }

        @media (prefers-color-scheme: dark) {
            :root {
                --dark: #f8fafc;
                --dark-secondary: #e2e8f0;
                --gray: #94a3b8;
                --gray-light: #1e293b;
                --white: #0f172a;
            }
        }

        * {
            box-sizing: border-box;
        }

        body {
            font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
            background: linear-gradient(135deg, #e0f2fe 0%, #f0f9ff 50%, #ffffff 100%);
            min-height: 100vh;
            margin: 0;
            display: flex;
            flex-direction: column;
        }

        .main-container {
            flex: 1;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 40px 20px;
        }

        .register-wrapper {
            width: 100%;
            max-width: 400px;
        }

        .register-card {
            background: var(--white);
            border-radius: 24px;
            padding: 48px 40px;
            box-shadow: 
                0 4px 6px -1px rgba(0, 0, 0, 0.1),
                0 2px 4px -1px rgba(0, 0, 0, 0.06),
                0 20px 25px -5px rgba(0, 0, 0, 0.1);
            border: 1px solid rgba(14, 165, 233, 0.1);
        }

        @media (prefers-color-scheme: dark) {
            .register-card {
                background: #1e293b;
                border: 1px solid rgba(14, 165, 233, 0.2);
                box-shadow: 
                    0 4px 6px -1px rgba(0, 0, 0, 0.3),
                    0 20px 25px -5px rgba(0, 0, 0, 0.4);
            }
        }

        .brand-section {
            text-align: center;
            margin-bottom: 36px;
        }

        .brand-logo {
            width: 64px;
            height: 64px;
            margin-bottom: 16px;
            transition: transform 0.3s ease;
        }

        .brand-logo:hover {
            transform: scale(1.05);
        }

        .brand-title {
            font-size: 32px;
            font-weight: 700;
            color: var(--dark);
            margin: 0 0 8px 0;
            letter-spacing: -0.5px;
        }

        @media (prefers-color-scheme: dark) {
            .brand-title {
                color: #f1f5f9;
            }
        }

        .brand-subtitle {
            color: var(--gray);
            font-size: 15px;
            margin: 0;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-label {
            display: block;
            font-size: 13px;
            font-weight: 600;
            color: var(--dark-secondary);
            margin-bottom: 8px;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        @media (prefers-color-scheme: dark) {
            .form-label {
                color: #e2e8f0;
            }
        }

        .form-control {
            width: 100%;
            padding: 14px 16px;
            font-size: 15px;
            border: 2px solid #e2e8f0;
            border-radius: 12px;
            background: var(--white);
            color: var(--dark);
            transition: all 0.2s ease;
            outline: none;
        }

        @media (prefers-color-scheme: dark) {
            .form-control {
                background: #0f172a;
                border-color: #334155;
                color: #f1f5f9;
            }
        }

        .form-control:focus {
            border-color: var(--primary);
            box-shadow: 0 0 0 4px rgba(14, 165, 233, 0.15);
        }

        .form-control::placeholder {
            color: var(--gray);
            opacity: 0.7;
        }

        .form-row {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 16px;
        }

        @media (max-width: 480px) {
            .form-row {
                grid-template-columns: 1fr;
            }
        }

        .checkbox-wrapper {
            display: flex;
            align-items: flex-start;
            gap: 12px;
            margin: 24px 0;
        }

        .checkbox-wrapper input[type="checkbox"] {
            width: 20px;
            height: 20px;
            margin: 0;
            cursor: pointer;
            accent-color: var(--primary);
            flex-shrink: 0;
            margin-top: 2px;
        }

        .checkbox-wrapper label {
            font-size: 13px;
            color: var(--gray);
            cursor: pointer;
            line-height: 1.5;
        }

        .checkbox-wrapper a {
            color: var(--primary);
            text-decoration: none;
            font-weight: 500;
        }

        .checkbox-wrapper a:hover {
            text-decoration: underline;
        }

        .btn-submit {
            width: 100%;
            padding: 16px 24px;
            font-size: 16px;
            font-weight: 600;
            color: white;
            background: linear-gradient(135deg, var(--primary) 0%, var(--primary-dark) 100%);
            border: none;
            border-radius: 12px;
            cursor: pointer;
            transition: all 0.3s ease;
            box-shadow: 0 4px 14px rgba(14, 165, 233, 0.35);
            position: relative;
            overflow: hidden;
        }

        .btn-submit:hover {
            transform: translateY(-2px);
            box-shadow: 0 6px 20px rgba(14, 165, 233, 0.45);
        }

        .btn-submit:active {
            transform: translateY(0);
        }

        .btn-submit:disabled {
            opacity: 0.7;
            cursor: not-allowed;
            transform: none;
        }

        .btn-submit.loading {
            color: transparent;
        }

        .btn-submit.loading::after {
            content: '';
            position: absolute;
            width: 22px;
            height: 22px;
            top: 50%;
            left: 50%;
            margin-left: -11px;
            margin-top: -11px;
            border: 2px solid white;
            border-radius: 50%;
            border-top-color: transparent;
            animation: spin 0.8s linear infinite;
        }

        @keyframes spin {
            to { transform: rotate(360deg); }
        }

        .message {
            padding: 14px 18px;
            border-radius: 12px;
            margin-bottom: 24px;
            font-size: 14px;
            font-weight: 500;
            display: none;
        }

        .message.show {
            display: block;
            animation: slideIn 0.3s ease;
        }

        @keyframes slideIn {
            from {
                opacity: 0;
                transform: translateY(-10px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .message.error {
            background: #fef2f2;
            border: 1px solid #fecaca;
            color: #dc2626;
        }

        @media (prefers-color-scheme: dark) {
            .message.error {
                background: rgba(239, 68, 68, 0.1);
                border-color: rgba(239, 68, 68, 0.3);
            }
        }

        .message.success {
            background: #f0fdf4;
            border: 1px solid #bbf7d0;
            color: #16a34a;
        }

        @media (prefers-color-scheme: dark) {
            .message.success {
                background: rgba(16, 185, 129, 0.1);
                border-color: rgba(16, 185, 129, 0.3);
            }
        }

        /* Login Link */
        .login-section {
            text-align: center;
            margin-top: 28px;
            padding-top: 24px;
            border-top: 1px solid #e2e8f0;
        }

        @media (prefers-color-scheme: dark) {
            .login-section {
                border-top-color: #334155;
            }
        }

        .login-section p {
            color: var(--gray);
            font-size: 14px;
            margin: 0;
        }

        .login-section a {
            color: var(--primary);
            font-weight: 600;
            text-decoration: none;
            transition: color 0.2s ease;
        }

        .login-section a:hover {
            color: var(--primary-dark);
            text-decoration: underline;
        }

        /* Hint text */
        .hint {
            font-size: 12px;
            color: var(--gray);
            margin-top: 6px;
        }

        .required {
            color: var(--error);
        }
    </style>
</head>

<body>

    <div class="main-container">
        <div class="register-wrapper">
            <div class="register-card">
                
                <!-- Brand -->
                <div class="brand-section">
                    <img src="../src/imagenes/Klyer-logo-transparent.png" alt="KLYER" class="brand-logo">
                    <h1 class="brand-title">KLYER</h1>
                    <p class="brand-subtitle">Únete a la comunidad</p>
                </div>

                <!-- Messages -->
                <div id="errorMessage" class="message error"></div>
                <div id="successMessage" class="message success"></div>

                <form id="registroForm">
                    <div class="form-row">
                        <div class="form-group">
                            <label class="form-label" for="nombre">Nombre <span class="required">*</span></label>
                            <input type="text" class="form-control" id="nombre" placeholder="Tu nombre" required>
                        </div>
                        <div class="form-group">
                            <label class="form-label" for="apellidos">Apellidos <span class="required">*</span></label>
                            <input type="text" class="form-control" id="apellidos" placeholder="Tus apellidos" required>
                        </div>
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

                    <div class="form-group">
                        <label class="form-label" for="fechNaci">Fecha de nacimiento <span class="required">*</span></label>
                        <input type="date" class="form-control" id="fechNaci" required>
                    </div>

                    <div class="checkbox-wrapper">
                        <input type="checkbox" id="terminos" required>
                        <label for="terminos">
                            Acepto los <a href="#">términos de servicio</a> y <a href="#">política de privacidad</a>
                        </label>
                    </div>

                    <button type="submit" class="btn-submit" id="btnEnviar">
                        Crear cuenta
                    </button>
                </form>

                <div class="login-section">
                    <p>¿Ya tienes cuenta? <a href="inicio_sesion.php">Iniciar sesión</a></p>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        const form = document.getElementById('registroForm');
        const btnEnviar = document.getElementById('btnEnviar');
        const errorMessage = document.getElementById('errorMessage');
        const successMessage = document.getElementById('successMessage');

        function showError(message) {
            errorMessage.textContent = message;
            errorMessage.classList.add('show');
            successMessage.classList.remove('show');
            window.scrollTo({ top: 0, behavior: 'smooth' });
        }

        function showSuccess(message) {
            successMessage.textContent = message;
            successMessage.classList.add('show');
            errorMessage.classList.remove('show');
            window.scrollTo({ top: 0, behavior: 'smooth' });
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
            const fechaNaci = document.getElementById('fechNaci').value;

            // Validations
            if (!nombre || !apellidos || !nick || !email || !pass || !fechaNaci) {
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

            const fechaNacDate = new Date(fechaNaci);
            const hoy = new Date();
            if (fechaNacDate >= hoy) {
                showError('La fecha de nacimiento debe ser anterior a hoy.');
                return;
            }

            const edad = Math.floor((hoy - fechaNacDate) / (365.25 * 24 * 60 * 60 * 1000));
            if (edad < 13) {
                showError('Debes tener al menos 13 años para registrarte.');
                return;
            }

            const userData = {
                nombre: nombre,
                apellidos: apellidos,
                nickname: nick,
                email: email,
                password: pass,
                foto_perfil: null,
                fecha_nacimiento: fechaNaci,
                fecha_creacion_cuenta: new Date().toISOString().split('T')[0]
            };

            btnEnviar.disabled = true;
            btnEnviar.classList.add('loading');
            btnEnviar.textContent = 'Creando cuenta...';

            try {
                const response = await fetch('http://localhost:8080/apirest/rest/usuarios/insertar', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(userData)
                });

                if (response.ok) {
                    showSuccess('¡Cuenta creada! Redirigiendo al login...');
                    sessionStorage.setItem('registroEmail', email);
                    setTimeout(() => {
                        window.location.href = 'inicio_sesion.php';
                    }, 2000);
                } else {
                    const errorText = await response.text();
                    if (errorText.includes('nickname') || errorText.includes('unique')) {
                        showError('El nombre de usuario ya está en uso.');
                    } else if (errorText.includes('email') || errorText.includes('unique')) {
                        showError('El correo ya está registrado.');
                    } else {
                        showError('Error al crear la cuenta. Intenta de nuevo.');
                    }
                }
            } catch (error) {
                console.error('Error:', error);
                showError('Error de conexión. Verifica que la API esté funcionando.');
            } finally {
                btnEnviar.disabled = false;
                btnEnviar.classList.remove('loading');
                btnEnviar.textContent = 'Crear cuenta';
            }
        });

        document.querySelectorAll('#registroForm input').forEach(input => {
            input.addEventListener('input', hideMessages);
        });
    </script>

</body>

</html>

