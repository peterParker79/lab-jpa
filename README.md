# LAB JPA

En este lab vamos a generar un proyecto con spring Initializr y a crear una API REST con JPA.

## 0. Forkear el repositorio
- Haz un fork de este repositorio en tu cuenta de GitHub.
- Clona el repositorio en tu máquina local.

## 1. Crear un proyecto con Spring Initializr
- Ir a [Spring Initializr](https://start.spring.io/)
- Añade las tres dependencias de la siguiente lista:
  - Spring Web
  - Spring Data JPA
  - MySQL Driver
- Genera el proyecto y descomprime el zip en este proyecto.
- Abre el proyecto en IntelliJ IDEA.

## 2. Crear base de datos en MySQL
- Abre MySQL Workbench y crea una base de datos llamada `lab_jpa`. Botón derecho en `Schemas` y `Create Schema...`.
- Crea una tabla llamada `person` con los siguientes campos:
  - id (int, primary key, auto increment)
  - name (varchar(255))
  - age (int)

## 3. Configurar la conexión a la base de datos
- Abre el archivo `src/main/resources/application.properties` y añade la siguiente configuración:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/lab_jpa
spring.datasource.username=root
spring.datasource.password=admin
```
- Cambia el usuario y la contraseña según tu configuración de MySQL.
- Cambia el puerto si el tuyo no es el 3306.

Testea la conexión a la base de datos ejecutando el archivo principal dentro de la carpeta `java/com.example.demo/DemoApplication.java` (el nombre variará según lo que hayas puesto en initialzr). Si todo está bien, la terminal no debería parar y no debería dar errores de conexión. Si da errores, revisa la configuración de la base de datos y el archivo `application.properties`.

## 4. Crear la entidad `Person`
- Dentro del paquete principal `com.example.demo` crea un nuevo paquete llamado `models`.
- Dentro de `models` crea una clase llamada `Person` con los siguientes atributos:
    - id (Long, auto increment, primary key)
    - name (String, not null)
    - age (int, not null)
Recuerda que esto es una entidad, por tanto deberás usar las anotaciones `@Entity`, `@Table`, `@Id`, `@GeneratedValue` y `@Column` de JPA.
- Añade también el constructor vacío y el constructor con todos los atributos (el id no es necesario).
- Añade los getters y setters para todos los atributos.
- Añade el método `toString()` para poder ver los datos de la persona al imprimirla.

## 5. Crear el repositorio `PersonRepository`
- Dentro del paquete `com.example.demo` crea un nuevo paquete llamado `repositories`.
- Dentro de `repositories` crea una interfaz llamada `PersonRepository` que extienda de `JpaRepository<Person, Long>`.
- No es necesario añadir nada más de momento, ya que JpaRepository ya tiene todos los métodos necesarios para hacer operaciones CRUD (Create, Read, Update, Delete) sobre la entidad `Person`.


## 6. Testear el repositorio `PersonRepository`
- Dentro de la carpeta de test `src/test/java/com.example.demo` crea un nuevo paquete llamado `repositories` y una clase llamada `PersonRepositoryTest`.
- En esta clase vamos a testear el repositorio `PersonRepository` usando JUnit.
- Añade la anotación `@SpringBootTest` a la clase para que se inicie el contexto de Spring al ejecutar los tests.

### 6.1. Testear el método `save()`
- Crea un método llamado `testSave()` que haga lo siguiente:
    - Crea una instancia de `Person` con los datos que quieras (name, age).
    - Guarda la persona en el repositorio usando el método `save()`.
    - Busca la persona en el repositorio usando el método `findById()` y comprueba que no es nulo.
    - Imprime la persona por consola.
    - Comprueba que el id de la persona no es nulo con una aserción.
    - Comprueba que el nombre de la persona es "John Doe" con una aserción.
    - Comprueba que la edad de la persona es 30 con una aserción.
También puedes comprobar en mySQL que la persona se ha guardado correctamente en la tabla `person`. Ten en cuenta que no lo estamos borrando así que te va a crear una fila en la tabla cada vez que lo ejecutes.

### 6.2. Testear el método `findAll()`
- Crea un método llamado `testFindAll()` que haga lo siguiente:
    - Crea una lista de personas con los datos que quieras (name, age).
    - Guarda las personas en el repositorio usando el método `saveAll()`.
    - Busca todas las personas en el repositorio usando el método `findAll()` y comprueba que no es nulo.
    - Imprime la lista de personas por consola.
    - Comprueba que el nombre de la primera persona es "Ditto" (o el nombre que pusieras) con una aserción.
    - Comprueba que la edad de la primera persona es 30 con una aserción.

### 6.3. Testear el método `delete()`
- Crea un método llamado `testDelete()` que haga lo siguiente:
    - Crea una instancia de `Person` con los datos que quieras (name, age).
    - Guarda la persona en el repositorio usando el método `save()`.
    - Busca la persona en el repositorio usando el método `findById()` y comprueba que no es nulo.
    - Borra la persona en el repositorio usando el método `delete()`.
    - Busca la persona en el repositorio usando el método `findById()` y comprueba que es nulo.
    - Imprime la persona por consola.
    - Comprueba que el id de la persona es nulo con una aserción.

### 6.4. Testear el update
- Crea un método llamado `testUpdate()` que haga lo siguiente:
    - Crea una instancia de `Person` con los datos que quieras (name, age).
    - Guarda la persona en el repositorio usando el método `save()`.
    - Busca la persona en el repositorio usando el método `findById()` y comprueba que no es nulo.
    - Cambia el nombre y la edad de la persona.
    - Guarda la persona en el repositorio usando el método `save()`.
    - Busca la persona en el repositorio usando el método `findById()` y comprueba que no es nulo.
    - Imprime la persona por consola.
    - Comprueba que el id de la persona no es nulo con una aserción.
    - Comprueba que el nombre de la persona es "Nuevo nombre" (o el nombre que pusieras) con una aserción.
    - Comprueba que la edad de la persona es 30 (o lo que pusieras en la nueva edad) con una aserción.

Puedes utilizar workbench en todo momento para hacer la comprobación manual.


## BONUS
- Crea un nuevo paquete llamado `controllers` y dentro de este crea una clase llamada `PersonController`.

### 7. Crear el controlador `PersonController`
- Añade la anotación `@RestController` a la clase.
- Añade un endpoint para obtener una string con el mensaje "Hello World". Usa la anotación `@GetMapping("/api/hello")` para que sea un endpoint REST.
- Añade un método `getAll()` que devuelva todas las personas en formato JSON. Usa la anotación `@GetMapping("/api/persons")` para que sea un endpoint REST.
