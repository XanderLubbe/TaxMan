# TaxMan

A simple PAYE Tax API designed in Java.

***

## Table of contents
* [Introduction](#introduction)
* [Technologies](#technologies)
* [Some background](#some-background)
* [Getting Started](#getting-started)
* [Data](#data)
***
## Introduction
TaxMan is an API for finding applicable PAYE tax amounts based on a monthly salary. 
It works by providing it with either your salary amount or your salary amount as 
well as your age. The former will return a response with three applicable amounts,
those being the tax amounts for under 65's, between 65 and 74, and over 75's. The latter
will return the applicable tax for your salary and age.
***
## Technologies
* Java
* Gradle 
* Spring Boot
* JPA
* H2
* Swagger

Swagger documentation can be found at the URL - `http://localhost:8080/swagger-ui/`.

The H2 database console can be found at the URL - `localhost:8080/h2-console/`, the username is `sa` and the password is blank.
***
## Some background
As stated in the [introduction](#introduction), the API can be hit at two endpoints, 
namely /taxes and /tax. The first will return three applicable amounts, the second will 
return only one based on the age you provide. Here is an example of how it looks:
* ### /taxes
  #### Request URL
    `http://localhost:8080/taxes?salary=38719`

    #### Curl
    ```
    curl -X 'GET' \
    'http://localhost:8080/taxes?salary=38719' \
    -H 'accept: */*'
    ```
    
    #### Response body
    ```
    {
      "amountUnder65": 7655,
      "amountUnder74": 6905,
      "amountOver75": 6656
    }
    ```
* ### /tax
    
    #### Request URL
    `http://localhost:8080/tax?salary=54690&age=71`
    
    #### Curl
    ```
    curl -X 'GET' \
      'http://localhost:8080/tax?salary=54690&age=71' \
      -H 'accept: */*'
    ```

    #### Response body
    ```
    {
      "amount": 12580
    }
    ```


***
## Getting Started

The application can be launched by running:

`gradle build`

Followed by:

`gradle bootrun`
***
## Data
### How data is loaded
The application's database is populated by either its `data.csv` or `data.sql` file. The data.csv is used
when the data.sql cannot be found inside the resources folder. The data.sql is run by default in most
cases. 

### How to modify loaded data
There is one correct way of loading new data to be populated into the database, and this is by updating
the `data.csv` with the new data and then immediately deleting the current `data.sql` as it will be holding
the old data. Once the application is run, the new data will be populated into the database and then an
export of the database's table can done. This export will replace the 'data.sql' file.

### Structures for the `data.csv` and `data.sql`:
* #### CSV

    ```
    0, 4979, 0, 0, 0
    4980, 5030, 0, 0, 0
    ...
    ```

* #### SQL
    ```
    ;
    INSERT INTO "PUBLIC"."TAXDTO" (id, LOWER_LIMIT, AMOUNT_UNDER65, AMOUNT_UNDER74, AMOUNT_OVER75, UPPER_LIMIT) VALUES
    (1, 0, 0, 0, 0, 4979),
    (2, 7479, 0, 0, 0, 7529),
    ... ;
    ```
***