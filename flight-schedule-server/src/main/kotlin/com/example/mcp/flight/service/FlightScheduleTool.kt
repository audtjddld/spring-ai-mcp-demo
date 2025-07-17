package com.example.mcp.flight.service

import com.example.mcp.flight.domain.FlightScheduleEntity
import com.example.mcp.flight.domain.FlightScheduleModel
import com.example.mcp.flight.domain.FlightScheduleRepository
import java.math.BigDecimal
import java.time.LocalDateTime
import org.springframework.ai.tool.annotation.Tool
import org.springframework.ai.tool.annotation.ToolParam
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class FlightScheduleTool(
    private val flightScheduleRepository: FlightScheduleRepository,
) {

    @Tool(name = "getAllSchedules", description = "항공편 일정을 검색조건으로 조회합니다")
    fun getAllSchedules(
        @ToolParam(description = "항공기 번호 (부분 검색 가능)", required = false) flightNumber: String? = null,
        @ToolParam(description = "출발 공항 코드 (부분 검색 가능)", required = false) departureAirport: String? = null,
        @ToolParam(description = "도착 공항 코드 (부분 검색 가능)", required = false) arrivalAirport: String? = null,
        @ToolParam(description = "항공사 (부분 검색 가능)", required = false) airline: String? = null,
        @ToolParam(description = "항공편 상태 (정확한 일치)", required = false) status: String? = null,
        @ToolParam(description = "출발 날짜 (YYYY-MM-DD 형식)", required = false) departureDate: String? = null
    ): List<FlightScheduleModel> {
        return flightScheduleRepository.findBySearchCriteria(
            flightNumber = flightNumber,
            departureAirport = departureAirport,
            arrivalAirport = arrivalAirport,
            airline = airline,
            status = status,
            departureDate = departureDate
        ).map { it.toDomainModel() }
    }

    @Tool(name = "getScheduleById", description = "ID로 항공편 일정을 가져옵니다")
    fun getScheduleById(id: Long): FlightScheduleModel = flightScheduleRepository.findById(id)
        .map { it.toDomainModel() }
        .orElseThrow { IllegalArgumentException("Schedule not found for ID: $id") }

    @Transactional
    @Tool(name = "createSchedule", description = "새로운 항공편 일정을 생성합니다")
    fun createSchedule(
        @ToolParam(description = "항공기 번호") flightNumber: String,
        @ToolParam(description = "출발 공항 코드") departureAirport: String,
        @ToolParam(description = "도착 공항 코드") arrivalAirport: String,
        @ToolParam(description = "항공기 출발시간") departureTime: String,
        @ToolParam(description = "항공기 도착시간") arrivalTime: String,
        @ToolParam(description = "항공사") airline: String,
        @ToolParam(description = "항공편 상태") status: String,
        @ToolParam(description = "항공편 가격") price: String,
        @ToolParam(description = "총 좌석 수") totalSeats: Int,
        @ToolParam(description = "예약 가능한 좌석 수", required = false) availableSeats: Int? = null,
    ): FlightScheduleModel {
        val schedule = FlightScheduleEntity(
            flightNumber = flightNumber,
            departureAirport = departureAirport,
            arrivalAirport = arrivalAirport,
            departureTime = LocalDateTime.parse(departureTime),
            arrivalTime = LocalDateTime.parse(arrivalTime),
            airline = airline,
            status = status,
            price = BigDecimal(price),
            totalSeats = totalSeats,
            availableSeats = availableSeats ?: totalSeats
        )
        return flightScheduleRepository.save(schedule).toDomainModel()
    }

    @Transactional
    @Tool(name = "updateAvailableSeats", description = "항공편의 예약 가능한 좌석 수를 업데이트합니다 (동시성 제어 적용)")
    fun updateAvailableSeats(
        @ToolParam(description = "항공편 ID") flightId: Long,
        @ToolParam(description = "차감할 좌석 수") seatsToReduce: Int
    ): FlightScheduleModel {
        // 비관적 락을 사용하여 동시성 제어
        val entity = flightScheduleRepository.findByIdWithLock(flightId)
            .orElseThrow { IllegalArgumentException("Flight not found for ID: $flightId") }
        
        if (entity.availableSeats < seatsToReduce) {
            throw IllegalArgumentException("Not enough available seats. Available: ${entity.availableSeats}, Requested: $seatsToReduce")
        }
        
        val updatedEntity = entity.copy(
            availableSeats = entity.availableSeats - seatsToReduce,
            status = if (entity.availableSeats - seatsToReduce == 0) "매진" else entity.status,
            updatedAt = LocalDateTime.now()
        )
        
        return flightScheduleRepository.save(updatedEntity).toDomainModel()
    }

    @Tool(name = "checkAvailableSeats", description = "항공편의 예약 가능한 좌석 수를 확인합니다")
    fun checkAvailableSeats(
        @ToolParam(description = "항공편 ID") flightId: Long
    ): Map<String, Any> {
        val entity = flightScheduleRepository.findById(flightId)
            .orElseThrow { IllegalArgumentException("Flight not found for ID: $flightId") }
        
        return mapOf(
            "flightId" to entity.id!!,
            "flightNumber" to entity.flightNumber,
            "availableSeats" to entity.availableSeats,
            "totalSeats" to entity.totalSeats,
            "price" to entity.price,
            "status" to entity.status
        )
    }
}
