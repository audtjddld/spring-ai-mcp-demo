plugins {
    kotlin("jvm") version "1.9.20"
    kotlin("kapt") version "1.9.20"
    kotlin("plugin.allopen") version "1.9.20"
    kotlin("plugin.spring") version "1.9.20" apply false
    id("org.springframework.boot") version "3.4.4" apply false
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example.mcp"
version = "0.0.1-SNAPSHOT"

allprojects {
    apply {
        plugin("io.spring.dependency-management")
        plugin("kotlin")
    }

    repositories {
        mavenCentral()
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "21"
        }
    }
    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
        systemProperty("spring.profiles.active", "test")
    }

    extra["springAiVersion"] = "1.0.0-M7"

    dependencyManagement {
        imports {
            mavenBom("org.springframework.ai:spring-ai-bom:${extra["springAiVersion"]}")
        }
    }
}


subprojects {
    apply {
        plugin("kotlin")
        plugin("org.jetbrains.kotlin.jvm")
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
