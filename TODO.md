# TODO - Mejoras App Móvil

## 1. Corregir error de registro ✅
- [x] Arreglar método registrarUsuario en RegistroCuenta.java para pasar parámetros correctos a la API
- [x] Mejorar manejo de errores en ApiRest.java (timeouts, códigos de error específicos)
- [x] Agregar validación de nickname (mínimo 3 caracteres)

## 2. Eliminar campo fecha de nacimiento ✅
- [x] Quitar campo fecha de nacimiento del layout activity_registro_cuenta.xml
- [x] Actualizar el código Java (RegistroCuenta.java)

## 3. Mejorar estética y diseño ✅
- [x] Actualizar colors.xml con mejores colores y contrastes
- [x] Mejorar activity_inicio_sesion.xml con diseño moderno (gradiente, card)
- [x] Mejorar activity_registro_cuenta.xml con diseño moderno
- [x] Mejorar fragment_perfil.xml con card y mejor diseño
- [x] Mejorar activity_modificar_perfil.xml con diseño consistente
- [x] Crear recursos drawable necesarios (iconos, gradientes)

## 4. Archivos creados/actualizados
- colors.xml - Colores mejorados
- activity_inicio_sesion.xml - Nuevo diseño con gradiente y card
- activity_registro_cuenta.xml - Nuevo diseño sin fecha de nacimiento
- fragment_perfil.xml - Nuevo diseño con card flotante
- activity_modificar_perfil.xml - Diseño consistente
- RegistroCuenta.java - Código actualizado sin fecha
- ApiRest.java - Mejor manejo de errores
- Dibujables creados:
  - gradient_blue.xml
  - gradient_button.xml
  - rounded_top_background.xml
  - rounded_top_background_white.xml
  - ic_person.xml
  - ic_username.xml
  - ic_email.xml
  - ic_lock.xml
  - ic_arrow_back.xml
  - ic_arrow_back_white.xml

