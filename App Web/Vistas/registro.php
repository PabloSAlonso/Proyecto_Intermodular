<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light d-flex flex-column min-vh-100">

    <header class="bg-primary text-white py-3">
        <div class="container d-flex align-items-center">
            <img src="/Logotipo_App/Klyer-logo-transparent.png" alt="Logo" width="50" height="50" class="me-3">
            <h5 class="mb-0" style="font-family: 'Times New Roman', Times, serif;">KLYER</h5>
        </div>
    </header>

    <div class="container flex-grow-1 d-flex justify-content-center align-items-center">
        <div class="col-md-7 col-lg-6">

            <div class="card shadow">
                <div class="card-body p-4">

                    <h3 class="text-center text-primary mb-4">
                        Crear Cuenta
                    </h3>

                    <form>
                        <div class="row">

                            <div class="col-md-6 mb-3">
                                <label class="form-label">Nombre</label>
                                <input type="text" class="form-control" required>
                            </div>

                            <div class="col-md-6 mb-3">
                                <label class="form-label">Apellidos</label>
                                <input type="text" class="form-control" required>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Alias de usuario</label>
                            <input type="text" class="form-control" placeholder="@usuario" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Correo electrónico</label>
                            <input type="email" class="form-control" placeholder="correo@ejemplo.com" required>
                        </div>

                        <div class="row">

                            <div class="col-md-6 mb-3">
                                <label class="form-label">Contraseña</label>
                                <input type="password" class="form-control" required placeholder="Mínimo de 6 caracteres">
                            </div>

                            <div class="col-md-6 mb-3">
                                <label class="form-label">Fecha de nacimiento</label>
                                <input type="date" class="form-control" required>
                            </div>
                        </div>

                        <div class="form-check mb-3">
                            <input class="form-check-input" type="checkbox" id="terminos" required>
                            <label class="form-check-label" for="terms">
                                Acepto los términos y condiciones
                            </label>
                        </div>

                        <button type="submit" class="btn btn-primary w-100" id="btnEnviar" disabled>
                            Registrarse
                            <!-- Aqui ya veré si le inicio la sesión directamente o si tras registrarse le hago iniciarla -->
                        </button>
                    </form>

                    <div class="text-center mt-3">
                        <span>
                            ¿Ya tienes cuenta?
                            <a href="inicio_sesion.php" class="link-primary fw-semibold">
                                Inicia sesión
                            </a>
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
        const terminos = document.getElementById("terminos");
        const btnEnviar = document.getElementById("btnEnviar");
        // Cambiar estado del boton según el checkbox
        terminos.addEventListener("change", function() {
            btnEnviar.disabled = !this.checked;
        });
    </script>
</body>
</html>