# Truck CRUD Challenge


## Technologies used
### Backend

 - Java 8
 - **Spring Boot 2.2.2 (required > 2)**
 - Lombok
 - Liquibase
 - Swagger (Swagger-codegen)
 - Mockito
 - AspectJ
 - **H2 DB (required)**
 - Hibernate
 - Jacoco
 - etc...

### Frontend

 - **Angular 8 (Required > 7)**
 - Clarity design
 - Ngrx
 - Ngrx effects

## Assumptions

 - The application doesn't need to be able to create/update/delete the following resources:
	 -  FuelTypes
	 - Classifications
	 - Applications
	 - Colors
 - The application may be used by multiple persons at the same time.
	 - I implemented optimistic locking in order to manage concurrent access.
 - The application should be able to move to another RDMS without much hassle.
	 - For that I used JPA/Hibernate and Liquibase.


## API
The swagger definition can be found [here](https://app.swaggerhub.com/apis/strang3quark/TruckAPI/1.0-oas3).

### Resources:

 - Truck
 - FuelType
 - Classification
 - Application
 - Color

---

### Endpoints:

 - **GET** /trucks
	 - list all trucks
 - **POST** /trucks
	 - create a truck
 - **GET** /trucks/{id}
	 - get truck by id
 - **PUT** /trucks/{id}
	 - update truck by id
 - **DELETE** /trucks/{id}
	 - delete truck by id

  - **GET** /fueltypes
  - **GET** /classifications
  - **GET** /applications
  - **GET** /colors

---

### Notes:
 - As required I implemented the CRUD operations for the truck.
 - The definition was done with OpenAPI 3.
	 - I used tags in order to have a controller interface for each resource.
 - I created the list all endpoints for the other entities so I can get them in the frontend, I know I could just define an enumerator in the API definition, but that didn't offer much flexibility.



## Database ER diagram
![ER Diagram](database.png?raw=true)

## Backend

For the backend I used Spring Boot, and Swagger codegen as requested, for the DB I used Hibernate.

I pretty much divided the application into 3 layers:

 - Facade
	 - Controllers
	 - Mappers (DTO to Model and vice-versa)
 - Management
	 - Business logic (validations, etc)
	 - Manages access to the persistence layer
	 - Should have a service for each repository
 - Persistence
	 - Responsible for persisting and retrieving information from the database
	 - Uses as little logic as possible
	 - Should only be accessed by the management layer
		 - Each public repository method should have a single point of entrance (should be called by only one method in management).

### Modules:

- **Web**:
	- Contains the Spring starter and spring settings:
		- JPA settings
		- Logging
		- etc..
	- Contains the liquibase changesets.
- **Foundation**:
	- Contains the shared code across all modules. This could be:
		- Annotations
		- Exceptions
		- Utils
		- etc...
- **Model**:
	- Contains the entities wich are mapped to the database, usually POJOs.
 - **Codegen**:
	 - Contains all the generated code from the API definition (Java and Typescript).
		 - Java:
			 - Controller interfaces
			 - DTOs
		 - Angular:
			 - Services
			 - DTOs
 - **Facade**:
	 - Implementation of controllers from codegen
	 - Mappers for the DTOs from codegen to model, and vice versa.
 - **Management**
	 - Contains the business logic
		 - Services
		 - Validators
	 - Point of access for the persistence layer
 - **Persistence**
	 - Responsible for fetching and persisting the data.

### How persistence is handled

- As explained above the single point of access for each repository method is in the management.
- The database structure and initial data is created with **Liquibase** using changesets in XML.
	- Each DDL operation has it's own changeSet
		- This prevents that a changeSet is "half-executed", requiring manual intervention.
- Hibernate is being used as ORM.
	- Entities have cascade operations configured.	
		- This may degrade performance on delete large amount of data in the N to N relations.
			- I considered using bulk delete with HQL queries, but I tought it was overkill for this project.
- For complex queries HQL is used.
	- Allows better control of what's being fetched (JOINS).
	- Allows prevention of multiple queries.
	- Needs to be used carefully to prevent unwanted cartesian joins.
- For simple repositories **CrudRepository** is used
	- Repositories can be created by  creating an interface extending CrudRepository.
	- This is perfect for simple cases.


## Frontend
For the frotend application I used Angular 8, Clarity design, ngrx and ngrx effects.
The models and services are generated from swagger using maven swagger codegen plugin.

### Modules and Components

 - **Core module**:
	 - Contains the core components
	 - **Components**:
		 - **header** - the navbar
 - **Shared module**
	 - Contains shared components and pipes
	 - **Components**:
		 - **modal-dialog:** Dialog box with 1 or 2 buttons
	 - **Pipes**:
		 - **join:** Join items of an array into a string with a custom separator
		 - **split**: Splits a string into an array
		 - **pluck**: Gets all values from a map with the specified key
 - **Truck-manager module**:
	 - Contains all the truck management components and triggers the store actions.
	 - **Components:**
		 - **Manager:** 
			 - the main component of this module.
			 - has the truck grid and a router-outlet
			 - **Components:**
				 - **Grid**: show the grid of trucks, allows selecting trucks to edition
				 - **Detail:** modal, shows the truck details and has buttons for edit/delete truck
					 - **color-item** and **text-item** represents a property of the truck
				 - **Create/edit**: form for creation/edition of the truck
					 - This component uses template driven forms binding to a model, it's not the best approach but I think that reactive forms may be overkill for this task.
 - **store**:
	 - This is not really a module, but a directory with the ngrx configurations
	 - **Actions**: express ngrx events
	 - **Effects**: specifies what action should the application take when a specific action occurs
	 - **Models**: the application state
	 - **Reducers**: handles the state changes, modifies the state


### Communication with the backend:
- The services and models are generated via the swagger codegen maven plugin in the backend.
- The services are called by the ngrx/effects, when a component wants some information from the service it should subscribe the store and trigger a Load action.

 

## Workflow
I used git for managing versions, I used that in order to save my work and do some experimentation if needed without compromising my current work.
Optimally I could use GitFlow, but I was alone on this and didn't care too much about that.



## Running the application
### Backend

 - In my development machine I used OpenJDK 11, but it should work on a
   Java 8 compatible JDK. 
 - Please make sure that the database is created with **liquibase**
   and that it is on the current directory when the application is executed.
   - If for some strange reason that database isn't created (should not happen), just run `mvn liquibase:update`
   - I provided a shell script for that (**backend-build-execute.sh**), but I was only able to
   test it on Linux.

### Frontend

 - The usual:
	 - make sure the backend runs on localhost:8080
	 - npm install
	 - ng serve

