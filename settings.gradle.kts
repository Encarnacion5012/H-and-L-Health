rootProject.name = "K-and-L-Health-V2"



listOf(
 ""
).forEach { moduleName ->
    if (file(moduleName).isDirectory) {
        include(moduleName)
    }
}
