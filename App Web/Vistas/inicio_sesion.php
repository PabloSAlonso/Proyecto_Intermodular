<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light d-flex flex-column min-vh-100">

    <header class="bg-primary text-white py-3">
        <div class="container d-flex align-items-center">
            <!-- Logo de la app (aun no tengo la imagen pero sera una K azul o blanca) -->
            <img src="/Logotipo_App/Klyer-logo-transparent.png" alt="Logo" width="50" height="50" class="me-3">
            <h5 class="mb-0" style="font-family: 'Times New Roman', Times, serif;">KLYER</h5>
        </div>
    </header>

    <div class="container flex-grow-1 d-flex justify-content-center align-items-center">
        <div class="col-md-5 col-lg-4">

            <div class="card shadow">
                <div class="card-body p-4">
                    <h3 class="text-center mb-4 text-primary">Iniciar Sesión</h3>
                    <form>
                        <div class="mb-3">
                            <label class="form-label">Correo electrónico</label>
                            <input type="email" class="form-control" placeholder="Formato:micorreo@ejemplo.com" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Contraseña</label>
                            <input type="password" class="form-control" placeholder="******" required minlength="6" id="pass">
                            <!-- Agregaré una funcionalidad de cambiar de formato password a formato texto y viceversa al pulsar -->
                            <input type="checkbox" id="mostrar_pass"><span>Mostrar Contraseña</span>
                        </div>

                        <button type="submit" class="btn btn-primary w-100">
                            Iniciar sesión
                        </button>
                    </form>

                    <div class="text-center mt-3">
                        <span class="d-block mt-2">
                            ¿No tienes cuenta?
                            <a href="registro.php">Regístrate</a>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer class="bg-white border-top py-3">
        <div class="container text-center">
            <span class="text-muted small">
                © 2026 · Todos los derechos reservados ·
                <a href="#" class="link-primary">Términos y condiciones</a>
            </span>
        </div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        const checkboxPass = document.getElementById("mostrar_pass");
        const inputPass = document.getElementById("pass")

        checkboxPass.addEventListener("change", function() {
            if (this.checked) {
                inputPass.type = 'text';
            } else {
                inputPass.type = 'password';
            }
        });
    </script>
</body>

</html>