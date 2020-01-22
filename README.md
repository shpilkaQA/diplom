# Дипломный проект профессии «Тестировщик ПО»

Дипломный проект представляет собой автоматизацию тестирования комплексного сервиса, 
взаимодействующего с СУБД и API Банка.

## Документы:

в процессе

## Инструкция по запуску:

**для Windows 7**

1. Скопировать текущий репозиторий
1. Из папки с репозиторием запустить контейнеры
    ```
    docker-compose up -d
    ```
3. Запустить SUT
    * ***Для работы с MySQL***
   
    ```
    java -Dspring.datasource.url=jdbc:mysql://192.168.99.100:3306/app -jar artifacts/aqa-shop.jar
    ```
    
    * ***Для работы с Postgres***
   
    ```
    java -Dspring.datasource.url=jdbc:postgresql://192.168.99.100:5432/app -jar artifacts/aqa-shop.jar
   ```
  
1. Запустить тесты
    * ***Для работы с MySQL***
       
    Запуск в mysql установлен по умолчанию
    ```
    gradlew test
    ```
    
    * ***Для работы с Postgres***
    
    ```
    gradlew test -Ddb.url=jdbc:postgresql://192.168.99.100:5432/app
    ```
    
1. Для формирования отчета Allure и его открытия в браузере выполнить команду
    ```
    gradlew allureReport
    gradlew allureServe
    ```

1. Остановить контейнеры
    ```
    docker-compose stop
    ```
    или остановить и удалить контейнеры
    ```
    docker-compose down
    ```