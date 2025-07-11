package com.example.mcp.flight.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface FlightScheduleRepository : JpaRepository<FlightScheduleEntity, Long> {
    
    @Query("SELECT f FROM flight_schedule f WHERE " +
           "(:flightNumber IS NULL OR f.flightNumber LIKE %:flightNumber%) AND " +
           "(:departureAirport IS NULL OR f.departureAirport LIKE %:departureAirport%) AND " +
           "(:arrivalAirport IS NULL OR f.arrivalAirport LIKE %:arrivalAirport%) AND " +
           "(:airline IS NULL OR f.airline LIKE %:airline%) AND " +
           "(:status IS NULL OR f.status = :status)")
    fun findBySearchCriteria(
        @Param("flightNumber") flightNumber: String?,
        @Param("departureAirport") departureAirport: String?,
        @Param("arrivalAirport") arrivalAirport: String?,
        @Param("airline") airline: String?,
        @Param("status") status: String?
    ): List<FlightScheduleEntity>
}
