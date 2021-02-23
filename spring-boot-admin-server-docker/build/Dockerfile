FROM hx/xu-jdk8

ENV JAVA_OPTS=" -Xms128M -Xmx256M "

ADD spring-boot-admin-docker-1.0.jar spring-boot-admin-docker-1.0.jar
EXPOSE 8011
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /spring-boot-admin-docker-1.0.jar" ]
