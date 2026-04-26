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

---

## ☁️ Deploying to Red Hat OpenShift

Since the code is hosted on Git, deploying to OpenShift is incredibly straightforward using OpenShift's **Source-to-Image (S2I)** feature. OpenShift will automatically pull the code from Git, build the Maven project, create a container image, and deploy it.

### Step 0: Get a Free OpenShift Cluster (Red Hat Developer Sandbox)
If you don't have an OpenShift cluster, you can get a free 30-day sandbox environment from Red Hat to demo this project.

1. **Register for a free Red Hat account:** Go to the [Red Hat Developer Sandbox Registration Page](https://sso.redhat.com/auth/realms/redhat-external/login-actions/registration?client_id=crtoolchain-public&tab_id=7SLOR9dHQSw&client_data=eyJydSI6Imh0dHBzOi8vc2FuZGJveC5yZWRoYXQuY29tL2FwaS9hdXRoL29pZGMvaGFuZGxlci9mcmFtZSIsInJ0IjoiY29kZSIsInN0IjoiNmU2ZjZlNjM2NTNkNDI1NzY3NDY2YzQ3NzE1MzZmMzg1YTYyMzY3NzU2NjE3ODU4NmI3OTZhNzcyNTMzNDQyNTMzNDQyNjY1NmU3NjNkNzA3MjZmNjQ3NTYzNzQ2OTZmNmUyNjZmNzI2OTY3Njk2ZTNkNjg3NDc0NzA3MzI1MzM0MTI1MzI0NjI1MzI0NjczNjE2ZTY0NjI2Zjc4MmU3MjY1NjQ2ODYxNzQyZTYzNmY2ZDI2NzI2NTY0Njk3MjY1NjM3NDU1NzI2YzNkNjg3NDc0NzA3MzI1MzM0MTI1MzI0NjI1MzI0NjczNjE2ZTY0NjI2Zjc4MmU3MjY1NjQ2ODYxNzQyZTYzNmY2ZDI1MzI0NjI1MzM0NjY5NmU3NDYzNmQ3MDI1MzM0NDM3MzAzMTMzNjEzMDMwMzAzMDMwMzIzNjQ3NWE0ZDQxNDEzMjI2NjY2YzZmNzczZDcyNjU2NDY5NzI2NTYzNzQyNjczNjM2ZjcwNjUzZDZmNzA2NTZlNjk2NDJiNzA3MjZmNjY2OTZjNjUyYjY1NmQ2MTY5NmMifQ) and create an account.
2. **Access your Sandbox:** Once registered and verified, launch your OpenShift Developer Sandbox.
3. **Get your Login Command:** 
   - Click your username in the top right corner of the OpenShift Web Console.
   - Click **"Copy login command"**.
   - Click **"Display Token"**. This will give you the exact `oc login --token=...` command you need.
4. **Install the `oc` CLI tool:**
   - Download the OpenShift Command Line Interface (`oc`) from the help menu (?) in the top right of the Web Console, under "Command Line Tools", and add it to your system PATH.

### Deployment Steps (From Git)

1. **Login to your OpenShift cluster:**
   ```bash
   oc login --token=<your-token> --server=<your-cluster-url>
   ```

2. **Create a new project (Namespace):**
   ```bash
   oc new-project mortgage-bridge
   ```

3. **Deploy directly from your Git repository:**
   *(Replace `<YOUR_GIT_REPO_URL>` with the actual HTTP/HTTPS Git URL of this repository)*
   ```bash
   oc new-app registry.access.redhat.com/ubi8/openjdk-17~<YOUR_GIT_REPO_URL> --name=mortgage-api
   ```
   *OpenShift will now start a build pod to compile the Spring Boot app using Maven.*

4. **Watch the build logs (Optional but recommended):**
   ```bash
   oc logs -f bc/mortgage-api
   ```

5. **Expose the service to the internet:**
   Once the build is complete and the pod is running, create a Route so external clients can access the API:
   ```bash
   oc expose svc/mortgage-api
   ```

6. **Get your live URL:**
   ```bash
   oc get route mortgage-api
   ```
   *Copy the `HOST/PORT` URL from the output. You can now access your Swagger UI at `http://<ROUTE_URL>/swagger-ui.html`!*

### Alternative: Dockerfile Deployment
If you prefer building via the included `Dockerfile`:
```bash
oc new-app --strategy=docker <YOUR_GIT_REPO_URL> --name=mortgage-api
oc expose svc/mortgage-api
```


### Download oc CLI
Go to: https://mirror.openshift.com/pub/openshift-v4/clients/ocp/stable/
Download for your OS:

Windows → openshift-client-windows.zip
Mac → openshift-client-mac.tar.gz
Linux → openshift-client-linux.tar.gz

### Step 2 — Get your login command from the console

In the OpenShift Web Console, click your username (top-right corner)
Click "Copy login command"
Click "Display Token"
Copy the full oc login --token=... --server=... line

  ```bash  
oc new-project mortgage-bridge
oc project dilsecodie-dev
oc new-app registry.access.redhat.com/ubi8/openjdk-17~https://github.com/codiebyheaart/MortgageBridge --name=mortgage-api
oc logs -f buildconfig/mortgage-api
oc expose service/mortgage-api
oc get route mortgage-api
   ```

