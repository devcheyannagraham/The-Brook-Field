# BFIMS

The Brook & Field Inventory Management System is a full-stack inventory and retail management application for handling books, publications, accessories, customers, orders, and basic reporting. It was created as my capstone project, bringing together a modern Spring Boot backend and an Angular frontend in one working system.

## What it does

The application lets users:

- manage publications and inventory items
- track accessories and stock levels
- create and review customer orders
- browse a shop-style interface for purchasing or renting items
- view simple reports such as popular items and recent orders
- access different admin and user-facing workflows

## Why it was created

The project was built to demonstrate a complete end-to-end web application with real business-style workflows rather than a simple demo. It combines data modeling, REST APIs, UI state, authentication-aware routes, and seeded sample data so it can be explored locally without extra setup.

## What went into building it

The app uses:

- Java 21 and Spring Boot for the backend API and business logic
- Spring Data JPA with an H2 database for local development
- Angular 19 for the frontend interface
- JUnit tests for backend logic
- Playwright-based tests for UI flows

The backend also seeds example data on startup, so a fresh local run already has sample content to explore.

## Getting started

### Prerequisites

Make sure you have:

- Java 21 or newer
- Node.js and npm
- Git

### Run the backend

From the project root:

```bash
./mvnw -DskipTests spring-boot:run
```

The app uses Spring profiles to choose its configuration. The active profile is set in [src/main/resources/application.properties](src/main/resources/application.properties).

### Spring profiles

The active profile is selected in [src/main/resources/application.properties](src/main/resources/application.properties).

- Use the development profile by setting `spring.profiles.active=dev` to load settings from [src/main/resources/application-dev.properties](src/main/resources/application-dev.properties).
- Use the production profile by setting `spring.profiles.active=prod` to load settings from [src/main/resources/application-prod.properties](src/main/resources/application-prod.properties).

Only change values in the profile-specific files when they need to differ from the other environment. Common settings to review are:

- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`
- `server.port`
- `spring.jpa.hibernate.ddl-auto`

The production file also uses the Heroku-style `PORT` environment variable for the web port.

The API will start on http://localhost:8080.

### Run the frontend

In a second terminal:

```bash
cd frontend
npm install
npm start
```

The frontend will be available at http://localhost:4200.

### Default admin account

The app seeds a default admin user on first startup. A local login option is available through the bootstrap data, which makes it easier to explore the admin pages right away.

Default seeded admin credentials:

- Email: bfadmin@mail.com
- Password: bfadministrator

You can change these values in [src/main/java/demo/bfims/Config/Bootstrap.java](src/main/java/demo/bfims/Config/Bootstrap.java) if you want to use different local test credentials.

