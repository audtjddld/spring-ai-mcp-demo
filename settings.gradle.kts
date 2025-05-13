plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "spring-ai-mcp-example"

include(
    "todo-mcp-server",
    "my-mcp-client",
)
