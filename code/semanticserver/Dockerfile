FROM openjdk:8-jdk-alpine
EXPOSE 8080

COPY . /usr/app/semantic-server

WORKDIR /usr/app/semantic-server

#RUN apt update
#RUN apt -y install openjdk-8-jdk
#RUN ./wait-for-it.sh postgres:5432 -- ./mvnw clean install
RUN ls
RUN ./mvnw clean install -Dmaven.test.skip=true 
#RUN ./mvnw clean install 
#RUN cd target
CMD ["java","-jar","target/semanticserver-0.0.1-SNAPSHOT.jar"]
