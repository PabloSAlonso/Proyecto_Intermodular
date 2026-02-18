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
    <style>
        :root {
            --bg-color: #ffffff;
            --text-color: #000000;
            --card-color: #f2f2f2;
            --primary: #2962FF;
        }

        @media (prefers-color-scheme: dark) {
            :root {
                --bg-color: #121212;
                --text-color: #ffffff;
                --card-color: #1e1e1e;
                --primary: #90CAF9;
            }
        }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: var(--bg-color);
            color: var(--text-color);
        }

        .perfil-container {
            max-width: 900px;
            margin: auto;
            padding: 20px;
        }

        .perfil-header {
            text-align: center;
            margin-bottom: 30px;
        }

        .foto-perfil {
            width: 120px;
            height: 120px;
            border-radius: 50%;
            object-fit: cover;
            border: 3px solid var(--primary);
        }

        .btn-editar {
            display: inline-block;
            margin-top: 10px;
            padding: 8px 16px;
            background-color: var(--primary);
            color: white;
            text-decoration: none;
            border-radius: 8px;
        }

        .publicaciones {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 20px;
        }

        .card-post {
            background-color: var(--card-color);
            padding: 10px;
            border-radius: 12px;
        }

        .card-post img {
            width: 100%;
            border-radius: 8px;
        }
    </style>
</head>

<body>

    <div class="perfil-container">

        <div class="perfil-header">
            <img src="<?= $usuario['foto'] ?>" class="foto-perfil">
            <h2><?= $usuario['nickname'] ?></h2>
            <p><?= $usuario['descripcion'] ?></p>

            <a href="editar_perfil.php" class="btn-editar">Editar perfil</a>
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