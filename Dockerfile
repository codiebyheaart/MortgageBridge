FROM registry.access.redhat.com/ubi8/openjdk-17:1.18

# Copy the source code
COPY . /tmp/src

# Build the application using maven
USER root
RUN chown -R 1001:0 /tmp/src
USER 1001
RUN cd /tmp/src && mvn clean package -DskipTests && \
    cp target/homeMortgages-0.0.1-SNAPSHOT.jar /deployments/app.jar && \
    rm -rf /tmp/src

# Start the application
CMD ["java", "-jar", "/deployments/app.jar"]
