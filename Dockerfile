FROM openjdk:21
EXPOSE 8080
ADD target/wordOccurrenceAnalyzer-1.0.jar wordOccurrenceAnalyzer.jar
ENTRYPOINT ["java","-jar","/wordOccurrenceAnalyzer.jar"]