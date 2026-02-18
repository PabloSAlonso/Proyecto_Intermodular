<?php
// Simulación de usuario (luego vendrá de base de datos)
$usuario = [
    "nickname" => "nickname",
    "descripcion" => "Descripción del usuario",
    "foto" => "img/user_icon.png"
];

$publicaciones = [
    ["imagen" => "img/post1.jpg", "descripcion" => "Mi primera publicación"],
    ["imagen" => "img/post2.jpg", "descripcion" => "Otra publicación"]
];
?>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Perfil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../src/styles.css">
</head>

<body>
    <?php require_once '../components/header_feed.php' ?>

    <div class="perfil-container">

        <div class="perfil-header">
            <img src="<?= $usuario['foto'] ?>" class="foto-perfil">
            <h2><?= $usuario['nickname'] ?></h2>
            <p><?= $usuario['descripcion'] ?></p>

            <a href="editar_perfil.php" class="btn-editar">Editar perfil</a>
            <a href="subir_publicacion.php" class="btn-publicar">+ Nueva publicación</a>
        </div>


        <div class="publicaciones">
            <?php foreach ($publicaciones as $post): ?>
                <div class="card-post">
                    <img src="<?= $post['imagen'] ?>">
                    <p><?= $post['descripcion'] ?></p>
                </div>
            <?php endforeach; ?>
        </div>

    </div>

</body>

</html>