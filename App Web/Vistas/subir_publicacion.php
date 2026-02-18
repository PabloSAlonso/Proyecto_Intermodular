<?php
session_start();
require_once "../config/Request.php";

$request = new Request("http://localhost:8080/apirest/rest");

$mensaje = "";

$id_usuario = $_SESSION['usuario_id'] ?? 1;

if ($_SERVER["REQUEST_METHOD"] === "POST") {

    if (isset($_FILES["imagen"]) && $_FILES["imagen"]["error"] === 0) {

        $descripcion = $_POST["descripcion"];

        $nombreArchivo = time() . "_" . basename($_FILES["imagen"]["name"]);
        $rutaDestino = "uploads/" . $nombreArchivo;

        $tipoArchivo = strtolower(pathinfo($rutaDestino, PATHINFO_EXTENSION));

        // Validar que sea imagen
        $extPermitidas = ["jpg", "jpeg", "png", "webp"];

        if (in_array($tipoArchivo, $extPermitidas)) {

            if (move_uploaded_file($_FILES["imagen"]["tmp_name"], $rutaDestino)) {

                $data = [
                    "id_usuario" => $id_usuario,
                    "descripcion" => $descripcion,
                    "imagen" => $rutaDestino
                ];

                try {
                    $response = $request->createPublication($data);

                    if ($response != null) {
                        $mensaje = "Publicación subida correctamente";
                    } else {
                        $mensaje = "Error al guardar en API";
                    }
                } catch (Exception $e) {
                    $mensaje = "Error API: " . $e->getMessage();
                }
            } else {
                $mensaje = "Error al mover la imagen";
            }
        } else {
            $mensaje = "Formato no permitido";
        }
    } else {
        $mensaje = "Selecciona una imagen";
    }
}
?>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Subir Publicación</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../src/styles.css">
</head>

<body>

    <?php require_once '../components/header.php' ?>
    
    <div class="perfil-container">
        <h2>Nueva Publicación</h2>

        <?php if ($mensaje != ""): ?>
            <p><?= $mensaje ?></p>
        <?php endif; ?>

        <form method="POST" enctype="multipart/form-data">

            <label>Seleccionar imagen</label>
            <input type="file" name="imagen" accept="image/*" required>

            <label>Descripción</label>
            <textarea name="descripcion" rows="3" required></textarea>

            <button type="submit" class="btn-editar">
                Publicar
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