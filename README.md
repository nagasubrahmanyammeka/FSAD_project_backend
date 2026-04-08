# AgriConnect Spring Boot Backend

## ✅ Complete Backend for AgriConnect Agriculture Platform

---

## 📁 Project Structure

```
backend/
├── pom.xml
└── src/main/
    ├── resources/
    │   └── application.properties
    └── java/com/agriconnect/
        ├── AgriConnectApplication.java
        ├── config/
        │   ├── DataSeeder.java          ← Seeds default users & products on startup
        │   ├── ModelMapperConfig.java   ← ModelMapper bean
        │   ├── SecurityConfig.java      ← JWT + CORS + Spring Security
        │   └── SwaggerConfig.java       ← OpenAPI / Swagger UI
        ├── controller/
        │   ├── AuthController.java      ← /api/auth/**
        │   ├── AdminController.java     ← /api/admin/**
        │   ├── UserController.java      ← /api/users/**
        │   ├── FeedbackController.java  ← /api/feedbacks/**
        │   ├── GuidanceController.java  ← /api/guidance/**
        │   ├── ContentController.java   ← /api/content/**
        │   └── ProductController.java   ← /api/products/**
        ├── dto/
        │   ├── UserDTO.java
        │   ├── RegisterRequestDTO.java
        │   ├── LoginRequestDTO.java
        │   ├── AuthResponseDTO.java
        │   ├── FeedbackDTO.java
        │   ├── GuidanceDTO.java
        │   ├── ContentDTO.java
        │   ├── ProductDTO.java
        │   └── AdminStatsDTO.java
        ├── entity/
        │   ├── User.java
        │   ├── Feedback.java
        │   ├── Guidance.java
        │   ├── Content.java
        │   └── Product.java
        ├── exception/
        │   ├── GlobalExceptionHandler.java
        │   ├── ResourceNotFoundException.java
        │   ├── UserAlreadyExistsException.java
        │   └── InvalidCredentialsException.java
        ├── repository/
        │   ├── UserRepository.java
        │   ├── FeedbackRepository.java
        │   ├── GuidanceRepository.java
        │   ├── ContentRepository.java
        │   └── ProductRepository.java
        ├── security/
        │   ├── JwtUtil.java             ← Token generation & validation
        │   └── JwtAuthFilter.java       ← Bearer token filter
        └── service/
            ├── CustomUserDetailsService.java
            ├── AuthService.java
            ├── AdminService.java
            ├── UserService.java
            ├── FeedbackService.java
            ├── GuidanceService.java
            ├── ContentService.java
            └── ProductService.java
```

---

## 🚀 How to Run in Spring Tool Suite (STS)

### Step 1 — Prerequisites
- ✅ Java 17 installed
- ✅ MySQL running on port **3306**
- ✅ Database `mydb` created

```sql
CREATE DATABASE mydb;
```

### Step 2 — Import project in STS
1. Open **Spring Tool Suite 4**
2. Go to **File → Import → Maven → Existing Maven Projects**
3. Browse to: `FSAD_Project-main/backend`
4. Click **Finish** and wait for Maven to download dependencies

### Step 3 — Run the application
- Right-click `AgriConnectApplication.java` → **Run As → Spring Boot App**
- Server starts on: **http://localhost:2026**

### Step 4 — Open Swagger UI
```
http://localhost:2026/swagger-ui.html
```
- Click **Authorize** → paste a JWT token to test secured endpoints

---

## 🔐 Default Seeded Users (auto-created on first start)

| Username  | Password   | Role   |
|-----------|------------|--------|
| admin     | admin123   | admin  |
| farmer1   | farmer123  | farmer |
| expert1   | expert123  | expert |
| public1   | public123  | public |

---

## 📡 API Endpoints

### Auth — `/api/auth`
| Method | URL                  | Auth | Description          |
|--------|----------------------|------|----------------------|
| POST   | `/api/auth/register` | ❌   | Register new user    |
| POST   | `/api/auth/login`    | ❌   | Login, get JWT token |
| GET    | `/api/auth/me`       | ✅   | Get current user     |

### Admin — `/api/admin` *(admin role)*
| Method | URL                     | Description        |
|--------|-------------------------|--------------------|
| GET    | `/api/admin/stats`      | User statistics    |
| GET    | `/api/admin/users`      | List all users     |
| DELETE | `/api/admin/users/{id}` | Delete any user    |

### Users — `/api/users` *(authenticated)*
| Method | URL              | Description           |
|--------|------------------|-----------------------|
| GET    | `/api/users/{id}`| Get user profile      |
| PUT    | `/api/users/{id}`| Update profile        |
| DELETE | `/api/users/{id}`| Delete own account    |

### Feedback — `/api/feedbacks`
| Method | URL              | Auth | Description        |
|--------|------------------|------|--------------------|
| POST   | `/api/feedbacks` | ❌   | Submit feedback    |
| GET    | `/api/feedbacks` | ❌   | View all feedbacks |

### Guidance — `/api/guidance`
| Method | URL             | Auth | Description         |
|--------|-----------------|------|---------------------|
| POST   | `/api/guidance` | ❌   | Submit request      |
| GET    | `/api/guidance` | ❌   | View all guidance   |

### Content — `/api/content`
| Method | URL                  | Auth | Description          |
|--------|----------------------|------|----------------------|
| GET    | `/api/content`       | ❌   | View all content     |
| POST   | `/api/content`       | ✅   | Upload new content   |
| DELETE | `/api/content/{id}`  | ✅   | Delete content       |

### Products — `/api/products`
| Method | URL                    | Auth | Description       |
|--------|------------------------|------|-------------------|
| GET    | `/api/products`        | ❌   | List all products |
| GET    | `/api/products/{id}`   | ❌   | Get one product   |
| POST   | `/api/products`        | ✅   | Add product       |
| PUT    | `/api/products/{id}`   | ✅   | Update product    |
| DELETE | `/api/products/{id}`   | ✅   | Delete product    |

---

## 🔒 Security Features
- **JWT** — `io.jsonwebtoken` (JJWT 0.11.5) — HS256 signed tokens
- **BCrypt** — password hashing
- **Stateless sessions** — no HTTP sessions, pure JWT
- **Role-based access** — `admin`, `farmer`, `expert`, `public`
- **Global Exception Handling** — 400, 401, 404, 409, 500
- **DTOs** — all entities mapped via `ModelMapper`
- **Swagger UI** — full OpenAPI 3 documentation at `/swagger-ui.html`
- **CORS** — configured for `localhost:5173`, `3000`, `5000`
- **OAuth2 Google** — optional, configured in `application.properties`

---

## ⚙️ application.properties Summary

```properties
spring.application.name=SpringSecurityBackendDemo
server.port=2026
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=root@123
spring.jpa.hibernate.ddl-auto=update
jwt.secret=<base64-encoded-key>
jwt.expiration=86400000  # 24 hours
```
"# FSAD_project_backend" 
