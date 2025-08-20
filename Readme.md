<!-- filepath: d:\Colleges Files\inventory-system\README.md -->
# 📦 Inventory Management System

A comprehensive inventory management system built with Spring Boot, PostgreSQL, and modern web technologies.

![Inventory System](https://img.shields.io/badge/Spring%20Boot-3.5.4-green)
![Java](https://img.shields.io/badge/Java-21-orange)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13+-blue)

## ✨ Features

- 📦 **Goods Management** - Add, edit, delete, and track inventory items
- 👥 **Staff Management** - Manage staff members and their roles
- 📥 **Goods Input** - Track incoming inventory with supplier details
- 📤 **Goods Output** - Monitor outgoing inventory with customer info
- 🏷️ **Category Management** - Organize items by categories
- 📊 **Dashboard** - Real-time statistics and overview
- 🔍 **Search & Filter** - Find items quickly
- 📱 **Responsive Design** - Works on desktop and mobile

## 🛠️ Tech Stack

### Backend
- **Spring Boot 3.5.4** - Application framework
- **Java 21** - Programming language
- **PostgreSQL** - Database
- **JPA/Hibernate** - ORM
- **Maven** - Build tool

### Frontend
- **HTML5 & CSS3** - Structure and styling
- **JavaScript** - Dynamic functionality
- **Bootstrap 5** - UI framework
- **Font Awesome** - Icons

### DevOps
- **Docker** - Containerization
- **Jenkins** - CI/CD pipeline
- **GitHub Actions** - Automated workflows

## 🚀 Quick Start

### Prerequisites
- Java 21 or higher
- PostgreSQL 13+
- Maven 3.6+
- Git

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/YOUR_USERNAME/inventory-management-system.git
cd inventory-management-system
```

2. **Setup PostgreSQL Database**
```sql
CREATE DATABASE inventory_db;
\c inventory_db;
\i src/main/resources/Inventory-System.sql
```

3. **Configure Database Connection**
Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/inventory_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

4. **Build and Run**
```bash
mvn clean install
mvn spring-boot:run
```

5. **Access the Application**
- Backend API: http://localhost:8000
- Frontend: Open `src/main/resources/static/index.html` in browser

## 📁 Project Structure

```
inventory-system/
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── controller/     # REST controllers
│   │   │   ├── entity/         # JPA entities
│   │   │   ├── repository/     # Data repositories
│   │   │   ├── service/        # Business logic
│   │   │   ├── dto/            # Data transfer objects
│   │   │   └── config/         # Configuration classes
│   │   └── resources/
│   │       ├── static/         # Frontend files
│   │       │   ├── css/        # Stylesheets
│   │       │   ├── js/         # JavaScript files
│   │       │   └── index.html  # Main page
│   │       └── application.properties
├── docker/                     # Docker configurations
├── Dockerfile
├── Jenkinsfile                 # CI/CD pipeline
└── README.md
```

## 🔌 API Endpoints

### Goods Management
- `GET /api/Goods` - Get all goods
- `POST /api/Goods` - Create new goods
- `PUT /api/Goods/{id}` - Update goods
- `DELETE /api/Goods/{id}` - Delete goods

### Staff Management
- `GET /api/Staff` - Get all staff
- `POST /api/Staff` - Create new staff
- `PUT /api/Staff/{id}` - Update staff
- `DELETE /api/Staff/{id}` - Delete staff

### Input/Output Tracking
- `GET /api/GoodsIn` - Get input records
- `POST /api/GoodsIn` - Create input record
- `GET /api/GoodsOut` - Get output records
- `POST /api/GoodsOut` - Create output record

### Categories
- `GET /api/Category` - Get all categories
- `POST /api/Category` - Create category
- `DELETE /api/Category/{id}` - Delete category

## 🐳 Docker Setup

1. **Build Docker image**
```bash
docker build -t inventory-system .
```

2. **Run with Docker Compose**
```bash
docker-compose up -d
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Your Name**
- GitHub: [@Reno-Aosp](https://github.com/Reno-Aosp)


## 🙏 Acknowledgments

- Spring Boot team for the amazing framework
- Bootstrap team for the UI components
- PostgreSQL team for the robust database

---

⭐ **Star this repository if it helped you!**


halo guys peh