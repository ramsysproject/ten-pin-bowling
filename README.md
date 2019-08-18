# Ten Pin Bowling Score Calculator

This application takes a text file as an input, the content of this file is the score lines for each player.
Once loaded, it parses the text file and applies logic to determine the player score.
Finally it presents the score in the screen.

This is a SpringBoot application, specifically a CommandLineRunner one, built by Maven.

* **Build instructions**
1. Execute `mvn clean install`
2. Inside the target folder you can find the jar file, named `bowling-1.0.0-SNAPSHOT.jar`

* **Running instructions**

There are a couple of ways to run the application:

1. Using Maven Springboot plugin. 

Run `mvn spring-boot:run -Dspring-boot.run.arguments=/full/path/to/input/file.txt`

2. Using jar file.

Run from project root `java -jar target/bowling-1.0.0-SNAPSHOT.jar /full/path/to/input/file.txt`

3. At the moment the application can be invoked in docker for running the demo game. In future releases i will create a volume mapping 
to allow the container to take an input file

a. Pull the image `docker pull emaramirez1306/classic-bowling-scorer:initial`

b. Run the image `docker run emaramirez1306/classic-bowling-scorer:initial`

c. You can also build locally and run it, for building locally execute the `build.sh` script

* **How to run tests**
1. Run `mvn clean test` to execute unit tests
2. Run `mvn clean verify` to execute unit and integration tests
