# Proyecto Backend para Sistema de Rutas y Choferes

Este proyecto es un backend desarrollado en Java utilizando Spring Boot y Maven, para gestionar ciudades, choferes y rutas en una aplicación de transporte.

## Características

- APIs REST para gestión de ciudades, choferes y rutas.
- Persistencia en base de datos relacional, referenciado mediante claves foráneas.
- Utiliza Maven para gestionar dependencias y construcción.
- Incluye configuración básica de CORS para desarrollo local.

## Requisitos

- Java 17 o superior
- Maven 3.8+ instalado
- Base de datos relacional (PostgreSQL)
  - Asegúrate de tener la base instalada y configurada.
  - Actualiza las propiedades de conexión en `application.properties`.


## Instalación

Clona este repositorio:

git clone https://github.com/FidelFragoza/rutas_back.git
cd rutas_back

Configura tu base de datos y modifica `src/main/resources/application.properties` según tu entorno.

Construye el proyecto y descarga dependencias:

mvn clean install

Ejecuta la aplicación:

mvn spring-boot:run

La API estará disponible en `http://localhost:8080`.

## Uso

- Puedes realizar peticiones GET, POST, PUT, DELETE usando cualquier cliente HTTP (Postman, curl, frontend).
- Ejemplo de endpoint: `GET /api/ciudades` para listar ciudades.

## Estructura del Proyecto

- `src/main/java` - Código fuente Java
- `src/main/resources` - Configuración y recursos
- `pom.xml` - Gestión de dependencias y plugin Maven

## Notas

- No subas archivos generados (target, logs, dependencias) en este repositorio.
- Para despliegue en producción, considera usar Docker, un servidor Java o servicios en la nube.

## Contribuciones

Se aceptan mejoras, reportes de bugs y pull requests.

