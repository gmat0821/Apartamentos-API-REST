## API REST - APARTAMENTOS

## 1. Descripción de la API

Esta plataforma de servicios web basada en arquitectura REST fue diseñada para automatizar y optimizar la administración integral de bienes raíces dedicados al arrendamiento de espacios habitacionales. El sistema centraliza la operación de cuatro pilares fundamentales:

* **Gestión de Clientes (`/api/clientes`):** Control exhaustivo de perfiles de usuarios, clasificándolos entre arrendatarios y propietarios, con encriptación de credenciales mediante BCrypt.
* **Inventario Inmobiliario y Unidades (`/api/propiedades`, `/api/unidades`):** Administración de inmuebles principales (casas, cabañas, departamentos) y sus divisiones habitacionales interiores, incluyendo una galería de imágenes multimedia y control de disponibilidad por temporada.
* **Operación de Negocio (`/api/reservas`, `/api/pagos`):** Núcleo transaccional que previene solapamientos de fechas, calcula importes por estancia y realiza el seguimiento de estados de cuenta y liquidaciones.
* **Módulos de Interacción y Analítica (`/api/resenas`, `/api/notificaciones`, `/api/reportes`):** Canales de comunicación interna entre usuarios, sistema de valoración de estancias finalizadas y generación de reportes estratégicos personalizados de ocupación, ingresos y deudores.

---

## 2. Tecnologías Implementadas

* **Lenguaje:** Java  
* **Framework Principal:** Spring Boot (Configurado en el puerto `8000`)
* **Persistencia y ORM:** Spring Data JPA / Hibernate (Utilizando `@EntityGraph` para la optimización de consultas de carga perezosa)
* **Base de Datos:** MySQL
* **Seguridad de Credenciales:** Spring Security BCrypt

---

## 3. Pasos de Instalación y Ejecución Local

1. **Requisitos Previos:** Verifica tener instalado el JDK de Java, tu IDE de preferencia (IntelliJ, Eclipse o VS Code) y un servidor local MySQL en funcionamiento.
2. **Creación del Esquema:** Accede a tu consola de MySQL y genera una base de datos vacía ejecutando la instrucción:

   ```sql
   CREATE DATABASE apartamentos;

```

3. **Obtención del Código:** Clona este repositorio en tu espacio de trabajo local usando tu cliente Git.
4. **Importación:** Abre el directorio raíz del proyecto en tu IDE como un proyecto Maven para que descargue las dependencias necesarias.
5. **Configuración de Conexión:** Localiza el archivo de propiedades en la ruta `src/main/resources/application.properties` y ajusta tus credenciales locales de acceso a la base de datos, asegurándote de que el puerto esté definido en `8000`.
6. **Despliegue:** Ejecuta la clase principal `ApartamentosApplication.java`. El servidor Tomcat embebido se pondrá en marcha, exponiendo la API en el puerto `8000` y generando las tablas automáticamente mediante Hibernate.

---

## 4. Variables de Entorno (Configuración en `application.properties`)

```properties
spring.application.name=apartamentos
server.port=8000

spring.datasource.url=jdbc:mysql://localhost:3306/apartamentos?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD_AQUI

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

```

---

## 5. Listado de Endpoints

### Clientes (`/api/clientes`)

* `GET /api/clientes`: Lista la totalidad de clientes registrados.
* `GET /api/clientes/{id}`: Obtiene los datos detallados de un cliente por su ID.
* `POST /api/clientes`: Registra un nuevo cliente (Propietario o Inquilino).
* `PUT /api/clientes/{id}`: Actualiza la información de perfil de un cliente.
* `DELETE /api/clientes/{id}`: Da de baja a un cliente del sistema.

### Propiedades (`/api/propiedades`)

