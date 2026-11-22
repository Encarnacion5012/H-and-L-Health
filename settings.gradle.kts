rootProject.name = "K-and-L-Health-V2"



listOf(
    "grpc-contracts",
    "event-contracts",
    "api-gateway",
    "auth-service",
    "patient-service",
    "doctor-service",
    "medical_services-service"
).forEach { moduleName ->
    if (file(moduleName).isDirectory) {
        include(moduleName)
    }
}

