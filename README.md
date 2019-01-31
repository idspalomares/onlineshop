1. You need to have:
    - Java 8
    - Maven
2. Go to 'shop' repository execute 'mvn clean install'
3. After build execute 'mvn spring-boot:run'
4. Login/Homepage: http://localhost:8080
5. http://localhost:8080/h2-console/ to access H2 Database
    - use this for JDBC url: jdbc:h2:mem:testdb