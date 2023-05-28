# *Servicio de Gestión de Alquileres*
El Servicio de Gestión de Alquileres es una aplicación web que permite administrar y controlar el proceso de alquiler de propiedades. Proporciona funcionalidades para el registro de clientes, gestión de juegos, seguimiento de alquileres y más.

## *Características*
 * Registro de clientes: Permite agregar nuevos clientes al sistema con información personal y de contacto.
 * Gestión de juegos: Permite administrar el catálogo de juegos disponibles para alquilar, incluyendo detalles como título, descripción y disponibilidad.
 * Seguimiento de alquileres: Permite realizar el seguimiento de los alquileres realizados, incluyendo información de cliente, juego y fechas de inicio y fin del alquiler.
 * Integración con servicio de equilibrador de carga: El servicio se integra con un servicio de equilibrador de carga para garantizar la disponibilidad y escalabilidad.
 * Base de datos: Utiliza una base de datos MySQL para almacenar los datos de clientes, juegos y alquileres.
## *Tecnologías utilizadas*
 * Java 17
 * Spring Boot 3.0.2
 * MySQL 8
 * Maven
 * Docker
## Diseño Base Datos
![Diagrama Relacional](https://github.com/Magnico/GameRent/assets/61947333/d3dacc16-76ca-4a1c-a491-d761c82359bc)
## *Instalación y ejecución*
 1. Asegúrese de tener Docker y Docker Compose instalados en su sistema.
 2. Clone el repositorio del proyecto desde el repositorio proporcionado.
 3. Asegúrese de que el archivo "docker-compose.yaml" y el archivo "populate.sql" (proporcionado en el repositorio) se encuentren en el mismo directorio.
 4. Abra una terminal y navegue hasta el directorio donde se encuentran los archivos mencionados.
 5. Ejecute el siguiente comando: `docker-compose up`
 6. Esto iniciará los contenedores Docker para cada servicio y comenzará a ejecutar el servicio web.
 7. Una vez que los contenedores se hayan iniciado correctamente, podrás acceder al servicio web en http://localhost:8200 desde tu navegador o cualquier otra herramienta.
## *Contribución*
Si deseas contribuir a este proyecto, sigue los siguientes pasos:
 1. Haz un fork del repositorio.
 2. Crea una rama para realizar tus modificaciones.
 3. Realiza tus cambios y realiza commits descriptivos.
 4. Envía un pull request explicando los cambios realizados.