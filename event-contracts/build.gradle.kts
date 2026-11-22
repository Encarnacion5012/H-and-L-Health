plugins {
    `java-library`
    id("com.google.protobuf") version "0.9.4"
}

description = "event-contracts"

dependencies {
    // 'api' para que cualquier servicio que importe este módulo herede los tipos de Protobuf
    api("com.google.protobuf:protobuf-java:4.29.3")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:4.29.3"
    }

}