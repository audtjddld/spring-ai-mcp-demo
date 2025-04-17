plugins {
    kotlin("jvm") version "1.9.10"
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example.mcp"
version = "0.0.1-SNAPSHOT"

allprojects {
    apply {
        plugin("java")
        plugin("io.spring.dependency-management")
        plugin("kotlin")
    }

    repositories {
        mavenCentral()
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(20))
        }
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
        systemProperty("spring.profiles.active", "test")
    }

    extra["springAiVersion"] = "1.0.0-M6"

    dependencyManagement {
        imports {
            mavenBom("org.springframework.ai:spring-ai-bom:${extra["springAiVersion"]}")
        }
    }
}

subprojects {
    apply {
        plugin("java")
        plugin("kotlin")
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "20"
        }
    }

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
