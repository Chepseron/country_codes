# country_codes

The application works with the following tools 
* Tomcat 7
* Java 8
* Maven 
* Docker 19.03

# How to execute the project #
## Using the IDE(netbeans) ##

* Clone the project from https://github.com/Chepseron/country_codes.git
* Ensure you have java 8 installed, if not then follow the instructions at https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html
* You can open your project with any IDE of your choice, Eclipse, Netbeans, Intellij etc
* Proceed to install project dependencies by, Right clicking on the project and then select build with dependencies, this is in the case you are using netbeans
* Once you have installed the dependencies, right click on the project and then choose Run.
* On your browser type in http://localhost:8080 

## Using docker ##

navigate to the project directory, with the filename Dockerfile and execute the following to build an image 
docker build −t <image−name> .

execute the following to run the docker container 
docker run <image−name>

for further instructions on how to execute the same you can find them at the following link https://www.tutorialspoint.com/working-with-java-inside-docker-container
