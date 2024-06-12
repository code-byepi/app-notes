# Proyecto NoteTime

Este es un proyecto de gestión de notas que permite a los usuarios registrar cuenta, iniciar sesión, crear, consultar, archivar y filtrar notas

## Funcionalidades

### 1. Registro de Usuarios

Los usuarios pueden registrarse en la plataforma proporcionando su correo electrónico y contraseña.

### 2. Inicio de Sesión

Los usuarios pueden iniciar sesión en la plataforma con su correo electrónico y contraseña.

![Inicio de Sesión](/img/login.png)


### 3. Listado de Notas

Los usuarios pueden ver todas sus notas en una lista y realizar acciones como editar, archivar y eliminar.

![Listado de Notas](/img/listado.png)
![Listado de Notas](/img/listado-light.png)


### 4. Creación de Notas

Los usuarios pueden crear nuevas notas con un título, contenido y categoría opcional.

![Creación de Notas](/img/crear-notas.png)

### 5. Archivar Notas

Los usuarios pueden archivar notas para ocultarlas de la lista principal y verlas posteriormente en una sección separada.

![Archivar Notas](/img/archivadas.png)

### 6. Filtrar por Categorias

Los usuarios pueden filtrar notas por categoria. 

![Filtrar](/img/filtrado.png)

## Tecnologías Implementadas

- Angular: v17.3
- Bootstrap: v5.3.3
- Java
- Spring Boot

El backend de NoteTime está desarrollado con Spring Boot, un framework de Java para crear aplicaciones Java rápidas y eficientes. Utiliza Spring Security para la autenticación y autorización.

## Instalación - Frontend

1. Clona este repositorio.
2. Instala las dependencias de Angular con `npm install`.
3. Ejecuta la aplicación Angular con `ng serve`.
4. Abre `http://localhost:4200` en tu navegador.


## Instalación - Backend

1. Configurar la base de datos desde 'application.properties'
2. Compilar el proyecto y ejecutar el JAR

Java JDK: Asegúrate de tener Java JDK instalado en tu sistema.

## Contribuir

¡Todas las contribuciones son bienvenidas! Si deseas contribuir, sigue estos pasos:

1. Haz un fork del proyecto.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Haz commit de tus cambios (`git commit -am 'Agrega una nueva funcionalidad'`).
4. Haz push de tu rama (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.


