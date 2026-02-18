<?php
require_once '../config/Request.php';
// Get sample publications for testing

?>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feed - Klyer</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #2A2929; 
        }

        .avatar {
            width: 40px;
            height: 40px;
            object-fit: cover;
            border-radius: 50%;
            border: 2px solid #0d6efd;
        }

        .post-img {
            width: 100%;
            max-height: 450px;
            object-fit: cover;
        }

        .like-btn.liked {
            background-color: #e91e63;
            border-color: #e91e63;
            color: white;
        }
    </style>
</head>

<?php require_once '../components/header.php' ?>

<body>

<div class="container my-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <?php if (empty($publicaciones)): ?>
                <div class="alert alert-info">No hay publicaciones todavía.</div>
            <?php else: ?>
                <?php foreach ($publicaciones as $pub): ?>
                <div class="card mb-4 shadow-sm border-0">

                    <div class="card-header bg-white d-flex align-items-center gap-2">
                        <img src="<?php echo htmlspecialchars($pub['foto_perfil']); ?>" class="avatar" alt="Foto de perfil">
                        <strong class="text-primary"><?php echo htmlspecialchars($pub['nombre']); ?></strong>
                        <span class="text-muted small"><?php echo htmlspecialchars($pub['nickname']); ?></span>
                    </div>

                    <img src="<?php echo htmlspecialchars($pub['imagen']); ?>" class="post-img" alt="Imagen de publicación">

                    <div class="card-body">
                        <p class="card-text text-secondary">
                            <?php echo htmlspecialchars($pub['descripcion']); ?>
                        </p>
                    </div>

                    <div class="card-footer bg-light d-flex gap-2">
                        <button class="btn btn-sm btn-primary like-btn" data-id="<?php echo $pub['id_publicacion']; ?>">
                            ♥ <?php echo $pub['likes']; ?>
                        </button>
                        <button class="btn btn-sm btn-outline-secondary">
                            💬 <?php echo $pub['comentarios']; ?>
                        </button>
                    </div>

                </div>
                <?php endforeach; ?>
            <?php endif; ?>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

<script>
    // Simple like button functionality
    document.querySelectorAll('.like-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            this.classList.toggle('liked');
        });
    });
</script>

<?php require_once '../components/footer.php' ?>

</body>
</html>
