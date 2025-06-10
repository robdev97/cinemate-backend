# CineMate – Backend (REST API)

CineMate to aplikacja wspierająca wspólne oglądanie filmów. Projekt składa się z dwóch oddzielnych aplikacji: frontendowej (Vaadin) i backendowej (REST API).

👉 Repozytorium aplikacji frontendowej (Vaadin):  
https://github.com/robdev97/cinemate-frontend.git

---

## 🔧 Jak uruchomić projekt

### Wymagania:
- Java 21+
- Gradle
- MySQL (lub inna baza zgodna z konfiguracją)

### Konfiguracja build.gradle
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.5'
    id 'io.spring.dependency-management' version '1.1.4'
}

group = 'com.cinemate'
version = '0.0.1-SNAPSHOT'

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'com.mysql:mysql-connector-j'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    implementation 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}

tasks.named('test') {
    useJUnitPlatform()
}

### Krok po kroku:
1. Sklonuj repozytorium:
git clone https://github.com/robdev97/cinemate-backend.git

2. W pliku `application.properties` uzupełnij dane do bazy danych MySQL.
spring.application.name=cinemate-backend

spring.datasource.url=jdbc:mysql://localhost:3306/cinemate?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=cinemate_project
spring.datasource.password=cinemate_pass123
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080

4. Zbuduj i uruchom projekt:
./gradlew bootRun
6. Backend będzie dostępny pod adresem:  
`http://localhost:8080`

---

## 📦 Technologie:
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Lombok
- REST API
- Gradle

---

## ✍️ Autor:
Robert – uczestnik bootcampu Kodilla Java Developer
