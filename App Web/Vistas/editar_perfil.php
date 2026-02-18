<?php
session_start();
require_once "../config/Request.php";

$request = new Request("http://localhost:8080/apirest/rest");

$mensaje = "";

// Simulación usuario logueado
$id_usuario = $_SESSION['usuario_id'] ?? 1;
$nombre_actual = $_SESSION['nombre'] ?? "NombreActual";
$nickname_actual = $_SESSION['nickname'] ?? "NickActual";

if ($_SERVER["REQUEST_METHOD"] === "POST") {

    $nombre = $_POST["nombre"];
    $nickname = $_POST["nickname"];
    $password = $_POST["password"];

    $data = [
        "id_usuario" => $id_usuario,
        "nombre" => $nombre,
        "nickname" => $nickname,
        "password" => $password
    ];

    try {
        $response = $request->modificarUser($data);

        if ($response != null) {
            $mensaje = "Perfil actualizado correctamente";

            $_SESSION['nombre'] = $nombre;
            $_SESSION['nickname'] = $nickname;
        } else {
            $mensaje = "Error al actualizar";
        }
    } catch (Exception $e) {
        $mensaje = "Error API: " . $e->getMessage();
    }
}
?>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Editar Perfil</title>
    <style>
        /* ========================= */
        /* EDITAR PERFIL */
        /* ========================= */

        .perfil-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        form {
            background-color: var(--card-color);
            padding: 25px;
            border-radius: 16px;
            max-width: 420px;
            margin: 0 auto;
            display: flex;
            flex-direction: column;
            gap: 15px;
            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.08);
        }

        label {
            font-weight: 600;
            font-size: 14px;
        }

        input {
            padding: 12px;
            border-radius: 10px;
            border: 1px solid #ccc;
            background-color: var(--bg-color);
            color: var(--text-color);
            font-size: 14px;
            transition: 0.2s ease;
        }

        input:focus {
            outline: none;
            border: 1px solid var(--primary);
            box-shadow: 0 0 5px rgba(41, 98, 255, 0.3);
        }

        button.btn-editar {
            margin-top: 10px;
            padding: 12px;
            border-radius: 10px;
            border: none;
            background-color: var(--primary);
            color: white;
            font-weight: bold;
            cursor: pointer;
            transition: 0.2s ease;
        }

        button.btn-editar:hover {
            transform: translateY(-2px);
            opacity: 0.9;
        }

        .perfil-container p {
            text-align: center;
            font-weight: 500;
        }

        .volver-link {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            color: var(--primary);
            font-weight: 600;
            transition: 0.2s ease;
        }

        .volver-link:hover {
            opacity: 0.7;
            transform: translateX(-3px);
        }
    </style>
</head>

<body>

    <div class="perfil-container">
        <h2>Editar Perfil</h2>

        <?php if ($mensaje != ""): ?>
            <p><?= $mensaje ?></p>
        <?php endif; ?>

        <form method="POST">

            <label>Nombre</label>
            <input type="text" name="nombre"
                value="<?= $nombre_actual ?>" required>

            <label>Nickname</label>
            <input type="text" name="nickname"
                value="<?= $nickname_actual ?>" required>

            <label>Nueva contraseña</label>
            <input type="password" name="password" required>

            <button type="submit" class="btn-editar">
                Guardar cambios
            </button>

        </form>

        <div style="text-align:center;">
            <a href="perfil.php" class="volver-link">
                ← Volver al perfil
            </a>
        </div>

    </div>

</body>

</html>