# Sistema de Clinica Veterinaria

**Desarrollado por:
**Versión:** 1.0.0  
**Tecnologías:**
- Java 17
- Spring Boot 3.5.4
- Spring Security con JWT
- Spring Data JPA + MySQL
- Lombok
- Apache Maven
- Arquitectura Limpia (paquetes `application`, `domain`, `web`, `config`)

---

##  Descripción
El **Sistema de Veterinaria ClinicAnimal** 
permite la gestion y control de cita,
brindando la opcion de agendar una Cita, Actualizarla, y crear una receta sobre la misma,
adicionalmente genera reportes que pueden ser usados para analizar la forma en la que el negocio se desenvuelve, 
tambien posee multiples estados que ayudan a la hora de manejar las citas.
---

##  Casos de Uso del Negocio (CUN)

| CUN     | Descripción                                                                                   | Estado |
|---------|-----------------------------------------------------------------------------------------------|--------|
| **CUN001** | Registrar cliente,mascota (Recepcionista/Admin) - Valicacion y verificación de datos.                    |  Completado |
| **CUN002** | Registrar cita (Recepcionista/Admin) - Crear cita seleccionando area,asignando personal el estado inical  varia segun la coordinacion previa, `REGISTRADO` y `EN_COLA`. |  Completado |
| **CUN003** | Check In de cita (Recepcionista) - Validacita y cambia estado de `PROGRAMADO` a `EN_COLA`.             |  Completado |
| **CUN004** | Progreso de órdenes - Trabajador marca como `TERMINADO`, Recepcionista/Admin entrega (`/deliver`) registrando receptor. |  Completado |
| **CUN005** | Historial del cliente - Consulta con filtros de fecha/estado y paginación.                     |  Completado |
| **CUN006** | Gestión de tipos de servicio (Admin) - CRUD, habilitar/deshabilitar, listar habilitados.       |  Completado |
| **CUN007** | Gestión de usuarios (Admin) - CRUD, detalle, habilitar/deshabilitar (soft delete), roles.      |  Completado |
| **CUN008** | Asignación manual (Admin) - Incluido en CUN003 (`/assign`).                                     |  Completado |
| **CUN009** | Reportes (Admin) - Ingresos por período, productividad por trabajador, órdenes por estado.     |  Pendiente |

---

##  Reglas de Negocio Implementadas

1. **Roles y permisos**
    - `ADMIN`: Acceso completo a todas las operaciones (CRUD de usuarios, servicios, clientes, citas y reportes).
    - `RECEPCIONISTA`: Registrar clientes y mascotas, programar citas, manejar estados.
    - `VETERINARIO`: Generar Receta.

2. **Estados de una Cita**   PROGRAMADA,EN_COLA,EN_PROGRESO,TERMINADA,CANCELADO
    - `PROGRAMADA`: Cita creada, agendada para una fecha futura.
    - `EN_COLA`: La cita ya esta cerca de comenzar.
    - `EN_PROGRESO`: El servicio esta en proceso.
    - `TERMINADA`: Se culmino la atencion.
    - `CANCELADO`: La cita fue cancelada por razones externas.

3. **Reglas de creación y actualización**

    - La cita debe usar el codigo de una mascota existente, y al añadir sus servicios, estos deben tener id's existentes.
    - El personal debtener 18 años o mas para crearse.
    - El numero del personal debe tener exactamente 9 digitos, y su DNI, 8 digitos, ademas, el DNI no puede ser repetido.
    - El gmail de un nuevo personal no puede estar ya registrado, adicionalmente debe terminar en "@gmail.com".
    - El rol y area elegido de un personal debe existir.
    - Al crear un rol o una area, el nombre no puede estar ya registrado.
    - Cuando se crea un personal, su estado automaticamente pasa a `DISPONIBLE`.
    - No se puede eliminar físicamente un personal, (se le cambia el estado a `DESPEDIDO`).
    - Solo puede haber una receta por cita.
    - El servicio tiene que ser creado con un codigo de area existente
    - La asistencia solo se puede registrar una vez al dia por Personal, y el personal debe ser existente.
    - El horario se debe registrar a un Rol existente
    - La hora de entrada debe ser antes de las 8:30AM , y la hora de salida no puede ser despues de la 6:00PM
   
      --AUN FALTA--
  - Tipos de servicio también se pueden deshabilitar en lugar de eliminar.
  - Los usuarios creados por Admin reciben un rol explícito (mínimo un rol por usuario).
  - Validaciones de existencia de cliente, servicio y usuario asignado antes de crear o modificar órdenes.
  - Recepción de órdenes solo con estado válido (no se puede asignar si ya está `TERMINADO` o `ENTREGADO`).

4. **Filtros y búsquedas**
    - Busqueda de Cita por el id de mascota y el estado.
    - Busqueda de Personal por Id de rol e Id de Area,
    - Búsqueda de receta por cantidad de medicamentos.

5. **Seguridad**
    - Autenticación vía JWT.
    - Control de permisos centralizado en `SecurityConfig` (sin anotaciones en métodos por ahora).
    - Filtros personalizados para extracción y validación del token.

---

##  Estado del Proyecto

### Completado 
- Implementación de todos los CUN del 001 al 008.
- Control de roles y permisos centralizado.
- Manejo de soft delete en Personal.
- Validaciones y reglas de negocio en servicios.
- Migraciones con Flyway.
- Arquitectura limpia con DTOs, Mappers y separación de capas.

### Pendiente 
- **CUN009**: Módulo de reportes administrativos.
- Documentación API con Swagger/OpenAPI.
- Manejo global de errores (`@ControllerAdvice`).
- Tests unitarios y de integración.
- Auditoría (`createdAt`, `updatedAt`) extendida a más entidades.
