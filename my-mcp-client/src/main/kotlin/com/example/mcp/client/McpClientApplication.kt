package com.example.mcp.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

//@EnableWebFlux
@SpringBootApplication
class McpClientApplication

fun main(args: Array<String>) {
    runApplication<McpClientApplication>(*args)
}
