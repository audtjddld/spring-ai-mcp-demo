package com.example.mcp.todo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.web.reactive.config.EnableWebFlux

@EnableR2dbcRepositories
@EnableWebFlux
@SpringBootApplication
class TodoServerApplication

fun main(args: Array<String>) {
    runApplication<TodoServerApplication>(*args)
}
