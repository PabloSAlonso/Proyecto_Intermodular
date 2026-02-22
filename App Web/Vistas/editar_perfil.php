<?php
session_start();
require_once "../config/Request.php";

$request = new Request("https://proyecto-intermodular-kpzv.onrender.com/rest");

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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../src/styles.css">
</head>

<body>
    <?php require_once '../components/header_feed.php' ?>

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