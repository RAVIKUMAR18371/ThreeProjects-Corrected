# ThreeProjects (Reorganized & Fixed)

Three independent Spring Boot Maven modules that together form a small microservices
setup:

| Module            | Role                                   | Port | Depends on          |
|--------------------|-----------------------------------------|------|----------------------|
| `eureka-server`    | Service registry (Netflix Eureka)       | 8761 | -                    |
| `address-service`  | Manages employee addresses (PostgreSQL) | 8082 | `eureka-server`      |
| `employee-service` | Manages employees (PostgreSQL)          | 8081 | `eureka-server`      |

Run them in this order: **eureka-server → address-service → employee-service**
(each with `./mvnw spring-boot:run` from inside its folder, or `mvn spring-boot:run`
if you have Maven installed globally). Once all three are up, check
`http://localhost:8761` to confirm both services registered.

---

## What was wrong in the original zip, and what I changed

The three modules had gotten mixed up during development — folder names, package
names, and pom.xml identities didn't match what the code inside them actually did.
Some of these were just cosmetic, but a few would have stopped the apps from
starting or compiling at all. Here's the full list, module by module.

### 1. `eureka-server` (was `Address_service/Address_service`)
This folder's `pom.xml` was correctly set up as a Eureka server (right dependency,
right artifact id), but the Java code inside was a bare `AddressServiceApplication`
with no address logic and — critically — **no `@EnableEurekaServer` annotation**.
Without that annotation, this app would start as an ordinary empty Spring Boot app,
not a discovery server, no matter what was on the classpath.

Fixes:
- Renamed package `com.Address.Address_service` → `com.springboot.eurekaserver`
- Renamed class to `EurekaServerApplication` and added `@EnableEurekaServer`
- Fixed `spring.application.name` in `application.properties` (was `Address_service`)

### 2. `address-service` (was `detailed/detailed`)
This folder actually contained the real address logic (`AddressController`,
`Address` entity, `AddressRepository`, etc.), but its `pom.xml` was a straight
copy of the eureka-server's pom — wrong artifact id, and it depended on
`spring-cloud-starter-netflix-eureka-server` instead of the **client** starter.
It was also missing `spring-boot-starter-data-jpa`, the `postgresql` driver,
`modelmapper`, and `lombok` — all of which the code uses.

Two real code bugs on top of that:
- `AddressServiceImpl` had no `@Service` annotation, so Spring would never create
  it as a bean and `AddressController` would fail dependency injection at startup.
- `AddressController` called `addressService.updateAddresses(...)`, but that
  method didn't exist anywhere in `AddressServiceImpl` — this was a compile error.

Fixes:
- Renamed package `com.springboot.detailed` → `com.springboot.address`
- Renamed class to `AddressServiceApplication`
- Fixed `pom.xml`: artifact id/name → `address-service`, swapped to
  `eureka-client`, added the missing JPA/postgres/modelmapper/lombok dependencies
- Added `@Service` to `AddressServiceImpl`
- Implemented the missing `updateAddresses(...)` method (requires each address
  to already have an id; reuses the existing save/update helper)
- Added an `AddressService` interface (mirrors the pattern already used in
  employee-service) and had the controller depend on the interface, not the impl
- Removed a duplicate `spring.application.name` line in `application.properties`

### 3. `employee-service` (was `service/service`)
This was the most broken of the three. The main class `ServiceApplication` lived
in package `com.Employee.service`, but **every other class** (entity, repository,
service, exceptions) was still declared under `com.springboot.detailed.*` — leftover
from copy-pasting the address-service code. Since Spring Boot's component scan only
looks under the main class's package by default, none of those classes would ever
be picked up: no JPA entity registered, no repository bean, no service bean.

On top of that:
- `config/ModelMapperConfig.java` existed but was **completely empty** — no
  `ModelMapper` bean, which `EmployeeServiceImpl` needs in its constructor.
- There was no `EmployeeController` at all — the `controller` folder existed but
  had no file in it, so none of the employee endpoints were reachable over HTTP.

Fixes:
- Moved every class from `com.springboot.detailed.*` into `com.Employee.service.*`
  so component scanning actually finds them
- Renamed the `services` package folder to `service` so it matches the package
  declaration (`com.Employee.service.service`)
- Filled in `ModelMapperConfig` with a real `ModelMapper` bean
- Added `EmployeeController` with `POST /employees`, `PUT /employees/{id}`,
  `DELETE /employees/{id}`, `GET /employees/{id}`, `GET /employees`
- Fixed `spring.application.name` in `application.properties` (was just `service`)
- Cleaned up a leftover commented-out duplicate interface declaration in
  `EmployeeRepository`

### General housekeeping
- Flattened the double-nested folders (e.g. `detailed/detailed/...` → `address-service/...`)
- Replaced the hardcoded database password in `employee-service`'s
  `application.properties` with a `YOUR_PASSWORD` placeholder — don't commit real
  DB passwords to a repo; use environment variables or a secrets manager instead
- Every service now has a distinct, correct `spring.application.name` — this is
  the name each shows up as in the Eureka dashboard, and it's how one service
  would look up another via Eureka/Feign later

## Not changed
I didn't touch business logic beyond what was needed to make things compile and
run correctly (e.g. I didn't add validation annotations, security, or the
cross-service employee-existence check that's marked `// Todo` in
`AddressServiceImpl` — that's flagged in the code as planned for a Feign client
later, so I left it as-is).

## Before you run it
Both `address-service` and `employee-service` expect local PostgreSQL databases
(`address_db` and `employee_db`) and a real password in place of `YOUR_PASSWORD`
in each `application.properties`.
