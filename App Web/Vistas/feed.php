<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feed</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<?php require_once '../components/header.php' ?>

<body>

    <div id="feed"></div>

    <script>
        fetch('/publicaciones/todas')
            .then(response => response.json())
            .then(publicaciones => {

                const feed = document.getElementById('feed');
                feed.innerHTML = '';

                publicaciones.forEach(pub => {
                    feed.innerHTML += `
        <div class="post">

          <div class="post-header">
            <img src="${pub.foto_perfil}" class="avatar">
            <span>${pub.nombre}</span>
          </div>

          <img src="${pub.imagen}" class="post-img">

          <div class="post-body">
            <p>${pub.descripcion}</p>
          </div>

          <div class="post-actions">
            <button class="like-btn" data-id="${pub.id_publicacion}">
              ${pub.likes}
            </button>
            <button>
              ${pub.comentarios}
            </button>
          </div>

        </div>
      `;
                });
            })
            .catch(error => console.error('Error cargando publicaciones:', error));
    </script>
    <?php require_once '../components/footer.php' ?>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>