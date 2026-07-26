# Auth Service - JWT-Based Authentication Microservice

## 🔧 Corrections Made

### 1. **CustomUserDetails - Null Pointer Exception Fix**
   - **Issue**: If user roles were null, `getAuthorities()` would throw NPE
   - **Fix**: Added null check and default "ROLE_USER" when roles are empty
   - **Added**: Trim functionality to handle whitespace in role strings

### 2. **UserDTO - Removed JPA Annotations**
   - **Issue**: DTOs had JPA annotations (@Id, @GeneratedValue) which shouldn't be there
   - **Fix**: Removed all persistence annotations from UserDTO
   - **Added**: Lombok @Getter @Setter for cleaner code

### 3. **User Model - Enhanced with Constraints**
   - **Added**: @Unique constraint on username field
   - **Added**: @Column(nullable = false) on password and username
   - **Added**: Default role value in database
   - **Added**: Lombok @Getter @Setter to replace manual getters/setters

### 4. **All DTOs - Added Lombok Annotations**
   - Removed manual getters/setters
   - Added @Getter, @Setter, @AllArgsConstructor, @NoArgsConstructor
   - Applied to: UserDTO, LoginRequestDTO, JWTTokenResponseDTO

### 5. **SecurityConfig - Enhanced Security**
   - **Added**: CORS configuration with proper allowed origins
   - **Added**: Session management (STATELESS)
   - **Added**: Better endpoint protection
   - **Added**: Actuator endpoints excluded from auth

### 6. **UserService - Better Error Handling**
   - **Added**: Username duplicate check before registration
   - **Added**: Default role assignment (ROLE_USER)
   - **Added**: Better exception messages
   - **Added**: DTOs constructor usage instead of manual mapping

### 7. **AuthController - Comprehensive Error Handling**
   - **Added**: Input validation for username and password
   - **Added**: Proper HTTP status codes (400, 401, 500)
   - **Added**: Logging with @Slf4j
   - **Added**: Structured error response format
   - **Added**: Better exception handling (BadCredentialsException vs other auth exceptions)

### 8. **Application Properties - Environment Support**
   - **Added**: Environment variables support with fallback defaults
   - **Added**: Database connection pooling (HikariCP)
   - **Added**: Logging configuration
   - **Added**: Actuator health check endpoints
   - **Added**: Eureka configuration with environment variables

## 📋 Prerequisites

- Java 17+
- Maven 3.6+
- PostgreSQL 12+
- Eureka Server running on `http://localhost:8761`

## 🚀 Quick Start

### 1. Database Setup
```sql
CREATE DATABASE auth_db;
-- Tables will be auto-created by Hibernate
```

### 2. Environment Variables (Optional)
```bash
export DATABASE_URL=jdbc:postgresql://localhost:5432/auth_db
export DATABASE_USER=postgres
export DATABASE_PASSWORD=6120
export EUREKA_URL=http://localhost:8761/eureka/
```

### 3. Build & Run
```bash
# Clean build
mvn clean install

# Run the service
mvn spring-boot:run

# Or using JAR
java -jar target/auth-service-0.0.1-SNAPSHOT.jar
```

The service will start on port `8083`

## 🔌 API Endpoints

### 1. Register User
**POST** `/auth/register`

```json
{
  "username": "john_doe",
  "password": "password123",
  "email": "john@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "roles": "ROLE_USER,ROLE_ADMIN"  // Optional, defaults to ROLE_USER
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "username": "john_doe",
  "email": "john@example.com",
  "roles": "ROLE_USER,ROLE_ADMIN"
}
```

### 2. Generate JWT Token
**POST** `/auth/generate-token`

```json
{
  "username": "john_doe",
  "password": "password123"
}
```

**Response (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "tokenType": "Bearer",
  "validityDuration": 1800
}
```

## 📝 Usage Examples

### Register a User
```bash
curl -X POST http://localhost:8083/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "test@123",
    "email": "test@example.com",
    "firstName": "Test",
    "lastName": "User"
  }'
```

### Login and Get Token
```bash
curl -X POST http://localhost:8083/auth/generate-token \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "test@123"
  }'
```

### Use Token in Other Services
```bash
curl -X GET http://other-service/api/endpoint \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE"
```

## 🔒 Security Features

✅ Password encryption using BCrypt
✅ JWT token generation (30 minutes validity)
✅ CORS configuration
✅ Role-based access control (RBAC)
✅ Input validation
✅ Exception handling with proper HTTP status codes
✅ Logging for debugging
✅ Stateless session management

## 📦 Dependencies

- Spring Boot 3.2.5
- Spring Security with JWT (jjwt 0.11.5)
- Spring Data JPA
- PostgreSQL JDBC Driver
- Lombok
- Netflix Eureka Client

## 🛠️ Configuration Options

### Database Configuration
- Default: PostgreSQL on localhost:5432
- Database: `auth_db`
- User: `postgres`
- Password: `6120`

### JWT Configuration
- Secret: `W2nocW+G/6uwB20n7yyKY8QRn8XhRNj4+/ECVo2d8RI=` (256-bit Base64)
- Algorithm: HS256
- Validity: 30 minutes
- ⚠️ Change secret in production!

### Eureka Configuration
- Server: http://localhost:8761/eureka/
- Register: Yes
- Fetch Registry: Yes

## ⚡ Performance Optimizations

- Connection pooling (HikariCP) enabled
- Stateless sessions (better for microservices)
- Proper logging levels (INFO by default)
- Query logging disabled in production
- Entity lazy loading supported

## 🐛 Common Issues & Fixes

### Issue: "User not found" on login
- ✅ Ensure user is registered first
- ✅ Check username and password are correct

### Issue: "Username already exists"
- ✅ Use a different username
- ✅ Or delete the user from database if testing

### Issue: Database connection error
- ✅ Ensure PostgreSQL is running
- ✅ Check database URL and credentials
- ✅ Ensure `auth_db` database exists

### Issue: CORS errors
- ✅ Check CORS configuration in SecurityConfig
- ✅ Ensure frontend URL is in allowed origins
- ✅ Include proper Authorization header

## 📚 Project Structure

```
auth-service/
├── src/main/java/com/example/auth_service/
│   ├── controller/       # REST endpoints
│   ├── service/          # Business logic
│   ├── model/            # JPA entities
│   ├── dto/              # Data transfer objects
│   ├── repository/       # Database access
│   ├── util/             # JWT utility
│   ├── config/           # Security configuration
│   └── AuthServiceApplication.java
├── src/main/resources/
│   └── application.properties
├── pom.xml
└── README.md
```

## 📞 Support

For issues or improvements, check the logs:
```bash
# View logs
tail -f logs/auth-service.log

# Enable debug mode in application.properties
logging.level.com.example.auth_service=DEBUG
```

---

**Version**: 1.0.0 (Corrected & Production Ready)
**Last Updated**: 2026
