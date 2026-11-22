plugins {
    `java-library`
    id("com.google.protobuf") version "0.9.4"
}

dependencies {
    // 1. Clases base para serializar/deserializar los mensajes Protobuf a objetos Java
    api("io.grpc:grpc-protobuf:1.69.0")

    // 2. Interfaces y Stubs de gRPC (BlockingStub, FutureStub, etc.)
    api("io.grpc:grpc-stub:1.69.0")


    compileOnly("jakarta.annotation:jakarta.annotation-api:2.1.1")
    compileOnly("javax.annotation:javax.annotation-api:1.3.2")

    compileOnly("jakarta.annotation:jakarta.annotation-api:2.1.1")
}

protobuf {
    protoc {
        // El compilador de Protobuf de Google (binario nativo)
        artifact = "com.google.protobuf:protoc:3.25.5"
    }
    plugins {
        // Plugin generador de stubs gRPC en Java
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.69.0"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                create("grpc")
            }
        }
    }
}