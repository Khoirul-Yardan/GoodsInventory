# Inventory System

A comprehensive inventory management system built with Spring Boot backend and HTML/CSS/JavaScript frontend.

## Project Structure

```
inventory-system/
├── javabackend/
│   └── demo/
│       ├── src/
│       │   ├── main/
│       │   │   ├── java/com/example/demo/
│       │   │   │   ├── controller/
│       │   │   │   │   ├── GoodsController.java
│       │   │   │   │   ├── GoodsInputController.java
│       │   │   │   │   └── GoodsOutputController.java
│       │   │   │   ├── entity/
│       │   │   │   ├── repository/
│       │   │   │   └── DataTransferObjectDTO/
│       │   │   └── resources/
│       │   │       ├── static/
│       │   │       │   ├── Dashboard.html
│       │   │       │   ├── Goods.html
│       │   │       │   ├── GoodsIn.html
│       │   │       │   ├── GoodsOut.html
│       │   │       │   ├── Login.html
│       │   │       │   └── *.css files
│       │   │       └── application.properties
│       │   └── test/
│       └── pom.xml
```

## Features

- **Goods Management**: Add, edit, delete, and view goods/products
- **Inventory Tracking**: Track goods in and goods out transactions
- **Dashboard**: Overview of inventory status
- **User Management**: Staff and login functionality
- **Categories & Storage**: Organize goods by categories and storage locations
- **Transaction History**: View all inventory movements

## Technologies Used

### Backend
- **Spring Boot** - Java framework for REST API
- **Spring Data JPA** - Database access layer
- **PostgreSQL** - Database
- **Maven** - Dependency management

### Frontend
- **HTML5** - Structure
- **CSS3 & Bootstrap 5** - Styling and responsive design
- **JavaScript** - Client-side functionality
- **Font Awesome** - Icons

## Getting Started

### Prerequisites
- Java 11 or higher
- Maven 3.6+
- PostgreSQL database

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd inventory-system
   ```

2. **Navigate to the backend directory**
   ```bash
   cd "javabackend/demo"
   ```

3. **Configure database**
   - Create a PostgreSQL database
   - Update `src/main/resources/application.properties` with your database credentials

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the application**
   - Backend API: `http://localhost:8000/api`
   - Frontend: `http://localhost:8000`

## API Endpoints

### Goods Management
- `GET /api/Goods` - Get all goods
- `GET /api/Goods/{id}` - Get goods by ID
- `POST /api/Goods` - Create new goods
- `PUT /api/Goods/{id}` - Update goods
- `DELETE /api/Goods/{id}` - Delete goods

### Goods Input (Stock In)
- `GET /api/GoodsIn` - Get all goods input records
- `POST /api/GoodsIn` - Record new goods input

### Goods Output (Stock Out)
- `GET /api/GoodsOut` - Get all goods output records
- `POST /api/GoodsOut` - Record new goods output

## Database Configuration

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

Apache License 2.0
