package com.example.mcp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FlightScheduleApplication

fun main(args: Array<String>) {
    runApplication<FlightScheduleApplication>(*args)
}
