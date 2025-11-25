# ETAPA 1: BUILD
FROM alpine:latest as build

# 1. Instalamos Java 17 y 'dos2unix' (crucial si vienes de Windows)
RUN apk update && apk add openjdk17 dos2unix

WORKDIR /app
COPY . .

# 2. Convertimos el script gradlew al formato Unix y le damos permisos
# Esto arregla el error de "standard_init_linux.go: exec user process caused: no such file or directory"
RUN dos2unix ./gradlew && chmod +x ./gradlew

# 3. Compilamos usando tu versión de Gradle (se descargará automáticamente)
RUN ./gradlew bootJar --no-daemon -x test

# ETAPA 2: RUNTIME
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
EXPOSE 8080

# Buscamos cualquier JAR generado en la carpeta libs
COPY --from=build /app/build/libs/*.jar ./app.jar

ENTRYPOINT java -jar app.jar