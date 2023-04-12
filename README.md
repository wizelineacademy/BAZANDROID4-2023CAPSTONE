# BAZANDROID4-2023CAPSTONE

# Reto de "The Movie DB"

## Introducción
¡Gracias por participar en el programa de aprendizaje de Android!
Aquí encontraras las instrucciones del desafío.

## El Desafío
El propósito de este desafío es que puedas demostrar tus habilidades de desarrollo en Android. Esta es tu oportunidad para demostrar todo lo que has aprendido durante el curso.
En este desafío, construirás una aplicación de Android completa por tu propia cuenta. No queremos limitarte, por eso, mejor solicitamos que construyas una aplicación desde cero.
Esperamos que encuentres este ejercicio desafiante y llamativo.
El propósito es construir una aplicación que use la API pública de [The Movie DB](https://developers.themoviedb.org/3/getting-started/introduction), la cuál debe incluir:

- Una pantalla de login (usuario y contraseña)
- Una pantalla principal con las siguientes 3 secciones:
  - Now Playing. Las últimas peliculas que han salido: /movie/now_playing
  - Latest. Las últimas peliculas que han salido: /movie/latest
  - Top Rated. Las mejor votadas: /movie/top_rated
- También deberan de consumir el api de generos de peliculas (/genre/movie/list) para relacionarlas con estas últimas y mostrarlas en el detalle de la pelicula

## Requerimientos

Estos son los requerimientos principales que evaluaremos:

- Hacer uso de todo lo que has aprendido durante el curso:
    - Mejores prácticas
    - Diseño de API
    - Diseño de UI
    - Patrones de diseño

## Enviando los entregables

Para publicar tu trabajo, deberás seguir estos pasos:

1. Crear un `pull request` con tu código, apuntando a la rama `master`
2. Llenar este [formato](https://forms.gle/32jzE4HVK9evpkC26)
3. Mantente al pendiente de la retroalimentación.
4. Genera los cambios conforme a los comentarios de tu mentor.

## Para empezar

Para empezar, sigue los siguientes pasos:

1. Realiza `Fork` a este proyecto
2. Convierte tu proyecto en privado
3. Concede accesos a este proyecto a tu mentor
4. Genera `commit` y sube tus cambios de manera periódica
5. Realiza los cambios según los comentarios de tu mentor
6. Deberán crearse una cuenta en [The movie DB](https://www.themoviedb.org/signup) para poder generar un API KEY. La cual debera ser usada en todos los request a la api
5. Realiza los cambios según los comentarios de tu mentor
7. ¡Diviértete!

## Entregables

Proporcionamos las fechas de entrega para que pueda organizarse; por favor, tome este desafío con seriedad e intente progresar constantemente.
Vale la pena mencionar que solamente podrás obtener retroalimentación del equipo de revisión para tu primera entregable, de tal manera tendrás la oportunidad de corregir o mejorar tu código según nuestras sugerencias.
Para el último entregable, proporcionaremos cierta retroalimentación, pero ya no habrá una última revisión posterior a ello. Si estás teniendo conflictos con algo, contacta a tu menor o cualquier encargada para obtener ayuda a tiempo. Siéntete libre de usar el canal de Slack.

## Primer Entregable
Con base en el material de autoestudio y las mentorías hasta este entregable, sugerimos que desarrolles lo siguiente:

- Vista de login
- Registro de usuario en firebase a partir de la vista de login
- Uso de retrofit para el consumo de la API
- Usar los patrones MVVM o MVI
- Listado de peliculas (las 3 categorias)
- Usar corutinas y RxJava
- Hacer uso de buenas prácticas
- Usar Navigation Component
- Usar un Interceptor para enviar el API KEY en cada llamada a la API. Asi como el lenguaje y la region, los cuales pueden obtener de Locale (Estos dos ultimos puntos son opcionales)

### **Fecha de Entrega 10 de abril**

> Nota: la lista anterior de este entregable es sólo una guía para ayudarte a distribuir la carga de trabajo; puedes entregar más o menos elementos si es necesario. De igual manera, si entregas menos elementos en este punto, tendrás que cubrir los elementos restantes en el siguiente entregable.

## Segundo Entregable

Con base en el material de autoestudio y las mentorías hasta este entregable, sugerimos que desarrolles lo siguiente:

- Vista de detalle de pelicula con Compose
- ROOM. Aseguarse de tener un modelo relacional entre las peliculas y sus generos.
- Usar state flows con las corrutinas
- Injeccion de depencias con Hilt
- Hacer uso de buenas prácticas

### **Fecha de Entrega 24 de abril**

> Nota: la lista anterior de este entregable es sólo una guía para ayudarte a distribuir la carga de trabajo; puedes entregar más o menos elementos si es necesario. De igual manera, si entregas menos elementos en este punto, tendrás que cubrir los elementos restantes en el siguiente entregable.

## Tercer y último entregable
- Modulos de Remote y Local. Separar el uso de Retrofit (remote) y ROOM (local) en diferentes modulos
- Pruebas unitarias
- Pruebas de instrumentacion
- Implementar detekt o ktlint
- Hacer uso de buenas prácticas

### **Fecha de Entrega 8 de mayo**


> Importante: este es el último entregabble, por lo cual todos los requerimientos deben ser incluidos. Proveeremos retroalimentación de tu entregable y tendrás 3 días más para aplicar los cambios. En el tercer día, dejaremos de recibir cambios a las 11:00 a.m.

