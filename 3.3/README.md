# Online Student Management System (Spring + Hibernate)

A minimal console-based project demonstrating:
- Dependency Injection with Spring (Java-based configuration)
- CRUD operations using Hibernate ORM
- Transaction management for fee payment and refund
- Integration of Spring + Hibernate with an H2 in-memory database

## Tech Stack
- Java 17
- Spring 5 (context, tx, orm)
- Hibernate 5
- H2 (in-memory DB)
- SLF4J simple logger
- Maven

## Project Structure
```
src/main/java/
  com/example/sms/
    MainApp.java                   # Demo runner
    config/AppConfig.java          # Spring & Hibernate configuration (Java config)
    entity/Student.java            # Student entity
    entity/FeeTransaction.java     # Fee transaction entity
    dao/StudentDao.java            # DAO interface
    dao/impl/StudentDaoImpl.java   # DAO implementation (Hibernate Session)
    service/StudentService.java    # Service interface
    service/impl/StudentServiceImpl.java  # Service with @Transactional methods
```

## Prerequisites
- Java 17 installed and on PATH (verify with `java -version`)
- Maven installed (verify with `mvn -v`)

## Build & Run
On Windows PowerShell (from the project root `d:\bt` or the cloned folder):

```bash
mvn -q clean compile
mvn -q exec:java
```

You should see logs showing:
- Students being created
- Payments and refunds applied in a single transaction
- CRUD updates and deletions

H2 is configured in-memory; data resets on each run.

## Notes
- Configuration is fully Java-based via `AppConfig` using `LocalSessionFactoryBean` and `HibernateTransactionManager`.
- Transactions are managed with `@Transactional` on service methods (fee payment/refund are atomic).
- Hibernate DDL auto is set to `update` for convenience. Adjust as needed.
