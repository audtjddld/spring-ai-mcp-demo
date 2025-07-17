package com.example.mcp.flight.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(name = "flight_schedule")
data class FlightScheduleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(name = "flight_number")
    val flightNumber: String,
    @Column(name = "departure_airport")
    val departureAirport: String,
    @Column(name = "arrival_airport")
    val arrivalAirport: String,
    @Column(name = "departure_time")
    val departureTime: LocalDateTime,
    @Column(name = "arrival_time")
    val arrivalTime: LocalDateTime,
    @Column(name = "airline")
    val airline: String,
    @Column(name = "status")
    val status: String,
    @Column(name = "price")
    val price: BigDecimal,
    @Column(name = "available_seats")
    val availableSeats: Int,
    @Column(name = "total_seats")
    val totalSeats: Int,
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),
) {
    fun toDomainModel(): FlightScheduleModel {
        return FlightScheduleModel(
            id = this.id,
            flightNumber = this.flightNumber,
            departureAirport = this.departureAirport,
            arrivalAirport = this.arrivalAirport,
            departureTime = this.departureTime,
            arrivalTime = this.arrivalTime,
            airline = this.airline,
            status = this.status,
            price = this.price,
            availableSeats = this.availableSeats,
            totalSeats = this.totalSeats,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }
}
