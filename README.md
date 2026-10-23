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