@echo off
echo ===================================
echo Starting Inventory System Application
echo ===================================

REM Check if Docker is running
docker info > nul 2>&1
if %errorlevel% == 0 (
    echo Docker is running. Starting with Docker...
    
    REM Build and start with Docker
    cd /d %~dp0
    cd javabackend\demo
    
    echo Building with Maven...
    call mvn clean package -DskipTests
    
    echo Building Docker image...
    docker build -t inventory-system:latest .
    
    echo Starting Docker container...
    docker run -d --name inventory-system -p 8090:8090 inventory-system:latest
    
    echo.
    echo Application started successfully!
    echo Access at: http://localhost:8090/api
) else (
    echo Docker is not running. Starting with Java...
    
    REM Start with Java directly
    cd /d %~dp0
    cd javabackend\demo
    
    echo Building with Maven...
    call mvn clean package -DskipTests
    
    echo Starting Spring Boot application...
    start java -jar -Dspring.profiles.active=dev target\demo-0.0.1-SNAPSHOT.jar
    
    echo.
    echo Application started successfully!
    echo Access at: http://localhost:8090/api
)

echo.
echo Press any key to close this window...
pause > nul