FROM openjdk:15-oracle
ENV APP_ENV production
COPY application/target/universal/stage /app
ENTRYPOINT ["/app/bin/application"]
EXPOSE 8080
