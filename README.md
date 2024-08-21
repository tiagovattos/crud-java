# Pure Java CRUD
> I'm developing this pure Java CRUD project just for studying proposes.

## Tools
- Java 21
- Maven 3.9.6
- H2 2.3.232

## How to run the project:
- Download H2 binary JAR from https://h2database.com/html/download.html
- Execute H2 binary jar
- Connect to database and execute this SQL script to create the tables </br>
  ```CREATE TABLE PET (id BIGINT PRIMARY KEY, name VARCHAR(255) NOT NULL, age INTEGER NOT NULL);```
- Clone the project and import to your IDEA
- Upload maven dependencies
- Run Main.java
