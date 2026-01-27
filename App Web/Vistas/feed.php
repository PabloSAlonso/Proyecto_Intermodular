<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feed</title>
</head>
<?php require_once '../components/header.php' ?>
<body>
    <?php foreach ($publicaciones as $pub): ?>
    <div class="post">

        <div class="post-header">
            <img src="<?= $pub['foto_perfil'] ?>" class="avatar">
            <span><?= htmlspecialchars($pub['nombre']) ?></span>
        </div>

        <img src="<?= $pub['imagen'] ?>" class="post-img">

        <div class="post-body">
            <p><?= htmlspecialchars($pub['descripcion']) ?></p>
        </div>

        <div class="post-actions">
            <button class="like-btn" data-id="<?= $pub['id_publicacion'] ?>">
                <?= $pub['likes'] ?>
            </button>
            <button>
                <?= $pub['comentarios'] ?>
            </button>
        </div>

    </div>
<?php endforeach; ?>

</body>
</html>