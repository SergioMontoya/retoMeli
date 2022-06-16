
# Reto Mutantes - Mercadolibre

Se puede ver una version estable del proyecto aqui:


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

Los archivos correspondientes a la especificación del ejercicio se encuentran en la carpeta `spec`. Dentro de ella
se encuentra [un pdf](./spec/Examen%20Mercadolibre%202017.pdf) que describe la funcionalidades y requisitos esperados
que contenga el proyecto.

### Implementacion y tecnologias usadas

- [Spring WebFlux]
- [MongoDB Atlas]
- [jUnit]
- [Amazon Web Service (ec2)]

## Setup

### Instrucciones de us
Para compilar y ejecutar proyecto es necesario contar con la version 11 de la JDK de preferencia y Gradle para la gestion de las dependencias.

Clonar el repositorio https://github.com/SergioMontoya/retoMeli
Descargar dependencias desde el build.gradle

### Uso

Una vez realizado el paso anterior, para dar inicio a la aplicación deben ejecutar la clase principal del proyecto "MutantsApplication.java"

### Pruebas
Para realizar las pruebas al servicio se entregan los siguientes artefactos.

#Collection de postman, con los ejemplos para las solicitudes para ambiente local y el servicio desplegado sobre AWS.

#Pruebas de carga realizadas en jmeter para las solicitudes hacia AWS.
Nota: El recurso aprovisionado para la aplicación esta compuesto de una unica instancia Ec2 (Con posibilidad de escalar) que hace parte de la capa gratuita.

![image](https://user-images.githubusercontent.com/98285203/174022828-e5d5ccb0-a641-4dae-a5c2-a77602908a32.png)


#Se comparte adicionalmente el punto de acceso al servicio.

###API - Mutant

CASO#1: Is mutant
Request: 
- POST ec2-3-86-251-213.compute-1.amazonaws.com:8080/mutant
Body
```
  {"dna":["ATGCGA", "CAGGGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]}
```
Response:
```
  200 OK
```
CASO#2: No es mutante

Body
```
  {"dna":["AATACT", "CCCAGA", "GGGATT", "AATTCC", "GGATCG", "TCACTG"]}
```
Response:
```
  403 Forbidden
```
CASO#3: Se munistran datos erroneos

Body
```
  {"dna":["AAXACT", "CCCAGA", "GGGATT", "AATTCC", "GGATCG", "TCACTG"]}
```
Response:
```
  400 Bad Request
```

###API - Stats

Request: 
- GET ec2-3-86-251-213.compute-1.amazonaws.com:8080/stats

Response: 200 (application/json)

```
{
    count_mutant_dna: 4,
    count_human_dna: 1,
    ratio: 0.8
}
```


#### Cobertura

