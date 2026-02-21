<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../src/styles.css">
</head>

<body class="bg-light d-flex flex-column min-vh-100">

    <?php require_once '../components/header.php' ?>

    <div class="container flex-grow-1 d-flex justify-content-center align-items-center">
        <div class="col-md-5 col-lg-4">

            <div class="card shadow">
                <div class="card-body p-4">
                    <h3 class="text-center mb-4 text-primary">Iniciar Sesión</h3>
                    <form>
                        <div class="mb-3">
                            <label class="form-label">Correo electrónico</label>
                            <input type="email" class="form-control" placeholder="Formato:micorreo@ejemplo.com" required id="email">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Contraseña</label>
                            <input type="password" class="form-control" placeholder="******" required minlength="6" id="password">
                            
                        </div>

                        <button type="submit" class="btn btn-primary w-100" id="btnIniciarSesion">
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
    <?php require_once '../components/footer.php' ?>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        const btnIniciarSesion = document.getElementById("btnIniciarSesion");

        btnIniciarSesion.addEventListener("click", (e) => {
            e.preventDefault();
            
            // Get values on button click
            const email = document.getElementById("email").value;
            const password = document.getElementById("password").value;

            // Validate required fields
            if (!email || !password) {
                alert("Por favor, completa todos los campos");
                return;
            }

            // Make API call to login
            fetch(`http://localhost:8080/apirest/rest/usuarios/obtener/${encodeURIComponent(email)}/${encodeURIComponent(password)}`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else if (response.status === 404) {
                    throw new Error("Email o contraseña incorrectos");
                } else {
                    throw new Error("Error en el servidor");
                }
            })
            .then(data => {
                // Login successful - store user data in sessionStorage
                sessionStorage.setItem("usuario", JSON.stringify(data));
                alert("¡Bienvenido, " + data.nombre + "!");
                window.location.href = "feed.php";
            })
            .catch(error => {
                console.error("Error:", error);
                alert(error.message);
            });
        });
    </script>
</body>
