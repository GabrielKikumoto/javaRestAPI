Project created using Spring Boot to provide REST API to perform basic CRUD actions on entities User and Project

# Tools
- Mysql 8.0.33
- Apache Maven 3.9.3
- Java version: 17.0.2

# MySQL configs
In application.properties you can find the MySQL user and password configs. Change it as you find suitable.


# Endpoints:

## User endpoints
- GET /users  -  Get list of users
- POST /users - Creates user specified in request body
- PUT  /users/{userId} - Updates user specified in path variable according to request body
- DELETE /users/{userId} - Deletes user
- POST /users/{userId}/projects/{projectId} - Link an User to a Project

## Project endpoints
- GET /projects - Get list of projects
- POST /projects - Create a project
- PUT /projects/{projectId} - Update a project
- DELETE /projects/{projectId} - Delete a project


