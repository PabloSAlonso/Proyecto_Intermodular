# Arquitectura

Arquitectura basada en API REST.

Clientes:

WebApp (PHP)
MobileApp (Java)
DesktopApp (C#)

Todos consumen:

/Api

La API gestiona:

- usuarios
- publicaciones

Estructura:

Api/
 ├ usuarios/
 │  ├ create.php
 │  ├ login.php
 │  └ get.php
 │
 ├ publicaciones/
 │  ├ create.php
 │  ├ list.php
 │  └ delete.php

Base de datos:

users
posts