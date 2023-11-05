
# Todo-App con Java, Maven, Swing, JPA, MySQL y Docker.

Este proyecto fue realizado como presentación para la **Academia Java + Springboot**, dictada por **Softtek** en conjunto con la **Universidad Siglo-XII**. El objetivo del proyecto fue desarrollar una aplicación de gestión de tareas en **Java**, donde se realice una conexión a una base de datos en **MySQL** y para la gestión de la capa de persistencia, se decidió trabajó con **JPA (Java Persistence API)**. Luego, el desarrollo de la interfaz de usuario se realizó con **Swing**, una decisión basada en poner en práctica lo aprendido durante el cursado de la academia.

- [Funcionamiento de la Aplicación](#funcionamiento-de-la-aplicacion)
- [Uso de la Aplicación](#uso-de-la-aplicación)
    - [Dependencias](#dependencias)
    - [Instalación](#instalación)
- [Arquitectura de la Aplicación](#arquitectura-de-la-aplicación)
    - [Patrones de Diseño](#patrones-de-diseño)
        - [Estructura N-Tier](#estructura-n-tier)
        - [Inyección de Dependencias](#patrón-de-inyección-de-dependencias)
        - [DAO](#patrón-dao)
        - [DTO](#patrón-dto)

- # Funcionamiento de la aplicacion
![Funcionamiento de la aplicacion](https://github.com/nicomuros/actividad-m4-acsofttek/blob/main/capturas/Comprobacion%20MySQL.gif?raw=true)  
En la grabación superior se pueden observar las distintas etapas del CRUD, junto con el manejo de errores y el feedback de los mismos al usuario:
* **Create:** Para poder realizar la carga de datos con éxito a la base de datos, es necesario que el usuario ingrese de forma **obligatoria**, tanto el título como la descripción de la tarea (tampoco puede ser una cadena de espacios en blanco). En la grabación se puede observar como, en el momento en que el usuario intenta ingresar una tarea con formatos invalidos se muestra un mensaje de error.
* **Read**: Las operaciones de lectura a la base de datos se realizan de forma automática cuando el usuario inicia la aplicación y cada vez que realiza una operación con éxito.
* **Update**: El proceso de actaualización de una tarea requiere que el usuario proporcione el identificador, además de al menos una modificación en el titulo o la descripción de la misma. Como cuando se añade una tarea, es necesario que el nuevo título o descripción **no** estén vacíos.
* **Delete**: Para poder eliminar una tarea, el usuario debe primero seleccionarla en la tabla, para poder obtener su identificador. Cuando esta condición no se cumple, se muestra un mensaje de error al usuario.

# Uso de la aplicación
## Dependencias
Para poder ejecutar el proyecto es necesario tener instaladas y configuradas las siguientes dependencias:
* **[Maven 3.9+](https://maven.apache.org/download.cgi)**
* **[JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)**
* **[MySQL 8.0.33](https://downloads.mysql.com/archives/installer/)**
* **[Git](https://git-scm.com/downloads)**

Es importante tener configuradas las variables de entorno `JAVA_HOME` y `MAVEN_HOME` para ejecutar correctamente el empaquetado y la iniciación de la aplicación.

## Instalación

Siga estos pasos para instalar la aplicación:

1. Clonar el repositorio desde GitHub al sistema local. Puedes hacerlo utilizando Git y ejecutando el siguiente comando en tu terminal:

`git clone https://github.com/nicomuros/actividad-m4-acsofttek.git`

2. Navegar al directorio de la aplicación:

`cd actividad-m4-acsofttek`

3. Utilizar Maven para compilar y construir la aplicación. Para ello, ejecutar el siguiente comando:

`mvn clean package`

Esto compilará el proyecto y creará un archivo JAR ejecutable en la carpeta "target", y creará un directorio /lib con las librerias necesarias para poder ejecutar la aplicación.

4. Iniciar MySQL y crear una base de datos llamada `softtek`. Asegurarse que esté escuchando el puerto **3306**. Las credenciales que posee de forma nativa la aplicación son `username: muros` `password: password`. Si se prefiere trabajar con Docker, En el directorio del proyecto, se encuentra un archivo `docker-compose.yml` con el cual levantar un contenedor de una imagen de MySQL 8.0.33 con las configuraciones necesarias para ejecutar la aplicación.

5. Para levantar la base de datos usando el archivo docker-compose que se incluye en repositorio, asegurate de tener instalado Docker y Docker Compose en tu sistema. Si no los tienes, puedes instalarlos desde sus webs oficiales. Yo recomiendo instalar **[Docker Desktop](https://www.docker.com/products/docker-desktop/)**, que incluye Docker Engine, Docker Compose y la interfaz CLI.

6. En la terminal de comandos, dirigirse al directorio del proyecto donde se encuentra el archivo docker-compose.yml y ejecutar el comando (asegurarse de no tener otra aplicación escuchando el puerto 3306):

`docker-compose up -d`

Recuerde esperar unos momentos a que la construcción del contenedor termine, en caso contrario la aplicación no logrará conectarse a la base de datos.

7. Desplazarse al directorio /target.

`cd target`

8. Ejecuta la aplicación JAR con el siguiente comando, reemplazando "nombre-del-archivo.jar" con el nombre real del archivo JAR generado:

`java -jar nombre-del-archivo.jar`

La aplicación se ejecutará y podrás interactuar con ella a través de la interfáz gráfica.

# Arquitectura de la aplicación.

## Patrones de diseño

### Estructura N-Tier
La estructura N-Tier o el diseño en capas permite la separación de responsabilidades de cada componente de la aplicación, lo cual lleva a una arquitectura modular y escalable. En esta arquitectura, se dividen las funcionalidades de la aplicación en varias capas o  niveles claramente definidos, cada una con un propósito específico, lo cual es una buena dirección para poder implementar el principio de responsabilidad única.

1. **Capa de Presentación (`/vista`):** Esta capa corresponde a la interfaz de usuario y se encarga de la presentación visual de la aplicación. En el contexto de una aplicación CLI, esta capa sería responsable de crear y gestionar las ventanas, formularios y elementos de la interfaz de usuario. Se comunica con la capa de servicio para solicitar y mostrar datos.

2. **Capa de Servicio (`/servicio`):** Coordina la interacción entre la capa de presentación y la capa de acceso a datos. Además, se encarga de realizar validaciones y de preparar los datos antes de ser enviados a la capa de acceso a datos.

3. **Capa de Acceso a Datos (`/repositorio`):** La capa de acceso a datos incluye clases y componentes que se conectan a la base de datos, realizan consultas y operaciones CRUD (Crear, Leer, Actualizar, Eliminar).

4. **Capa de Modelo (`/modelo`):** Esta capa es responsable de representar los datos y la estructura de la aplicación. Se subdivide en tres paquetes principales: `/dto`, que contiene objetos de transferencia de datos; `/entidades`, que almacena las entidades que representan los elementos fundamentales del dominio de la aplicación; y `/mapper`, donde se gestionan las conversiones entre los objetos DTO y las entidades del modelo.

5. **Capa de Excepciones (`/excepciones`):** Aquí se encuentran las excepciones personalizadas de la aplicación. Heredan directamente de `RuntimeExcepcion`.

### Patrón de Inyección de Dependencias

El patrón de Inyección de Dependencias es una parte fundamental del diseño de la aplicación y ofrece una serie de ventajas clave en el enfoque de desarrollo:

* **Desacoplamiento**  
  El patrón de Inyección de Dependencias promueve el desacoplamiento entre los componentes de la aplicación. Esto significa que las clases no están fuertemente ligadas a sus dependencias, lo que facilita la sustitución o el cambio de componentes sin afectar otras partes del sistema. Esta flexibilidad es esencial para mantener y evolucionar la aplicación con confianza.

* **Pruebas Unitarias**  
  La inyección de dependencias simplifica el proceso de prueba unitaria. Se pueden proporcionar implementaciones de dependencias simuladas o en blanco durante las pruebas, lo que permite aislar y probar componentes individualmente.

* **Preparar la aplicación para la inversión de control**   
  La Inyección de Dependencias (IoD) prepara la aplicación para la implementación de Inversión de Control (IoC) proporcionada por Spring.

### Patrón DAO
La interfaz `TareaDao` define métodos que se encargan de la interacción con la base de datos, como agregar, seleccionar, verificar, modificar y eliminar tareas. Esto separa claramente la lógica de acceso a datos de la lógica de negocio relacionada con las tareas.

### Patrón DTO
Los DTOs simplifican la transferencia de datos entre la capa de presentación y la capa de servicio, reduciendo la sobrecarga de información y mejorando el rendimiento y la seguridad al transmitir solo los datos necesarios. Además, se emplea un **mapper** para convertir datos entre los DTOs y las entidades del modelo, garantizando una comunicación coherente y eficiente.
