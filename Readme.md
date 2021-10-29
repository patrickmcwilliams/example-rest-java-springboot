#Crowdstreet Takehome Backend

This is a simple project created with Springboot and uses an in-memory H2 database. It is configured and should be ready to go without any local DB setup. Keep in mind that since its an in-memory DB, it will wipe each time you restart the server. 

Init scripts for the DB are found under `src/main/resources/data.sql`. This is where you will set up your tables, and any other SQL setup you need. 

H2 also comes with a built-in console that works in browser. Navigate to `localhost:8080/h2-console` to access it. Be sure to replace the default connection URL to `jdbc:h2:mem:testdb `. 

You will find a folder called "Count" that contains simple boilerplate code that shows how a post route and get route will work. The default URL for a local Springboot instance is `localHost:8080`.  For more information on Springboot's web framework, go visit their site [here](https://spring.io/projects/spring-framework)