# Hackathon #1: Sparky AI System

## Descripción General

Sparky Consulting, una empresa líder en soluciones de software, se enfrenta a un desafío creciente. Sus clientes
corporativos están implementando soluciones de inteligencia artificial utilizando diversos proveedores (OpenAI, Claude,
Meta, etc.), lo que ha generado problemas de gestión, sincronización y control de costos.

Para resolver esto, necesitan desarrollar **Sparky AI System**, un hub centralizado que permita a sus clientes
empresariales gestionar el acceso y consumo de diferentes modelos de IA a través de una única plataforma, aprovechando
la integración con GitHub Models.

## Duración y Formato

- **Tiempo**: 2 horas.
- **Equipos**: Grupos de 4 o 5 estudiantes.
- **Recursos**: Uso de IA permitido, documentación y material de Internet.

## Contexto del Negocio

Sparky AI System funcionará como una plataforma intermediaria que:

1. Registra empresas clientes con sus respectivos administradores.
2. Permite a los administradores de empresas definir qué modelos pueden usar y con qué límites.
3. Gestiona usuarios finales con sus propios límites específicos.
4. Registra y monitorea todas las solicitudes a los modelos de IA.
5. Centraliza la facturación y el control de costos.

## Requerimientos Técnicos

### Tecnologías Obligatorias

- Java 17+
- Spring Boot 3.x
- Spring Security con JWT
- Spring Data JPA
- PostgreSQL
- GitHub Models SDK (Java)
- JUnit y Mockito para testing

### Entidades y Relaciones

El sistema debe modelar las siguientes relaciones sin comprometer las mejores prácticas de POO:

1. **Sparky** mantiene relaciones con múltiples **Empresas**.
2. Cada **Empresa** tiene:
    - Un **Administrador** principal.
    - Múltiples **Usuarios**.
    - Varias **Restricciones** a nivel de empresa.
3. Cada **Usuario** tiene:
    - Múltiples **Límites** específicos basados en las restricciones de la empresa.
    - Un historial de **Solicitudes** a modelos de IA.
4. Cada **Solicitud** registra información sobre la consulta, respuesta y consumo de recursos.

Las entidades deben considerar al menos los siguientes aspectos:

- Información de empresa (nombre, RUC, fecha de afiliación, estado activo, etc.).
- Restricciones por modelo (tipo de modelo, límites de uso, etc.).
- Límites de usuario (por modelo, ventana de tiempo, límites específicos).
- Registro detallado de solicitudes (consulta, respuesta, tokens consumidos, etc.).

## Funcionalidades Requeridas

### 1. Gestión de Empresas y Administradores

Endpoints para que el Super Admin de Sparky pueda:

| Método | Endpoint                                | Descripción                                                                      |
|--------|-----------------------------------------|----------------------------------------------------------------------------------|
| POST   | `/api/admin/companies`                  | Crear nueva empresa cliente con su administrador                                 |
| GET    | `/api/admin/companies`                  | Listar todas las empresas registradas                                            |
| GET    | `/api/admin/companies/{id}`             | Obtener información detallada de una empresa                                     |
| PUT    | `/api/admin/companies/{id}`             | Actualizar información de empresa                                                |
| PATCH  | `/api/admin/companies/{id}/status`      | Activar/desactivar empresa                                                       |
| GET    | `/api/admin/companies/{id}/consumption` | Obtener reporte de consumo de la empresa (créditos totales, gastados por modelo) |

### 2. Gestión de Restricciones de Empresa

Endpoints para administradores de empresas:

| Método | Endpoint                         | Descripción                                       |
|--------|----------------------------------|---------------------------------------------------|
| POST   | `/api/company/restrictions`      | Crear nueva restricción de modelo para la empresa |
| GET    | `/api/company/restrictions`      | Listar todas las restricciones de la empresa      |
| PUT    | `/api/company/restrictions/{id}` | Actualizar una restricción existente              |
| DELETE | `/api/company/restrictions/{id}` | Eliminar una restricción                          |

### 3. Gestión de Usuarios y Límites

Endpoints para administradores de empresas:

| Método | Endpoint                              | Descripción                                 |
|--------|---------------------------------------|---------------------------------------------|
| POST   | `/api/company/users`                  | Crear nuevo usuario para la empresa         |
| GET    | `/api/company/users`                  | Listar todos los usuarios de la empresa     |
| GET    | `/api/company/users/{id}`             | Obtener información detallada de un usuario |
| PUT    | `/api/company/users/{id}`             | Actualizar información de usuario           |
| POST   | `/api/company/users/{id}/limits`      | Asignar límite específico a un usuario      |
| GET    | `/api/company/users/{id}/consumption` | Obtener reporte de consumo del usuario      |

### 4. Integración con Modelos de IA

Endpoints para usuarios finales:

| Método | Endpoint             | Descripción                                               |
|--------|----------------------|-----------------------------------------------------------|
| POST   | `/api/ai/chat`       | Realizar una consulta a un modelo de chat                 |
| POST   | `/api/ai/completion` | Realizar una solicitud de completado de texto             |
| POST   | `/api/ai/multimodal` | Realizar una consulta a un modelo multimodal (con imagen) |
| GET    | `/api/ai/models`     | Obtener lista de modelos disponibles para el usuario      |
| GET    | `/api/ai/history`    | Obtener historial de solicitudes del usuario              |

## Roles y Seguridad

Implementar JWT para autenticación y los siguientes roles con sus respectivos permisos:

1. **ROLE_SPARKY_ADMIN**: Acceso completo a todas las empresas y reportes globales.
2. **ROLE_COMPANY_ADMIN**: Acceso a la gestión de su empresa, restricciones, usuarios y reportes de su empresa.
3. **ROLE_USER**: Acceso a los endpoints de IA según sus límites asignados.

## Integración con GitHub Models

Utilizar el SDK de GitHub Models para Java. El sistema debe soportar al menos:

- Un modelo multimodal
- Un modelo de Meta
- Un modelo de DeepSpeak
- Un modelo de OpenAI
- Enlace: https://github.com/marketplace/models

## Gestión de Límites y Rate Limiting

- Implementar limites como "rolling window" (se reinician periódicamente).
- Soportar diferentes tipos de límites:
    - Número de solicitudes por ventana de tiempo.
    - Tokens consumidos por ventana de tiempo.
- Rechazar solicitudes que excedan límites e informar de manera apropiada.

## Registro de Solicitudes

Registrar para cada solicitud (independientemente de su éxito):

- Texto de la consulta
- Respuesta obtenida (o error)
- Tokens consumidos
- Fecha y hora
- Usuario y empresa
- Modelo utilizado

Para solicitudes multimodales, no guardar los archivos en la base de datos, solo sus nombres y la consulta textual.

## Testing

Implementar pruebas unitarias con JUnit y Mockito que cubran al menos:

- Controllers (pruebas de endpoints).
- Services (lógica de negocio).
- Repositorios (operaciones CRUD básicas).

## Entregables

1. Código fuente completo en un **repositorio público** de GitHub.
2. Colección de Postman para probar los endpoints (esta tiene que estar en formato json en el root del repositorio).

## Observaciones Adicionales

- El foco debe estar en la implementación de los endpoints, la lógica de negocio y la estructura de clases.
- Utilizar DTOs para transferir datos entre capas.
- Implementar manejo centralizado de excepciones.
- Validar todas las entradas del usuario.

¡Buena suerte y que gane el mejor equipo!

Con mucho cariño desde California,

**Gabriel Romero**  
❤️