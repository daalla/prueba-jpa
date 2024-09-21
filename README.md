docker run -p3306:3306 
--name prueba-jpa 
-e MYSQL_ROOT_PASSWORD=1234 
-e MYSQL_DATABASE=apis 
-d mysql:8.0