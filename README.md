📌 ms-consulta_tickets
✅ Descripción
Microservicio para la gestión y consulta de tickets asociados a usuarios. Permite crear, actualizar, eliminar y consultar tickets, con soporte para filtrado y caché en consultas.

✅ Versiones

Java: 21
Spring Boot: 3.2.3


✅ Tecnologías utilizadas

Spring Boot Web → Para exponer endpoints REST.
Spring Data JPA → Acceso a base de datos.
H2 Database → Base de datos en memoria para pruebas.
Spring Security + JWT → Autenticación y autorización.
Spring Validation → Validación de datos.
Spring Cache + Caffeine → Caché en consultas.
Springdoc OpenAPI → Documentación Swagger.
Lombok → Reducción de código boilerplate.
JUnit 5 → Pruebas unitarias.
Jacoco → Reporte de cobertura.
Actuator → Monitoreo del microservicio.


✅ Swagger
La documentación de la API está disponible en:
http://localhost:8080/swagger-ui.html


✅ Autenticación
Este servicio utiliza JWT.
Siempre que inicies el proyecto debes generar un token mediante el endpoint de autenticación (por ejemplo /auth/login).
Cómo usar el token

Obtén el token en el login.
Inclúyelo en cada petición en el header:

Authorization: Bearer <TOKEN>


✅ Endpoints principales
Usuarios

POST /usuarios → Crear usuario.
GET /usuarios/{id} → Consultar usuario por ID.

Tickets

POST /tickets → Crear ticket.
PUT /tickets/{id} → Actualizar ticket.
DELETE /tickets/{id} → Eliminar ticket.
GET /tickets/{id} → Consultar ticket por ID.
GET /tickets → Consultar tickets filtrados (por status y usuarioId).


✅ Caché

Se utiliza Caffeine para cachear:

obtenerPorId(UUID id) → Cachea ticket por ID.
obtenerTicketsFiltrados(Status status, UUID usuarioId, Pageable pageable) → Cachea resultados filtrados.


La caché se invalida automáticamente al crear, actualizar o eliminar tickets.
Método manual para limpiar caché por usuario:

Java@CacheEvict(value = "tickets", key = "#usuarioId")public void limpiarCacheUsuario(Long usuarioId) { }Mostrar más líneas

✅ Ejemplo de entrada (crear ticket)
JSONPOST /ticketsContent-Type: application/jsonAuthorization: Bearer <TOKEN>{  "descripcion": "Error en el sistema",  "status": "ABIERTO",  "usuarioId": 1}Mostrar más líneas
✅ Ejemplo de salida
JSON{  "id": "b3f1c2d4-8a7e-4f9a-9f3d-123456789abc",  "descripcion": "Error en el sistema",  "status": "ABIERTO",  "usuario": {    "id": 1,    "nombres": "Juan",    "apellidos": "Perez"  }}