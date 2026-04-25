# MortgageBridge (Phase 1)

This project contains the backend REST APIs for the Home Mortgages system. 

**Tech Stack (Phase 1):**
* Java 17
* Spring Boot 3.x
* Spring Data JPA (Hibernate)
* H2 In-Memory Database
* OpenAPI/Swagger for API Documentation

**Future Plans (Phase 2):**
* Migration to MongoDB

## Setup and Running the Application

### Prerequisites
* JDK 17
* Maven

### How to Run
1. Navigate to the root directory of the project.
2. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```
3. The application will start on `http://localhost:8080`.

### Database
* The application uses an in-memory H2 database for development and testing.
* The database console can be accessed at: `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:mortgagedb`
* **Username:** `sa`
* **Password:** `password`

## API Documentation (Swagger)

Swagger UI is configured for exploring and testing the REST APIs.
Once the application is running, open the following link in your browser:

👉 **[Swagger UI Link](http://localhost:8080/swagger-ui.html)**

## Testing

To run the unit and integration tests:
```bash
mvn test
```

## Available APIs
* `POST /api/v1/mortgages`: Submit a new mortgage application.
* `GET /api/v1/mortgages`: List all applications.
* `GET /api/v1/mortgages/{id}`: Get application details by ID.
* `PUT /api/v1/mortgages/{id}/status`: Update the status of an application.
* `DELETE /api/v1/mortgages/{id}`: Delete an application.
