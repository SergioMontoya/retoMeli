
# Reto Mutantes - Mercadolibre

### Especificaciones
Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar
contra los X-Men.
Te ha contratado a ti para que desarrolles un proyecto que detecte si un
humano es mutante basándose en su secuencia de ADN.

Para eso te ha pedido crear un programa con un método o función con la siguiente firma (En
alguno de los siguiente lenguajes: Java / Golang / C-C++ / Javascript (node) / Python / Ruby):
boolean isMutant(String[] dna); // Ejemplo Java
En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla
de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las
cuales representa cada base nitrogenada del ADN.

![image](https://user-images.githubusercontent.com/98285203/174024593-b648f08b-30a2-4a6c-a8e5-409a4141423b.png)

Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras
iguales​, de forma oblicua, horizontal o vertical.
Ejemplo (Caso mutante):
String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
En este caso el llamado a la función isMutant(dna) devuelve “true”.

Desarrolla el algoritmo de la manera más eficiente posible

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

