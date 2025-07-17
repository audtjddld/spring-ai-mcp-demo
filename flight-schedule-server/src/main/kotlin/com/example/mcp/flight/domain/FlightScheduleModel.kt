package com.example.mcp.flight.domain

import java.math.BigDecimal
import java.time.LocalDateTime

data class FlightScheduleModel(
    val id: Long? = null,
    val flightNumber: String,
    val departureAirport: String,
    val arrivalAirport: String,
    val departureTime: LocalDateTime,
    val arrivalTime: LocalDateTime,
    val airline: String,
    val status: String,
    val price: BigDecimal,
    val availableSeats: Int,
    val totalSeats: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
)
