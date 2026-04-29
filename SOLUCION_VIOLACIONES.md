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