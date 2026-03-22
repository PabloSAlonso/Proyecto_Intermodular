# API Specification

Base URL:
http://localhost:8080/tema5maven/rest

Endpoints:

## Usuarios

POST /usuarios/insertar
Crear usuario

POST /usuarios/login/{username}/{password}
Login usuario

GET /usuarios/obtener/{id}
Obtener usuario por ID

GET /usuarios/obtenerTodos
Obtener todos los usuarios

PUT /usuarios/update/{id}
Actualizar usuario

DELETE /usuarios/eliminar/{id}
Eliminar usuario

## Publicaciones

GET /publicaciones/todas
Obtener todas las publicaciones

GET /publicaciones/usuario/{id_usuario}
Obtener publicaciones de un usuario específico

POST /publicaciones/insertar
Crear publicación

DELETE /publicaciones/eliminar/{id}
Eliminar publicación

GET /publicaciones/obtener/{id}
Obtener publicación por ID

PUT /publicaciones/actualizar/{id}
Actualizar publicación