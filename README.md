# Eco meter

## Overview
Eco meter is a RESTful API for monitoring gas, cold water, and hot water usage. 
The application provides two main API methods: one for submitting current measurements for a given user 
and another for retrieving the history of previously submitted measurements for a given user.

## Technologies Used
- Java 8 JDK
- Spring Boot
- Spring Boot Validation
- Spring Data JPA
- Spring Web
- Springdoc OpenAPI (for API documentation)
- HSQLDB (for testing in-memory database)
- Docker Compose (for running with a persistent database (HSQLDB))
- Flyway (for database migrations)
- Lombok
- Maven (for build and dependency management)


## Local Development
### Prerequisites
- Java 8 JDK installed
- Docker and Docker Compose installed (for running with a persistent database)
- IntelliJ IDEA (optional)

## Running the Application Locally
1. Clone the repository: git clone https://github.com/sargiss987/eco-meter.git
2. Navigate eco-meter directory (cd eco-meter)
3. Navigate environment/local directory (cd environment/local) and run: docker compose up -d
4. Build the application using Maven: ./mvnw clean package
5. Run app through IDEA or navigate target directory (cd target) and 
   run command: java -jar EcoMeter-0.0.1-SNAPSHOT.jar (make sure the java path is set on you system)

## Running Tests
The tests are configured to run using an HSQLDB in-memory database.
Run test through IDEA or ./mvnw test

## Database Migrations
Flyway is used for database migrations. Migrations are automatically applied on application startup.

## API Documentation
API documentation is available using Springdoc OpenAPI at http://localhost:8080/swagger-ui/index.html.


