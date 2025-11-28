@echo off
echo ====================================
echo    ЗАПУСК AQA SHOP
echo ====================================
echo.
echo Запуск приложения с конфигурацией...
java -Dspring.config.location=application.properties -jar aqa-shop.jar
echo.
echo AQA Shop должен быть доступен на: http://localhost:8080
pause