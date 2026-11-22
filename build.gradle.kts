plugins {
    java
    id("org.springframework.boot") version "4.2.0" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
}



subprojects {
    group = "com"
    version = "0.0.1-SNAPSHOT"
    description = "K-and-L-Health-V2"

    repositories {
        mavenCentral()
    }


    plugins.withType<JavaPlugin> {
        extensions.configure<JavaPluginExtension> {
            toolchain {
                languageVersion.set(JavaLanguageVersion.of(21))
            }
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }


    tasks.withType<JavaExec>().configureEach {
        workingDir = projectDir
    }
}
