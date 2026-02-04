<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feed</title>

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
    </style>
</head>

<?php require_once '../components/header.php' ?>

<body>

<div class="container my-4">
    <div class="row justify-content-center">
        <div class="col-md-8" id="feed"></div>
    </div>
</div>

<script>
    fetch('/publicaciones/todas')
        .then(response => response.json())
        .then(publicaciones => {

            const feed = document.getElementById('feed');
            feed.innerHTML = '';

            publicaciones.forEach(pub => {
                feed.innerHTML += `
                <div class="card mb-4 shadow-sm border-0">

                    <div class="card-header bg-white d-flex align-items-center gap-2">
                        <img src="${pub.foto_perfil}" class="avatar">
                        <strong class="text-primary">${pub.nombre}</strong>
                    </div>

                    <img src="${pub.imagen}" class="post-img">

                    <div class="card-body">
                        <p class="card-text text-secondary">
                            ${pub.descripcion}
                        </p>
                    </div>

                    <div class="card-footer bg-light d-flex gap-2">
                        <button class="btn btn-sm btn-primary like-btn" data-id="${pub.id_publicacion}">
                            ${pub.likes}
                        </button>
                        <button class="btn btn-sm btn-outline-secondary">
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