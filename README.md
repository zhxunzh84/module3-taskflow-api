# TaskFlow API

A task management REST API built with Java 21, Spring Boot 4.1.1, Maven, and Spring AI.

Developed as part of the NTU Advanced Professional Certificate in AI Engineering — Module 3 End of Module Assignment.

## Overview

TaskFlow API provides basic task management operations, including creating, retrieving, updating, deleting, and marking tasks as completed.

The project follows a layered Spring Boot architecture and includes an optional AI-powered endpoint that generates natural-language summaries of completed and pending tasks.

## Technology Stack

- Java 21
- Spring Boot 4.1.1
- Spring Web / Spring MVC
- Spring AI 2.0.1 with OpenAI
- Maven
- Lombok
- HashMap for in-memory storage
- Postman for API testing

## Project Architecture

The application follows four main layers:

| Layer | Component | Responsibility |
|---|---|---|
| Model | Task | Defines the task data structure |
| Repository | TaskRepository | Manages in-memory task storage |
| Service | TaskService | Implements business logic |
| Controller | TaskController | Exposes REST API endpoints |

An additional TaskSummaryService handles AI-powered task summarization.

## API Endpoints

| Method | Endpoint | Description | Success Status |
|---|---|---|---|
| GET | /api/tasks | Retrieve all tasks | 200 |
| GET | /api/tasks/{id} | Retrieve a task by ID | 200 |
| POST | /api/tasks | Create a new task | 201 |
| PUT | /api/tasks/{id} | Update an existing task | 200 |
| DELETE | /api/tasks/{id} | Delete a task | 200 |
| PUT | /api/tasks/{id}/complete | Mark a task as completed | 200 |
| GET | /api/tasks/summary | Generate an AI task summary | 200 |

Requests for nonexistent task IDs return 404 Not Found where applicable.

## Running the Application

**Prerequisites**

- JDK 21
- Maven
- An OpenAI API key for the optional AI summary functionality

**Configuration**

Set the `OPENAI_API_KEY` environment variable locally before starting the application.

The `application.properties` file references the environment variable using:

`spring.ai.openai.api-key=${OPENAI_API_KEY}`

Never commit an actual API key to source control.

**Start the application**

From the project root, run:

`mvn spring-boot:run`

The API is available at:

`http://localhost:8080`

## Example Request

**Create a Task**

POST `/api/tasks`

Request body:

`{"title":"Learn Spring Boot","completed":false}`

Example response:

`{"id":1,"title":"Learn Spring Boot","completed":false}`

HTTP Status: 201 Created

## AI-Powered Task Summary

The optional Spring AI integration adds:

GET `/api/tasks/summary`

The endpoint retrieves the current task list, sends task information to an OpenAI model through Spring AI ChatClient, and returns a short English summary of completed and pending tasks.

Example response:

"The task 'Learn Spring Boot' is completed. The task 'Complete TaskFlow Assignment' is still pending."

The API key is stored outside the project using an environment variable.

## Testing

The API was manually tested using Postman, covering:

- Task creation
- Retrieve all tasks
- Retrieve a task by ID
- Update an existing task
- Delete a task
- Mark a task as completed
- AI-powered task summarization
- Handling nonexistent task IDs

## Notes

Task data is stored in memory using a HashMap. Data is not persisted across application restarts.

## Assignment

NTU Module 3 — End of Module Assignment

**Project:** TaskFlow — Task Management API

## Module 4 — Dockerization & Cloud Deployment

### Deployment Overview

The TaskFlow API has been containerized using a multi-stage Docker build and successfully deployed to Railway.

- **Platform:** Railway
- **Containerization:** Docker (Multi-stage Build)
- **Java:** 21
- **Spring Boot:** 4.1.2
- **Build Tool:** Apache Maven
- **Application Port:** 8080
- **Deployment Source:** GitHub (`main` branch)

### Live Application

**Production API:** https://module3-taskflow-api-production-f5aa.up.railway.app/api/tasks

The endpoint returns a JSON array of tasks. An empty array (`[]`) is expected when no tasks have been created, as the application uses in-memory storage.

### Docker Build and Local Testing

Build the Docker image:

```bash
docker build -t taskflow:latest .
```

Run the container:

```bash
docker run -p 8080:8080 taskflow:latest
```

Verify the API:

```bash
curl -i http://localhost:8080/api/tasks
```

**Local test result:** HTTP 200 OK, with a valid JSON response.

### Railway Deployment

The application is deployed from the GitHub repository using the root-level Dockerfile.

The deployment was verified through the public HTTPS endpoint.

**Production verification:**

```bash
curl -i https://module3-taskflow-api-production-f5aa.up.railway.app/api/tasks
```

**Result:** HTTP 200 OK, `Content-Type: application/json`, response body `[]`.

### Deployment Screenshots

**Railway Dashboard — Successful Deployment**

![Railway Dashboard](screenshots/railway-dashboard.png)

**Live API — JSON Response**

![Live API Response](screenshots/live-api.png)

### Technical Notes

- The Dockerfile uses a multi-stage build with Maven and Java 21.
- The application runs on port 8080.
- Task data is stored in memory and will be reset when the application restarts or redeploys.
- The basic task-list endpoint does not require an OpenAI API call.
- The AI summary endpoint requires a valid OpenAI API key and should not be publicly exposed without appropriate access controls.

### Deployment Issues

No blocking issues were encountered during the verified local Docker build, container startup, or Railway deployment.
