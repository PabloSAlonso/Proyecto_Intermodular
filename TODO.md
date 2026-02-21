# TODO - Rediseño Página de Registro

## Tareas:
- [x] 1. Rediseñar registro.php con estética clean y elegante (azules y blancos)
- [x] 2. Mejorar JavaScript para validación y manejo de errores
- [x] 3. Corregir la API para usar password_hash
- [x] 4. Estilos mejorados con gradientes y sombras elegantes

## IMPORTANTE - Pasos para probar:
1. **Reiniciar Tomcat** - El error 404 indica que la API no está desplegada
2. Copiar el WAR: `cp apirest/target/apirest.war /ruta/tomcat/webapps/`
3. Reiniciar Tomcat: `./catalina.sh stop` y luego `./catalina.sh start`
4. Verificar que la API funciona en: `http://localhost:8080/apirest/rest/usuarios/insertar`

## Notas Técnicas:
- Columna corregida: `password_hash` (no password)
- Endpoint: `/apirest/rest/usuarios/insertar`
- Puerto: 8080

