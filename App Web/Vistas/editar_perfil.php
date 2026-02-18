<?php
session_start();
require_once "config/Request.php";

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
    <link rel="stylesheet" href="style.css">
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

        <br>
        <a href="perfil.php">← Volver al perfil</a>
    </div>

</body>

</html>