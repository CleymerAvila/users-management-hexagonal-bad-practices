# DOCUMENTACIÓN DE SOLUCION A VIOLACIONES DE REGLA 1 Y REGLA 2

## VIOLACIONES REGLAS 1 (Arquitectura Hexagonal)


### VIOLACIÓN REGLA 3

- La regla 3 nos dice que no debemos personalizar los mensajes
de las validaciones dentro de los records, dejando los mensajes
por defectos con eliminando el atributo message= en los siguientes archivos: CreateUserCommand, DeleteUserCommand, 
LoginCommand, UpdateUserCommand, GetUserByIdQuery

- Se eliminó la constraints y validación @Valid en la implementación de GetUserByIdService

- Se evitó la redundancia eliminando la anotación @Builder en los recors, CreateUserCommand.java y GetUserByIdQuery.java


### VIOLACIÓN REGLA 4

- La regla 4 nos dice que debemos nombrar variables sin abreviaturas y deben ser claros,
evitar el operador == null al hacer comparaciones y usar `Objects.isNull()` o `Objects.isEquals()` en su lugar, realizar
imports especificos y evitar la wildcard (*), definir como statics los metodos sin estados y definir las clases
utilitaris como @UtilityClass para evitar su instancia.

- Se remplazó el operador == null con el Objects.isNull() en los archivos AppProperties.java, EmailDestinationModel.java,
UserId.java, UserName.java, UserPassword.java

- Se renombró variables abreviadas poco claras en los archivos AppProperties.java, UpdateUserHandler.java, 
UserController.java, UserManagementCli.java

- Se establecio metodos sin estados como metodos estaticos en los archivos DatabaseConnectionFactory.java,
EmailNotificationService.java y UserPersistentMapper.java. Y tambien se cambio su implementación y llamada estatica
a esos metodos en los archivos DatabaseConnectionFactoryTest.java, DependencyContainer.java, UserPersistenceMapperTest,
UserRepositoryMySQL.java.

- Se marcó como con la anotación @UtilityClass para evitar su instanciacion en la clases ValidatorProvider.java, 
DatabaseConnectionFactory.java

### VIOLACIÓN REGLA 5

- ningun metodo debe devolver null

- se cambio la implementación de los metodos dentro de GetAllUsersService.java y UserResponsePrinter para retornar
listas vacias en lugar de null

pasando de esto

``` Java
if (users.isEmpty()) {
      return null;
    }
    return users;
```
a esto
``` Java
if (users.isEmpty()) {
      return Collections.emptyList();
    }
    return users;
```


### VIOLACIÓN REGLA 6

- La regla 6 de buenas prácticas java y arquitectura hexagonal nos dice que el bloque try-catch debe ser usado si hay
recuperación de lo contrario se dirija al manejador de excepciones global. No se debe loggear PII ni se debe hacer loggin
en la capa dominio

- Se eliminó el email del usuario (PII) dentro de los logs de los archivos DeleUserService.java, LogginHandler, y se
eliminó el logger del value object userEmail dentro de la capa dominio

## VIOLACIONES REGLA 2 (Clean Code)


### VIOLACIÓN REGLA 1 (Funciones cortas y responsabilidad única)

- Se separó el metodo `execute` de la clase CreateUserService en 3 metodos diferentes con responsabilidades únicas,
`createUser()` `validateConstrainsts()` y `createUserFromCommand`


- Se dividio el metodo `execute` de la clase LoginService para mayor cohesión y desacople de codigo y responsabilidades
unicas: `getAndValidateUser()`, `validateCommand()`, `searchUserFromEmail()`, `validateUserStatus()` y `verifyUserPassword()` 

- Se dividió el método main de la clase principal Main.class y salida de la aplicación para separar las responsabilidades
unicas dentro del metodo y solo ejecutar un solo flujo dentro del main, separando las ejecuciones en los siguientes 
metodos `builContainer()`, `buildConsole()`, `buildCli()` y `run()`


### VIOLACIÓN REGLA 24 (Consistencia semántica)
-   Usar los mismos términos para los mismos conceptos.
-   Mantener coherencia en nombres y estructuras.

* En la clase UserAplicationMapper.java dentro de la carpeta application/service/mapper se encontraba una inconsistencia 
semantica a la hora de nombrar el concepto de correo declarando dos variables con el mismo concepto de manera difernte 
dentro de la misma clase, por lo que se renombró de correoElectronico a correo para mejor consistencia

* Haciendo lo propio dentro de la clase Main.class la cual usaba otro tipo de logger de una libreria diferente al 
contrario de lo usando en todo el proyecto, mostrando una inconsistencia semantica, por lo que se implemento el mismo 
uso del logger para todo el proyecto.

