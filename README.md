# Challenge LiterAlura - Alura Latam G8<br>Especialización Back-End

## Descripción
Reto que se desarrolla como parte de la fase Java y Spring Framework G8 - ONE.

>En este desafío, Construirás tu propio catálogo de libros: el **LiterAlura**.<br>Aprenderás a realizar solicitudes a una API de libros, a manipular datos JSON, guardarlos en una base de datos y, finalmente, a filtrar y mostrar los libros y autores de interés.

Tomado de la **[plantilla del desafío](https://trello.com/b/WDyMPDMb/literalura-challenge-java)**

## Estado del desafío
:white_check_mark: **2025-06-09**: *Crear el repositorio del proyecto en GitHub.*

:white_check_mark: **2025-06-09**: *Configurando el entorno Java y Spring. Configura application.properties con variables de entorno. Creación de la base de datos **literalura**.*

:white_check_mark: **2025-06-10**: *Conociendo la API para traer datos. Revisión de los parámetros de consumo y pruebas en la herramienta Postman.*

:white_check_mark: **2025-06-11**: *Construyendo una solicitud de API: uso de las clases HttpClient, HttpRequest y HttpResponse, para acceder a los resultados de la API.*

:white_check_mark: **2025-06-12**: *Analizando la respuesta en formato JSON. Se hace uso de clase ObjectMapper.*

:white_check_mark: **2025-06-13**: *Conversión de datos. Se preparan las clases que recibirán los datos al recibir los atributos del body después de  la consulta a la API.*

:white_check_mark: **2025-06-14**: *Interactuando con el usuario. Se exhibe un menú de opciones con las que el usuario puede elegir la consulta de libros, autores, libors por idiona y autores por fecha.*

:white_check_mark: **2025-06-15**: *Consultando libros. Teniendo en cuenta la documentación de la API en gutendex.com se recibe en un json la estructura retornada por la petición, teniendo en cuenta que en la clave denominada 'Results' es un arreglo que contiene los libros que coinciden con la búsqueda.".*

:white_check_mark: **2025-06-16**: *Consultando autores. El elementos clave-valor 'Results' es un arreglo de objetos que también contienen una clave denominada 'authors' que contiene el conjunto de autores del libro".*

:white_check_mark: **2025-06-17**: *Persistencia de datos. Se almacena la información de libro, teniendo en cuenta almacenar previamente la información del autor del cual será su clave principal, la clave foranea para el libro que se almacena en la Base de datos".*

:white_check_mark: **2025-06-18**: *Listando libros por idiomas. Haciendo unso de una native query se obtiene el listado de idiomas de los libros almacenados en la base de datos, son luego mostrados al usuarios para que elija y por consiguiente se consulta con otra native query y se muestra el conjunto de libros que coinciden con el idioma y el total de libros obtenidos".*

:white_check_mark: **2025-06-19**: *Listando autores vivos por año. Recibiendo un año desde consola, se realiza una native query para obtener el listado de autores que vivieron alrededor del año consultado".*

:cherries: **2025-06-20**: *Opciones extra: Se generan estadísticas haciendo uso de la clase 'DoubleSummaryStatistics' y teniendo en cuenta el número de descargas*

:cherries: **2025-06-21**: *Opciones extra: Se agrega la opción del Top 10 de libros más decargados*

## Funcionalidades
- **Consumo de la API**: .

- **Análisis de la respuesta JSON**: .

- **Inserción y consulta en la base de datos**: .

- **Exibición de resultados a los usuarios**: .

## Vista previa
<img src="https://live.staticflickr.com/65535/54614798843_ba9d318fa3_z.jpg" width="400"><br>
<img src="https://live.staticflickr.com/65535/54614798853_ca04a854a0_z.jpg" width="400"><br>
<img src="https://live.staticflickr.com/65535/54614798848_ab8cf8324e_z.jpg" width="400"><br>
<img src="https://live.staticflickr.com/65535/54614780239_b86368349f_z.jpg" width="400"><br>
<img src="https://live.staticflickr.com/65535/54614798868_72ca3c37f2_z.jpg" width="400"><br>
<img src="https://live.staticflickr.com/65535/54613702157_d9650e58ab_z.jpg" width="400"><br>
<img src="https://live.staticflickr.com/65535/54614798858_59f9440bfd_z.jpg" width="400"><br>

## Acceso al proyecto
Puedes descargarlo **[AQUI](https://github.com/oabm77/challenge-literalura/archive/refs/heads/master.zip)**

## Tecnologías utilizadas
- Lenguaje de programación orientado a objetos: [Java](https://www.java.com)
- Framework de código abierto para el desarrollo de aplicaciones Java: [Spring](https://spring.io)
- Herramienta para probar API REST: [Postman](https://www.postman.com)
- Sistema de gestión de bases de datos relacional de código abierto: [Postgresql](https://www.postgresql.org)
- API para traer datos referentes a obras de la literatura universal [Gutendex](https://gutendex.com/).

## Autores
| [<img src="https://www.aluracursos.com/assets/img/home/alura-logo.1730889068.svg" width=115 height=115 style="background-color:black;"><br><sub>Equipo Alura Latam</sub>](https://www.aluracursos.com) |  [<img src="https://live.staticflickr.com/65535/54296423135_023657de24_q_d.jpg" width=115><br><sub>Omar Bautista</sub>]([https://udocumentos.blogspot.com) |
| :---: | :---: |