* `GET /api/propiedades`: Devuelve el catálogo general de inmuebles.
* `GET /api/propiedades/{id}`: Consulta una propiedad específica vinculando los datos de su propietario.
* `GET /api/propiedades/tipo/{tipo}`: Filtra inmuebles según su categoría o clasificación.
* `GET /api/propiedades/propietario/{id}`: Consulta todos los inmuebles asociados a un dueño específico.
* `POST /api/propiedades`: Ingresa un nuevo inmueble al sistema.
* `PUT /api/propiedades/{id}`: Modifica las características de una propiedad.
* `DELETE /api/propiedades/{id}`: Elimina un inmueble del registro.

### Unidades Rentables (`/api/unidades`)

* `GET /api/unidades`: Consulta todas las habitaciones o pisos independientes.
* `GET /api/unidades/{id}`: Obtiene una unidad por su identificador.
* `GET /api/unidades/propiedad/{idPropiedad}`: Lista las unidades que componen una propiedad.
* `POST /api/unidades`: Crea una nueva unidad habitacional.
* `PUT /api/unidades/{id}`: Actualiza los datos de la unidad.
* `DELETE /api/unidades/{id}`: Elimina una unidad rentable.

### Galería de Imágenes (`/api/propiedad-imagenes`)

* `GET /api/propiedad-imagenes`: Lista todas las imágenes almacenadas.
* `GET /api/propiedad-imagenes/propiedad/{idPropiedad}`: Filtra las fotografías de un inmueble.
* `POST /api/propiedad-imagenes`: Agrega el enlace de una nueva foto descriptiva.
* `PUT /api/propiedad-imagenes/{id}`: Modifica los datos de la imagen.
* `DELETE /api/propiedad-imagenes/{id}`: Elimina un registro de imagen.

### Disponibilidad y Tarifas Especiales (`/api/disponibilidades`)

* `GET /api/disponibilidades`: Consulta los bloqueos o tarifas de temporada.
* `GET /api/disponibilidades/unidad/{idUnidad}`: Filtra la disponibilidad de una unidad específica.
* `POST /api/disponibilidades`: Registra un rango de fechas con precio especial.
* `PUT /api/disponibilidades/{id}`: Actualiza un registro de disponibilidad.
* `DELETE /api/disponibilidades/{id}`: Elimina un bloqueo de fechas.

### Reservaciones (`/api/reservas`)

* `GET /api/reservas`: Muestra el historial global de reservas.
* `GET /api/reservas/{id}`: Consulta una reservación específica.
* `POST /api/reservas`: Registra una nueva estancia validando la no superposición de fechas.
* `PUT /api/reservas/{id}`: Modifica una reserva activa.
* `DELETE /api/reservas/{id}`: Cancela o elimina una reserva.

### Pagos y Estados de Cuenta (`/api/pagos`)

* `GET /api/pagos`: Lista todos los movimientos y transacciones.
* `GET /api/pagos/reserva/{idReserva}`: Consulta los pagos asociados a una reserva.
* `GET /api/pagos/{id}`: Obtiene un detalle de pago por su ID.
* `POST /api/pagos`: Registra una nueva orden o abono de pago.
* `PUT /api/pagos/{id}`: Actualiza los datos de un pago.
* `POST /api/pagos/{id}/confirmar`: Cambia el estatus del pago a `PAGADO`.
* `POST /api/pagos/{id}/reembolsar`: Aplica un reembolso sobre un pago confirmado.
* `DELETE /api/pagos/{id}`: Elimina un registro de pago pendiente.

### Reseñas y Calificaciones (`/api/resenas`)

* `GET /api/resenas`: Consulta la totalidad de opiniones emitidas.
* `GET /api/resenas/{id}`: Obtiene una reseña específica.
* `GET /api/resenas/reserva/{idReserva}`: Filtra la calificación correspondiente a una estancia.
* `POST /api/resenas`: Publica una reseña tras finalizar la reserva.
* `PUT /api/resenas/{id}`: Actualiza un comentario o calificación.
* `DELETE /api/resenas/{id}`: Elimina una reseña.

