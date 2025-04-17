# Usa una imagen oficial de Maven con Java 21
FROM maven:3.9-eclipse-temurin-21

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia todo el contenido del proyecto al contenedor
COPY . .

# Compila el proyecto, saltando los tests
RUN ./mvnw clean package -DskipTests

# Expone el puerto 8080 (puedes cambiarlo si tu app usa otro)
EXPOSE 8080

# Ejecuta la aplicación compilada
CMD ["java", "-jar", "target/inventarios-0.0.1-SNAPSHOT.jar"]
