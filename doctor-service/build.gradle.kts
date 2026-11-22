import com.google.protobuf.gradle.id

plugins {
    java
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("com.google.protobuf") version "0.9.6"
}

group = "com"
version = "0.0.1-SNAPSHOT"
description = "doctor-service"





dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-grpc-client")
    implementation("org.springframework.boot:spring-boot-starter-grpc-server")
    implementation("org.springframework.boot:spring-boot-starter-kafka")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-security-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")

    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-actuator-test")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
    testImplementation("org.springframework.boot:spring-boot-starter-grpc-client-test")
    testImplementation("org.springframework.boot:spring-boot-starter-grpc-server-test")
    testImplementation("org.springframework.boot:spring-boot-starter-kafka-test")
    testImplementation("org.springframework.boot:spring-boot-starter-security-oauth2-client-test")
    testImplementation("org.springframework.boot:spring-boot-starter-security-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")


    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:3.2.0")


    val mapstructVersion = "1.6.3"

    // MapStruct & Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")

    implementation(project(":event-contracts"))
    implementation(project(":grpc-contracts"))


    val blazePersistenceVersion = "1.6.20"

    // 1. BOM de Blaze-Persistence
    implementation(platform("com.blazebit:blaze-persistence-bom:$blazePersistenceVersion"))

    // 2. Núcleo (Jakarta EE)
    implementation("com.blazebit:blaze-persistence-core-api-jakarta")
    runtimeOnly("com.blazebit:blaze-persistence-core-impl-jakarta")

    // 3. Integración con Hibernate 7 (requerido para Spring Boot 4.x)
    runtimeOnly("com.blazebit:blaze-persistence-integration-hibernate-7.1")

    // 4. Integración con Spring Data 4
    implementation("com.blazebit:blaze-persistence-integration-spring-data-4.0")

    // 5. Opcional: Entity Views (proyecciones DTO)
    implementation("com.blazebit:blaze-persistence-entity-view-api-jakarta")
    runtimeOnly("com.blazebit:blaze-persistence-entity-view-impl-jakarta")
    implementation("com.blazebit:blaze-persistence-integration-entity-view-spring-6.0")
    annotationProcessor("com.blazebit:blaze-persistence-entity-view-processor-jakarta")

    // 6. Opcional: Soporte WebMvc / Jackson para controladores REST
    implementation("com.blazebit:blaze-persistence-integration-spring-data-webmvc-jakarta")
    implementation("com.blazebit:blaze-persistence-integration-jackson-jakarta")
}

protobuf {
    plugins {
        id("grpc")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
