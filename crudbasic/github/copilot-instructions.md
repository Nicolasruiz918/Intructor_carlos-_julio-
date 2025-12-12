# Instrucciones rápidas para agentes (Copilot / AI)

Resumen corto
- Proyecto: Java Spring Boot (Maven) con estructura estándar bajo `src/main/java/com/sena/crudbasic`.
- Usa `jakarta.persistence` (JPA) para entidades y el wrapper Maven `mvnw.cmd` para Windows.

Qué inspeccionar primero
- Controladores REST: `src/main/java/com/sena/crudbasic/Controller/CategoryController.java` y `CourseController.java` — exponen `findAll`, `findById`, `save`, `delete`.
- Servicios: `src/main/java/com/sena/crudbasic/service` y `.../service/impl` contienen la lógica de negocio y conversores DTO↔Model (ej. `CategoryServiceImpl`).
- Repositorios: `src/main/java/com/sena/crudbasic/repository` — contiene consultas JPA personalizadas (ej. `filterByNameCategories`).
- Entidades (Model): `src/main/java/com/sena/crudbasic/Model` — busca constructores expuestos (ID autogenerado en `Category`).
- Config: `src/main/resources/application.properties` — puerto y configuración de BD.

Comandos útiles (Windows PowerShell)
- Compilar y empaquetar:
```powershell
.\mvnw.cmd clean package
```
- Ejecutar la aplicación (dev):
```powershell
.\mvnw.cmd spring-boot:run
```
- Buscar clases compiladas:
```powershell
# en la raíz del módulo
Get-ChildItem -Recurse -Filter *.class target
```

Rutas y comportamiento observado
- Actualmente muchos controladores usan mapeos vacíos `@GetMapping("")` y `@PostMapping("")` sin `@RequestMapping` de base — eso crea rutas en `/` para múltiples controladores. Revisa `CategoryController` y `CourseController` y considera que las rutas pueden chocar o producir mapeos ambiguos en el arranque.
- Ejemplos relevantes:
  - `CategoryController` expone `GET ""` y `POST ""` en `http://localhost:8080/` según la configuración actual.
  - `CourseController` expone también `GET ""`, `GET "{id}"`, `GET "filterbytitle/{title}"`, `POST ""`, `DELETE "{id}"`.

Patrones y convenciones del proyecto
- DTO ↔ Model: los servicios implementan métodos conversores `dtoToModel(...)` y `modelToDto(...)` (ver `CategoryServiceImpl`). Verifica qué constructor de entidad está disponible — por ejemplo `Category` tiene únicamente `Category(String name_category)` y `id` es autogenerado.
- Repositorios: usan nombres personalizados (p. ej. `filterByNameCategories(String)`) — revisa `CategoryRepository` para la firma exacta antes de invocarla.
- Paquetes: la carpeta `Model` usa mayúscula en el nombre del directorio (`Model`), revisa importaciones si renuevas refactorings por sensibilidad a mayúsculas en algunos entornos.

Errores comunes a detectar
- Mapeos ambiguos al arrancar: si Spring lanza `Ambiguous mapping`, busca controladores con `@GetMapping("")` sin ruta base y añade `@RequestMapping("/categories")` o `@RequestMapping("/courses")`.
- Incompatibilidades de constructor: si el servicio intenta construir la entidad con `new Category(id, name)` pero la entidad solo tiene `Category(String)`, ajusta el conversor para usar el constructor disponible.
- Tipo de retorno discordante: algunos `save()` devuelven `String` pero `repo.save(...)` retorna entidad; un patrón coherente aquí es devolver la entidad guardada.

Cómo probar un endpoint (ej. findAll) con Thunder Client
1. Asegúrate de arrancar la app: `.\mvnw.cmd spring-boot:run` (desde `crudbasic`).
2. Si no has cambiado rutas, prueba `GET http://localhost:8080/` — pero recuerda: múltiple controladores pueden competir por `/`.
3. Si los controladores se corrigieron con base-paths, usa por ejemplo `GET http://localhost:8080/categories` o `GET http://localhost:8080/courses`.
4. Header recomendado: `Accept: application/json`.

Ejemplos concretos de búsqueda en el código
- Conversor DTO→Model: `src/main/java/com/sena/crudbasic/service/impl/CategoryServiceImpl.java` contiene `dtoToModel` y `modelToDto`.
- Entidad con id autogenerado: `src/main/java/com/sena/crudbasic/Model/Category.java` (anotaciones `@Id` y `@GeneratedValue`).
- Repositorio con filtro: `src/main/java/com/sena/crudbasic/repository/CategoryRepository.java` (busca `filterByNameCategories`).

Notas para el agente
- No hagas cambios globales sin ejecutar `mvnw.cmd clean package` o `spring-boot:run` para validar arranque.
- Cuando modifiques controladores, utiliza `@RequestMapping("/resource")` en la clase para evitar mapeos vacíos en `/`.
- Siempre inspecciona `src/main/resources/application.properties` para puerto y datasource antes de probar endpoints.

¿Algo incompleto o que quieras que especifique más? Pide que añada:
- Ejemplos de tests o comandos para debug (logs, stacktraces).
- Patch propuesto para armonizar rutas (`@RequestMapping`) en ambos controladores.
