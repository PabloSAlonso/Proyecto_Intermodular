<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Subir Publicación</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f5f5f5;
        }

        .container {
            max-width: 500px;
            margin: 40px auto;
            padding: 20px;
        }

        .form-card {
            background: white;
            border-radius: 16px;
            padding: 30px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #333;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: 500;
        }

        .form-group input,
        .form-group textarea {
            width: 100%;
            padding: 12px;
            border: 2px solid #e2e8f0;
            border-radius: 8px;
            font-size: 14px;
        }

        .form-group input:focus,
        .form-group textarea:focus {
            border-color: #0ea5e9;
            outline: none;
        }

        .btn-publicar {
            width: 100%;
            padding: 14px;
            background: linear-gradient(135deg, #0ea5e9, #0284c7);
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
        }

        .btn-publicar:hover {
            transform: translateY(-2px);
        }

        .btn-publicar:disabled {
            opacity: 0.7;
            cursor: not-allowed;
        }

        .message {
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 20px;
            text-align: center;
            display: none;
        }

        .message.show {
            display: block;
        }

        .message.success {
            background: #d1fae5;
            color: #065f46;
        }

        .message.error {
            background: #fee2e2;
            color: #991b1b;
        }

        .volver-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #0ea5e9;
            text-decoration: none;
        }

        .navbar {
            background: #0ea5e9;
            padding: 12px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .navbar-brand {
            color: white;
            font-size: 20px;
            font-weight: bold;
            text-decoration: none;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .navbar-brand img {
            width: 35px;
            height: 35px;
        }

        .nav-links {
            display: flex;
            gap: 15px;
            align-items: center;
        }

        .nav-links a {
            color: white;
            text-decoration: none;
            font-size: 14px;
        }

        .nav-links span {
            color: white;
            font-size: 14px;
        }
    </style>
</head>

<body>
    <!-- Navigation -->
    <nav class="navbar">
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
        <div class="form-card">
            <h2>Nueva Publicación</h2>

            <div id="message" class="message"></div>

            <form id="publicacionForm">
                <div class="form-group">
                    <label>Seleccionar imagen</label>
                    <input type="file" name="imagen" accept="image/*" required id="imagenInput">
                </div>

                <div class="form-group">
                    <label>Descripción</label>
                    <textarea name="descripcion" rows="3" required id="descripcionInput" placeholder="¿Qué estás pensando?"></textarea>
                </div>

                <button type="submit" class="btn-publicar" id="btnPublicar">
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
            msgEl.className = 'message show ' + type;
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