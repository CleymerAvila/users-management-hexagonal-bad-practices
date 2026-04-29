# DOCUMENTACIÓN DE SOLUCION A VIOLACIONES DE REGLA 1 Y REGLA 2

## VIOLACIONES REGLAS 1 (Arquitectura Hexagonal)


- VIOLACIÓN REGLA 3

La regla 3 nos dice que no debemos personalizar los mensajes
de las validaciones dentro de los records, dejando los mensajes
por defectos con eliminando el atributo message= en los siguientes archivos

- application/service/dto/command/CreateUserCommand.java
- application/service/dto/command/DeleteUserCommand.java
- application/service/dto/command/LoginCommand.java
- application/service/dto/command/UpdateUserCommand.java
- application/service/dto/query/GetUserByIdQuery.java

## VIOLACIONES REGLA 2 (Clean Code)

### VIOLACIÓN REGLA 24 (Consistencia semántica)
-   Usar los mismos términos para los mismos conceptos.
-   Mantener coherencia en nombres y estructuras.

* En la clase UserAplicationMapper.java dentro de la carpeta application/service/mapper se encontraba una inconsistencia 
semantica a la hora de nombrar el concepto de correo declarando dos variables con el mismo concepto de manera difernte 
dentro de la misma clase, por lo que se renombró de correoElectronico a correo para mejor consistencia

* Haciendo lo propio dentro de la clase Main.class la cual usaba otro tipo de logger de una libreria diferente al 
contrario de lo usando en todo el proyecto, mostrando una inconsistencia semantica, por lo que se implemento el mismo 
uso del logger para todo el proyecto.

