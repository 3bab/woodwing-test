FROM java:openjdk-8-jre
ADD woodwing-test-0.0.1-SNAPSHOT.jar demo.jar
ENTRYPOINT ["java","-jar","/demo.jar"]