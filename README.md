# Hotel Booking Service

A Java Spring Boot backend that powers searching hotel availability, managing room inventory, and reserving stays. The service exposes REST APIs, persists data with JPA, and keeps business rules in a modular domain layer so new features—pricing, notifications, payment hooks—can be layered on safely.

## Capabilities

- Manage hotels, rooms, room types, and nightly rates in a relational schema
- Query availability across cities and date ranges with occupancy filters
- Create, confirm, and cancel bookings while tracking guest profiles
- Provide validation and conflict detection on overlapping reservations
- Deliver integration-friendly responses with DTOs and versioned endpoints

## Tech Stack

- Java 17 + Spring Boot 3.4
- Spring Web MVC, Spring Data JPA, Bean Validation
- H2 for local development storage (swap for PostgreSQL/MySQL in staging)
- Maven build with reproducible wrapper scripts
- JUnit + Spring Test for service and controller coverage

## Project Layout

```
.
├── pom.xml                 # Parent build + dependency management
├── src/main/java/com/hotelbooking
│   ├── BookingServiceApplication.java
│   ├── config              # Configuration beans (Jackson, Swagger, etc.)
│   ├── domain              # Entities and value objects
│   ├── repository          # Spring Data repositories
│   ├── service             # Use cases and business rules
│   └── web                 # REST controllers + DTOs
├── src/main/resources
│   ├── application.yml     # Profiles and datasource config
│   └── db/migration        # Flyway migrations (planned)
└── src/test/java/com/hotelbooking
    └── ...                 # Unit + slice tests
```

## Near-Term Roadmap

1. Model hotels, rooms, and bookings entities with validation helpers
2. Implement availability search and reservation orchestration services
3. Wire REST controllers with DTOs, error handling, and request logging
4. Introduce persistence migrations and seed data for local dev
5. Add CI workflow plus containerized runtime definition

Each milestone will ship via incremental commits so the service can evolve while staying deployable.
