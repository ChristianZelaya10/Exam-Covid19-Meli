# Ejercicio Registro de COVID-19 - Mercado libre

Ejercicio práctico para MercadoLibre. 

Se puede ver una version estable del proyecto aqui:

- [Servicio de checks](http://ec2-18-218-75-220.us-east-2.compute.amazonaws.com:8080/covid/checks).
- [Servicio de estadisticas](http://ec2-18-218-75-220.us-east-2.compute.amazonaws.com:8080/covid/stats)

- [Ejercicio](#ejercicio)
  - [Especificaciones](#especificaciones)
  - [Implementación y tecnologias usadas](#implementaci%C3%B3n-y-tecnologias-usadas)
  - [Comentarios relevantes](#comentarios-relevantes)
- [Setup](#setup)
  - [Instrucciones](#instrucciones)
  - [Uso](#uso)
  - [API Url](#api)
  - [Servicios](#servicios)
    - [Es mutante](#es-mutante)
    - [Estadisticas](#estadisticas)
- [Test](#test)
  - [Automaticos](#automaticos)
  - [Scripts](#scripts)
  - [Cobertura](#cobertura)

## Ejercicio

### Especificaciones


### Implementacion y tecnologias usadas

Se utilizó Java como el lenguaje de programacion utilizando Spring Boot. Los reportes de test coverage hechos con JaCoCo.

La aplicación está subida a AWS EC2 y tiene su base alojada en RDS de Amazon, la misma utiliza mySQL.

### Comentarios relevantes


## Setup

### Instrucciones
Para compilar y ejecutar proyecto es necesario contar con la version 11.0.5 de la JDK y Maven para la gestion de las dependencias.
El aplicación la esta apuntando a la base alojada en RDS de amazon asi que que no es necesario correr ningun script.

Clonar este repositorio: https://github.com/ChristianZelaya10/Exam-Covid19-Meli.git

Una vez levantada la aplicacion se puede realizar invocaciones a la API.

El puerto por defecto de la API es 8080.

### Uso

Para iniciar la aplicación, asegúrese de cumplir con las instrucciones anteriores. 

Una vez listo, ejecutar la clase principal en su IDE preferido y espere hasta que se inicie la aplicación.


### API Url

URL local: http://localhost:8080

URL hosteada en Amazon: http://ec2-18-218-75-220.us-east-2.compute.amazonaws.com:8080

### Servicios
#### Checks

Request: 
- POST hhttp://ec2-18-218-75-220.us-east-2.compute.amazonaws.com:8080/covid/checks

Request body (caso Infectado):

```
  {
    "name": "Diego",
    "country": "Argentina",
    "dna": ["ATGCGA", "CGGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
}
```

Response:

```
  {
    "id": 1234,
    "name": "Diego",
    "country": "Argentina",
    "dna": ["ATGCGA", "CGGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"],
    "result": “Infectado”
}
```
Request body (caso Sano):

```
  {
    "name": "Hernan",
    "country": "Argentina",
    "dna": ["ATGCGA", "CGGTAC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
}
```

Response:

```
  {
    "id": 2222,
    "name": "Hernan",
    "country": "Argentina",
    "dna": ["ATGCGA", "CGGTAC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"],
    "result": "Sano"
},

```
Request body (caso Inmune):

```
  {
   "name": "Marcos",
   "country": "Brasil",
   "dna": ["AAAAGA", "CGGTGC", "TTATGT", "AGAAGT", "CCCCTT", "TCACTT"]
}
```

Response:

```
  {
    "id": 3333,
    "name": "Marcos",
    "country": "Brasil",
    "dna": ["AAAAGA", "CGGTGC", "TTATGT", "AGAAGT", "CCCCTT", "TCACTT"],
    "result": "inmune"
}
```

#### Estadisticas

Request: 
- http://ec2-18-218-75-220.us-east-2.compute.amazonaws.com:8080/covid/stats

Response: 200 (application/json)

```
{
   "healthy":4,
   "infected":5,
   "inmune":9
}
```

### Test

#### Automaticos

Para la ejecucion de los test automaticos utilice jUnit.

#### Cobertura

Si bien la cobertura de codigo en la herramienta Codecov muestra un 70%, ejecutando los test localmente con la herramienta
Jacoco nos da 78%.

![coverage](./doc/images/coverage.png)
