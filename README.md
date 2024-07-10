<h1 align="center"> Literalura: Tu catálogo de libros personal </h1>
Literalura es una aplicación Java desarrollada con Spring Boot que te permite crear tu propia biblioteca digital. Conecta con la API Gutendex para buscar libros y guardarlos en una base de datos PostgreSQL.

Tecnologías:

- Java
- Spring Boot
- JPA
- Jackson
- PostgreSQL

Funcionalidades:

- Buscar libros por título en la API Gutendex.
- Guardar los libros encontrados en tu biblioteca personal.
- Listar los libros almacenados, incluyendo información como el autor, idioma y número de descargas.
- Clasificar los libros por diferentes criterios.
- ¡Y mucho más!

Objetivo:

Este proyecto fue desarrollado para poner en práctica los conocimientos adquiridos durante el curso de Spring Boot. Me permitió crear una aplicación funcional desde cero, enfrentando desafíos y aprendiendo nuevas técnicas.

Aquí muetro el menú que presenta para interactuar con la aplicación

![Screenshot del menú en el código](https://github.com/Gasca78/LiterAlura/blob/main/literalura/menu.png)

Y su respectiva protección al no ingresar un número válido

![Screenshot del menú en el código con valor ingresado no dentro de las opciones](https://github.com/Gasca78/LiterAlura/blob/main/literalura/opcion_menu_invalido.png)

Comenzando así con la opción 1 del menú

![Screenshot de la opción 1](https://github.com/Gasca78/LiterAlura/blob/main/literalura/opcion_1.png)

También puse protecciones para que el código siga trabajando aún cuando no encuentre el libro o quieran agregar un libro ya anteriormente guardado

![Screenshot de la opción 1 libro no encontrado](https://github.com/Gasca78/LiterAlura/blob/main/literalura/opcion_1_no_encontrado.png)

![Screenshot de la opción 1 libro repetido](https://github.com/Gasca78/LiterAlura/blob/main/literalura/opcion_1_repetido.png)

Después nos encontramos con la interacción de la opción 2, donde solo listamos la lista de los libros guardados

![Screenshot de la opción 2](https://github.com/Gasca78/LiterAlura/blob/main/literalura/opcion_2.png)

Luego pasamos al listado de los autores

![Screenshot de la opción 3](https://github.com/Gasca78/LiterAlura/blob/main/literalura/opcion_3.png)

Para la interacción de la opción 4 del menú, pedimos primero que ingresen el año para después mostrar la lista de autores vivos en ese año ingresado

![Screenshot de la opción 4](https://github.com/Gasca78/LiterAlura/blob/main/literalura/opcion_4.png)

Igualmente cuenta con una protección para cuando no se ingresan números:

![Screenshot de la opción 4 no ingresaste un número válido](https://github.com/Gasca78/LiterAlura/blob/main/literalura/opcion_4_error.png)

En la interacción con la opción 5 ponemos una lista de 4 opciónes de idiomas disponibles y los libros escritos en el idioma indicado

![Screenshot de la opción 5](https://github.com/Gasca78/LiterAlura/blob/main/literalura/opcion_5.png)

También cuenta con su protección para limitar que el idioma ingresado este dentro de las 4 opciones propuestas

![Screenshot de la opción 5 no se eligió una opción adecuada](https://github.com/Gasca78/LiterAlura/blob/main/literalura/opcion_5_no_es_opcion.png)

Pasando ahora a unas opciones donde no hay mucha interacción con el usuario más que mostrar la opción ingresada, comenzando con el top 10 de libros con más descargas

![Screenshot de la opción 6](https://github.com/Gasca78/LiterAlura/blob/main/literalura/opcion_6.png)

Teniendo también estadísticas sobre el promedio de descargas entre todos los libros y cuál es el libro con más descargas y cuál con menos

![Screenshot de la opción 7](https://github.com/Gasca78/LiterAlura/blob/main/literalura/opcion_7.png)

Por último nos topamos con la opción 8, la cual busca autores en específico de los libros que tengas guardados

![Screenshot de la opción 8](https://github.com/Gasca78/LiterAlura/blob/main/literalura/opcion_8.png)

También con sus respectivas protecciones, para cuando no se ingresa un nombre o el nombre buscado no se encuentra guardado en la base de datos

![Screenshot de la opción 8](https://github.com/Gasca78/LiterAlura/blob/main/literalura/opcion_8_no_nombre.png)

![Screenshot de la opción 8](https://github.com/Gasca78/LiterAlura/blob/main/literalura/opcion_8_nombre_no_en_repositorio.png)

Llegando así al final de la presentación de estre proyecto. ¡Gracias por leerme!

Agradecimientos:

Este proyecto no hubiera sido posible sin la ayuda de los profesores del curso de Spring Boot y la comunidad de Alura Latam. Gracias por su dedicación y por compartir sus conocimientos.