### Notificaciones y Mensajería (`/api/notificaciones`)

* `GET /api/notificaciones`: Lista general de avisos del sistema.
* `GET /api/notificaciones/{id}`: Consulta un mensaje específico.
* `GET /api/notificaciones/destinatario/{idCliente}`: Filtra los mensajes dirigidos a un usuario.
* `POST /api/notificaciones`: Envía una nueva notificación interna.
* `PUT /api/notificaciones/{id}`: Actualiza el estado de lectura del mensaje.
* `DELETE /api/notificaciones/{id}`: Elimina un registro de notificación.

### Reportes Estratégicos (`/api/reportes`)

* `GET /api/reportes/ocupados`: Genera la métrica de reservas activas actuales.
* `GET /api/reportes/ingresos`: Calcula la sumatoria total de capital recaudado por pagos confirmados.
* `GET /api/reportes/deudores`: Extrae el listado de clientes con pagos vencidos o pendientes expirados.

---

## 6. Ejemplos de Peticiones y Respuestas (Payloads en Postman)

### A. Registro de un Propietario (POST `http://localhost:8000/api/clientes`)

**Petición (JSON):**

```json
{
  "nombres": "Roberto",
  "apellidos": "Velasco",
  "email": "roberto.v@inversiones.com",
  "password": "clave_segura_1",
  "tipo": "PROPIETARIO"
}

```

**Respuesta Exitosa (JSON):**

```json
{
  "id": 1,
  "tipo": "PROPIETARIO",
  "nombres": "Roberto",
  "apellidos": "Velasco",
  "email": "roberto.v@inversiones.com",
  "telefono": null,
  "fechaNacimiento": null,
  "ine": null,
  "direccion": null,
  "fechaRegistro": "2026-10-11T01:30:00",
  "estatus": "ACTIVO"
}

```

### B. Registro de una Propiedad (POST `http://localhost:8000/api/propiedades`)

**Petición (JSON):**

```json
{
  "propietario": { "id": 1 },
  "tipo": "Cabaña",
  "titulo": "Cabaña de Lujo en el Bosque",
  "descripcion": "Hermosa cabaña rodeada de naturaleza con chimenea.",
  "direccion": "Camino al Nevado s/n",
  "ciudad": "Toluca",
  "estadoFederativo": "Estado de México",
  "codigoPostal": "50200",
  "pais": "Mexico",
  "latitud": 19.1122,
  "longitud": -99.7543,
  "precioNoche": 3500.00,
  "capacidad": 4,
  "numHabitaciones": 2,
  "numBanos": 2,
  "metrosCuadrados": 150
}

```

### C. Autorización de una Reserva (POST `http://localhost:8000/api/reservas`)

**Petición (JSON):**

```json
{
  "cliente": { "id": 2 },
  "unidad": { "idUnidad": 1 },
  "fechaEntrada": "2027-08-10T14:00:00",
  "fechaSalida": "2027-08-14T11:00:00",
  "importeTotal": 14000.00,
  "comentario": "Llevaremos a nuestro perro."
}

```

**Respuesta Exitosa (JSON):**

```json
{
  "idReservacion": 1,
  "unidad": {
    "idUnidad": 1,
    "nombre": "Cabaña Completa",
    "tipo": "Casa completa",
    "capacidad": 4,
    "precioNoche": 3500.00,
    "estado": "DISPONIBLE"
  },
  "cliente": {
    "id": 2,
    "tipo": "CLIENTE",
    "nombres": "Daniela",
    "apellidos": "Ortiz",
    "email": "dani.ortiz@correo.com",
    "estatus": "ACTIVO"
  },
  "fechaEntrada": "2027-08-10T14:00:00",
  "fechaSalida": "2027-08-14T11:00:00",
  "fechaCheckin": null,
  "fechaCheckout": null,
  "importeTotal": 14000.00,
  "estado": "PENDIENTE",
  "comentario": "Llevaremos a nuestro perro."
}

```
