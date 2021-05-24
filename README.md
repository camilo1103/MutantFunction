# Mutant Function
## This is a function for validating the DNA sequence

[![jonathanbernal](https://imagesbucket-mutantfunction.s3.amazonaws.com/firma.png)](https://www.linkedin.com/in/jonathan-camilo-bernal-aldana-542507146/)


The MutantFunction is an api developed in Java Maven and designed for Severless architecture. This function serves to validate the DNA sequence of a human and determine if it is mutant or not.

## Features

- Validate DNA sequences to verify if it is a mutant or not
- Its serverless architecture allows it to attend fluctuations between 100 and 1 million requests per second.
- Stores the information in a non-relational database (DynamoDB)



## Tech

MutantFunction uses different technologies to work properly and efficiently.

- [Java] - The API is developed in Java.
- [Maven] - Software tool for the management and development of Java applications.
- [Lambda AWS] - AWS Lambda is a serverless computing service.
- [DynamoDB AWS] - Amazon DynamoDB is a key-value and document database that delivers single-digit millisecond performance.
- [ApiGateway AWS] - Amazon API Gateway is a fully managed service that makes it easy for developers to create, publish, maintain, monitor, and protect APIs at any scale.


## Installation

MutantFunction requires [Maven](https://maven.apache.org/download.cgi) to compile.

Install the dependencies and Java 8 JDK

```sh
cd MutantFunction
mvn clean package
```

After compiling the application, you must go to the Target folder and take the file mutantFunction-1.0-SNAPSHOT.jar, in AWS in the lambda function load the code through a route an S3.

![alt text](https://imagesbucket-mutantfunction.s3.amazonaws.com/Code.PNG)



## Infrastructure as a service

The architecture used for this Api is a serverless architecture, this design was chosen to withstand the high fluctuations of requests made to the Api. lambda functions raise a container for each request, responding elastically to high traffic and generating a charge only for the service used. Additionally to guarantee performance, it automatically reuses the containers lifted in previous requests.

![alt text](https://imagesbucket-mutantfunction.s3.amazonaws.com/Arquitectura.jpg)



## Algorithm
The algorithm developed to validate a sequence aligned obliquely, vertically or horizontally, consists of traversing each of the points of the matrix and obtaining the data of its perimeter in a depth of 1

suppose the sequence

```json
{
 "dna":["ATGCAA","CAGTCC","TTATGT","AGATCG","AGAAGG","TCACTG"]
}
```
the matrix obtained, understanding each string as a row and each character of the strings as the columns.

![alt text](https://imagesbucket-mutantfunction.s3.amazonaws.com/Matriz.png)

You will know if a human is a mutant, if you find more than one sequence of four letters equal, obliquely, horizontally or vertically.
In each point it is validated that the points around exist, and if they exist it is validated if their value is equal to the character of the current letter

![alt text](https://imagesbucket-mutantfunction.s3.amazonaws.com/Sobrepuesto.png)

To delve into the algorithm, review the [Flow Chart](https://drive.google.com/file/d/1xoIfUIeauYrWY5zouzVlENaCVeUOyvxX/view?usp=sharing)


![alt text](https://imagesbucket-mutantfunction.s3.amazonaws.com/Diagrama+de+flujo.jpg)

#### Coverage unit test

The Mutant function has unit test development with Junit, obtaining a coverage percentage of 86.42%

![alt text](https://imagesbucket-mutantfunction.s3.amazonaws.com/Coverage.PNG)

#### How to consume Apis?

##### POST /dev/mutant/


To consume the /mutant/ api, a request must be sent with the POST method to the following endpoint with the body dna

```json
{
 "dna":["ATGCAA","CAGTCC","TTATGT","AGATCG","AGAAGG","TCACTG"]
}
```

Endpoint: *https://xeohr7t3h4.execute-api.us-east-1.amazonaws.com/dev/mutant*

Curl code

```sh
curl --location --request POST 'https://xeohr7t3h4.execute-api.us-east-1.amazonaws.com/dev/mutant' \
--header 'Content-Type: application/json' \
--data-raw '{
 "dna":["ATGCAA","CAGTCC","TTATGT","AGATCG","AGAAGG","TCACTG"]
}'
```

##### GET /dev/stats/


To consume the /stats/ api, a request must be sent with the GET method to the following endpoint

Endpoint: *https://xeohr7t3h4.execute-api.us-east-1.amazonaws.com/dev/stats*

Curl code

```sh
curl --location --request GET 'https://xeohr7t3h4.execute-api.us-east-1.amazonaws.com/dev/stats'
```

This endpoint response with this body

```json
{
    "count_mutant_dna": 7,
    "count_human_dna": 2,
    "ratio": 3.5
}
```

   [Java]: <https://www.java.com/es/download/help/java8_es.html>
   [Maven]: <https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html>
   [Lambda AWS]: <https://aws.amazon.com/es/lambda/features/>
   [DynamoDB AWS]: <https://aws.amazon.com/dynamodb/>
   [ApiGateway AWS]: <https://aws.amazon.com/es/api-gateway/>
   

