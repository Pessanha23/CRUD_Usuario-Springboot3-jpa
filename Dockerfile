FROM openjdk

COPY target/course_Login-0.0.1-SNAPSHOT.jar course_Login.jar

ENTRYPOINT ["java", "-jar", "course_Login.jar"]