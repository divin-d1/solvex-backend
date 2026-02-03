````markdown
# SolveX
*Open Problems. Collective Solutions. Better Thinking.*

---

## Table of Contents

- [Overview](#overview)  
- [Features](#features)  
- [Architecture](#architecture)  
- [Technology Stack](#technology-stack)  
- [Getting Started](#getting-started)
- [Authentication](#authentication)   

---

## Overview

SolveX is an **open, community-driven problem-solving platform** where users can post real-world problems and collaboratively work on solutions.  

Key design principles:
- Open submission of problems without mandatory registration  
- Structured solution projects for clarity  
- Transparent learning and improvement of solutions  
- Simple, minimal interface focusing on problem-solving over social interaction  

---

## Features

- Public problem submission  
- Session-based user registration and login  
- Structured solution projects (planned)  
- Commenting and likes (planned)  
- Developer contributions for solution refinement  
- Simple text-based interface for clarity  

---

## Architecture

SolveX follows a **3-layer architecture**:

1. **Presentation Layer**  
   - REST APIs via Spring Boot  
   - JSON-based communication  

2. **Application Layer**  
   - Service classes handle business logic  
   - Controllers handle HTTP requests  

3. **Data Layer**  
   - PostgreSQL database  
   - Hibernate / JPA for ORM  

**Session-based authentication** is implemented via `HttpSession`, replacing JWT tokens for simplicity.  

---

## Technology Stack

- **Backend:** Java 17+, Spring Boot 3, Spring Web, Spring Data JPA, Spring Security (Session-based)  
- **Database:** PostgreSQL  
- **Tools:** Maven, Lombok, Postman, Git/GitHub  
- **Security:** BCrypt password hashing, role-based access  

---

## Getting Started

### Prerequisites

- Java 17+  
- PostgreSQL  
- Maven  
- IDE (IntelliJ / VSCode)  

### Setup

1. Clone the repository:

```bash
git clone https://github.com/divin-d1/solvex-backend.git
cd solvex
````

2. Create the PostgreSQL database:

```sql
CREATE DATABASE solvex;
```

3. Configure database connection in `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/solvex
    username: <Your username>
    password: <Your Password>

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

4. Build and run the project:

```bash
mvn spring-boot:run
```

Server runs on `http://localhost:8080`.

## Authentication

* Uses **HTTP session** (`JSESSIONID`)
* Sessions are stored in the database
* Session is created on login and reused for subsequent requests
* Protected endpoints require an active session


## License

MIT License © 2026 SolveX Team

> SolveX demonstrates how a **simple, open platform** can support meaningful collaboration and structured problem-solving while remaining extensible for real-world use.