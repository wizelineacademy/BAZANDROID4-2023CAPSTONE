# BAZANDROID4-2023CAPSTONE

# Reto de "The Movie DB"

## Introducción
Aplicación para visualizar la última película (latest), Now Playing y TopRated
a Través de un registro previo de Firebase Auth.
Esta aplicación tiene como lenguaje principal kotlin con JetpackCompose

# Características
## Arquitectura
- La aplicación cuenta con la arquitectura principal hecha en MVVM con una aplicación de MVI en la
pantalla correspondiente al login que cuenta con su UIState
- Utiliza corutinas para el consumo de los servicios y guardado en la base de datos

## Módulos

- databasemovies
  - Este módulo maneja todo lo relacionado a las bases de datos
  - Cuenta con 3 entidades principales: Movies, Genres y MoviesWithGenres
  - MoviesWithGenres Es un módulo relacional de uno a muchos entre las películas y géneros
- remote
  - Esté módulo maneja las apis y los módelos para el consumo a través de retrofit

## Otras características

A continuación se listan otrás carácterísticas implementadas
- LiveData y StateFlow
- Clean arquitecture
- Inyección de dependencias con Hilt
- Coil y Glide para consumo de imágenes
- Navigation Component para JetpackCompose
- RXJava para el manejo de los generos de las películas
- Pruebas Unitarias en el archivo MoviesUnitTest
- FirebaseAuth con las funciones de alta y login
- Separación de módulos en para bases de datos en room y para apis

# CAPTURAS
- Login
- ![Login](login.png)

- Movies List
  - ![List](listmovies.png)

- Movies Detail
  - ![DetailMovies](detailmovies.png)
  

