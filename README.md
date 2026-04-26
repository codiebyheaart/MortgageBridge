# MortgageBridge (Phase 1)

This project contains the backend REST APIs for the Home Mortgages system. 

## What is this API all about and how does it help?

The **MortgageBridge API** is designed to streamline and digitize the home mortgage application process. Traditionally, applying for a mortgage involves a lot of paperwork, manual tracking, and delays. 

This API provides a centralized, secure backend where:
1. **Applicants** can submit their mortgage details (loan amount, interest rate, property address) digitally.
2. **Loan Officers/Admins** can retrieve application data instantly.
3. **Automated Systems** can process these applications and update their status (e.g., from `PENDING` to `APPROVED` or `REJECTED`).

**Key Benefits:**
* **Efficiency:** Reduces manual entry and provides instant access to application states.
* **Scalability:** Built on a robust Spring Boot architecture, allowing it to handle thousands of applications as the business grows.
* **Integrations:** The RESTful nature of the API makes it easy to plug into any front-end application (like a React or Angular web portal) or third-party credit-checking services.

---

## Tech Stack (Phase 1)
* **Java 17**
* **Spring Boot 3.x**
* **Spring Data JPA (Hibernate)**
* **H2 In-Memory Database**
* **OpenAPI/Swagger** for API Documentation

*(Note: Phase 2 will involve a migration to MongoDB)*

---

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

### Database Access
* The application uses an in-memory H2 database for development and testing.
* You can view the live database tables at: `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:mortgagedb`
* **Username:** `sa`
* **Password:** `password`

---

## 🚀 Live Demo Guide

Use this section to give a quick demonstration of the API in action.

### Option 1: Using the Visual Swagger UI (Recommended)
1. Start the application (`mvn spring-boot:run`).
2. Open your browser and navigate to: 👉 **[Swagger UI Link](http://localhost:8080/swagger-ui.html)**
3. Expand the **POST `/api/v1/mortgages`** endpoint, click **"Try it out"**, and paste the following JSON payload into the request body:
   ```json
   {
     "applicantName": "Alice Smith",
     "applicantEmail": "alice@example.com",
     "loanAmount": 350000,
     "interestRate": 4.2,
     "termInYears": 30,
     "propertyAddress": "789 Pine St, Seattle, WA",
     "status": "PENDING"
   }
   ```
4. Click **Execute** and show the `201 Created` response.
5. Next, expand the **GET `/api/v1/mortgages`** endpoint, click **"Try it out"**, and hit **Execute** to show the newly created application in the database!

### Option 2: Using the Terminal (cURL)
You can also demonstrate the API using terminal commands. Open a new terminal window and run:

**1. Create a Mortgage Application:**
```bash
curl -X POST http://localhost:8080/api/v1/mortgages \
     -H "Content-Type: application/json" \
     -d '{"applicantName":"Alice Smith","applicantEmail":"alice@example.com","loanAmount":350000,"interestRate":4.2,"termInYears":30,"propertyAddress":"789 Pine St, Seattle, WA","status":"PENDING"}'
```

**2. View All Applications:**
```bash
curl -X GET http://localhost:8080/api/v1/mortgages
```

---

## Available Endpoints Reference
* `POST /api/v1/mortgages`: Submit a new mortgage application.
* `GET /api/v1/mortgages`: List all applications.
* `GET /api/v1/mortgages/{id}`: Get application details by ID.
* `PUT /api/v1/mortgages/{id}/status`: Update the status of an application.
* `DELETE /api/v1/mortgages/{id}`: Delete an application.

## Testing
To run the automated unit and integration tests:
```bash
mvn test
```
