@echo off
echo ===================================
echo Stopping Inventory System Application
echo ===================================

REM Check if Docker is running
docker info > nul 2>&1
if %errorlevel% == 0 (
    echo Checking for Docker container...
    docker ps | findstr inventory-system > nul
    if %errorlevel% == 0 (
        echo Stopping Docker container...
        docker stop inventory-system
        docker rm inventory-system
        echo Docker container stopped and removed.
    ) else (
        echo No Docker container found running.
    )
)

REM Try to find and kill Java process
echo Checking for Java process...
for /f "tokens=1" %%i in ('jps -l ^| findstr demo-0.0.1-SNAPSHOT.jar') do (
    echo Stopping Java process with PID: %%i
    taskkill /F /PID %%i
    echo Java process stopped.
    goto :found
)

echo No Java process found running.
:found

echo.
echo Application stopped successfully!
echo.
echo Press any key to close this window...
pause > nul