package com.example.mcp.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

@EnableWebFlux
@SpringBootApplication
class McpClientApplication

fun main(args: Array<String>) {
    runApplication<McpClientApplication>(*args)
}
