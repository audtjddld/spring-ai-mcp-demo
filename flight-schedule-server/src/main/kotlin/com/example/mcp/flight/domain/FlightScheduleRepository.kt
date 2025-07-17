package com.example.mcp.flight.domain

import jakarta.persistence.LockModeType
import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface FlightScheduleRepository : JpaRepository<FlightScheduleEntity, Long> {

    @Query(
        "SELECT f FROM flight_schedule f WHERE " +
                "(:flightNumber IS NULL OR f.flightNumber LIKE %:flightNumber%) AND " +
                "(:departureAirport IS NULL OR f.departureAirport LIKE %:departureAirport%) AND " +
                "(:arrivalAirport IS NULL OR f.arrivalAirport LIKE %:arrivalAirport%) AND " +
                "(:airline IS NULL OR f.airline LIKE %:airline%) AND " +
                "(:status IS NULL OR f.status = :status) AND " +
                "(:departureDate IS NULL OR FORMATDATETIME(f.departureTime, 'yyyy-MM-dd') = :departureDate)"
    )
    fun findBySearchCriteria(
        @Param("flightNumber") flightNumber: String?,
        @Param("departureAirport") departureAirport: String?,
        @Param("arrivalAirport") arrivalAirport: String?,
        @Param("airline") airline: String?,
        @Param("status") status: String?,
        @Param("departureDate") departureDate: String?,
    ): List<FlightScheduleEntity>

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT f FROM flight_schedule f WHERE f.id = :id")
    fun findByIdWithLock(@Param("id") id: Long): Optional<FlightScheduleEntity>
}
