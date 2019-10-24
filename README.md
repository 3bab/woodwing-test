# Gintas' demo application for WoodWing


##### Description
This application based on Spring Boot 2 and Gradle 4.
Implemented according to the task requirements.

##### The plan of attack (steps)
* setup spring boot app with dependencies
* build folder structure for services, domain entities and controllers
* set up logback logging
* setup domain classes for request and response entities
* setup API controller
* setup service to handle the calculation
* implement unit tests for controller
* implement unit tests for service
* test the functionality with API client
* fine-tuning and bug fixing if any
* add docker functionality
* test with docker 
* modify readme
 
##### The approach description:
* Even though on the paper this task looks very simple, it has few very important elements that require more attention to details.
I chose to implement REST API endpoint that returns the sum based on the payload. The first important questions to answer - 
which request type to choose? GET is not suitable because we don't get any resource from the server. Nothing is saved or cached in any way. POST is not suitable because we are not trying to save anything on the server. I want the application to act as a
digital calculator without any persistence. PUT looks more promising that others because of the idempotence problem. Every request from any number of clients will return the 
same result.
However it's also not very "pretty" solution because we
are not trying to update any resource on the server. Nevertheless, I chose to go with PUT.
* To me the most suitable way to pass request values is json payload. It's easy to work with for the client. Also easy to work with in the code. Example:
```
{
    "summand_one_value":10,
    "summand_one_type":"yards",
    "summand_two_value":10,
    "summand_two_type":"meters",
    "sum_type":"meters"
}
```
The type can be either "yards" or "meters". The value is integer.
* The result is in json format. Example:
```
{
    "sum":22.0
}
```
* There is no persistence layer. So nothing is saved into DB or deliberately cached on the server. I tried to make it simple input to output
function just like digital calculator.
* I paid a lot of attention to request validation. In this case, any negative distance (summand) or unrecognized unit type will trigger an
exception which is returned as 400 BAD REQUEST to the client. It's also logged.
* Distance is returned as float number with precision that can be configured in application.properties. Default value is 2 decimals.
* I paid a lot of attention to testability. Test coverage is not 100% but close to that.
* There is no authentication or authorization on the API layer.
* I added docker file. Easy to run and test dockerized.

##### What can be improved:
Adding authentication would make the API more production-ready.
Current implementation extensibility is limited. For example, if new units types are added, such as mile and inch, the number of conversion functions would increse from 2 to 12 (fromInchToMile, fromMeterToYard, fromMileToYard, ...etc). That would make the code quite difficult to read and maintain. In that case, applying some design pattern would be handy. Chain of responsibility comes first from the top my head but there might be better ones.


### Prerequisites
Gradle 4.x. and Java 8


### Build
```
gradle build
```

### Test
```
gradle test
```

### Run
Either as jar package or docker container. Dockerfile is provided. With docker use:
```
docker run -p 8080:8080 <image>
```

### API endpoint
```
http://<server>:8080/sum PUT

{
    "summand_one_value":<integer>,
    "summand_one_type":"<type>",
    "summand_two_value":<integer>,
    "summand_two_type":"<type>",
    "sum_type":"<type>"
}
```

### Built With

* [Spring](https://spring.io) - The application framework
* [Gradle](https://gradle.org/) - Dependency management and build tool
* [Docker](https://docker.org/) - Virtualization software