### Practical task for Module 11&12

Using the provided skeleton, implement the REST service:
1. Run existing skeleton: 
    
        1.1 mvn clean install
        1.2 java -jar target/pt11_12-0.0.1-SNAPSHOT.jar
        
        2.1 In IDE just run PtApplication class

2. Check that the application is running. Open the link http://localhost:8080/pt11/ in your browser. You should see "Spring is here!".
3. Complete the implementation of the CarService class using the knowledge gained so that cars can be saved, found and updated in the database.    
4. Complete the implementation of the CarController to provide API which cover CRUD operations for a car object.

Useful link: https://spring.io/guides/gs/rest-service/

Should be used:
1. Spring Boot 2
2. Spring Data JPA    
3. Swagger
4. Jetty
5. log4j2

Should be done:
1. CRUD operations(on Controller and Repository layer)
2. Search Car by Colour API   
3. Store Enum 'Colour' in database as String
4. Document REST API with Swagger
5. Serialize/deserialize car's colour in enum 'Colour' using upper and lower case colour value
6. Implement Controller Advice
7. Use Console and File Appenders
8. Implement Logging in the REST service according to best practices
9. Implement Exception Handling in the REST service according to best practices
10. Validate Car
11. Change logging level in runtime using Spring Boot Actuator endpoints
12. In a separate branch/fork, use slf4j + log4j2 with modified config
        
        
  

