# Spring-Boot-Homework
# ERP System - Accounting Program

This project is a simple ERP System - Accounting Program developed using Java Spring Boot and Postman. The system is designed to manage customers, products, stocks, orders, and bills.

## Getting Started

### Prerequisites

- Java JDK 11 or higher version must be installed.
- Maven must be installed.
- PostgreSQL database must be installed.

### Installation

1. Clone this project from GitHub:
   git clone https://github.com/your-username/erp-muhasebe.git
2. Navigate to the project
   cd erp-muhasebe
3.  Create a database in PostgreSQL and update the database connection settings in `application.properties` file:yarlarını güncelleyin:
      spring.datasource.url=jdbc:postgresql://localhost:5432/erp_db
      spring.datasource.username=your-database-username
      spring.datasource.password=your-database-password
4. Compile the project:
mvn clean install
5. Run the project:
   mvn spring-boot:run
6. Test the APIs using a REST client like Postman.

### API Documentation

When the project is running, you can access the API documentation at the following URL:
http://localhost:8080/swagger-ui.html
This documentation contains all API endpoints, request and response examples, and parameters. You can use it to test the APIs without the need for a REST client like Postman.

## Contribution

If you would like to contribute to the project or report an issue, please submit a pull request or open an issue. Any contribution and feedback are appreciated.

## License

This project is licensed under the MIT License. For more information, see the [LICENSE](LICENSE) file.

## Author

- Full Name (email@example.com)

## Acknowledgments

This project was built using the following resources:

- [Spring Boot](https://spring.io/projects/spring-boot)
- [PostgreSQL](https://www.postgresql.org/)


