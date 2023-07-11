Project created using Spring Boot to provide a REST API to manage Users and Projects

Endpoints:

GET /users  -  Get list of users
POST /users - Creates user specified in request body
PUT  /users/{userId} - Updates user specified in path variable according to request body
DELETE /users/{userId} - Deletes user
POST /users/{userId}/projects/{projectId} - Link and User to a Project

GET /projects - Get list of projects
POST /projects - Create a project
PUT /projects/{projectId} - Update a project
DELETE /projects/{projectId} - Delete a project

