package com.example.mcp.flight.service

import com.example.mcp.flight.domain.FlightScheduleEntity
import com.example.mcp.flight.domain.FlightScheduleModel
import com.example.mcp.flight.domain.FlightScheduleRepository
import java.time.LocalDateTime
import org.springframework.ai.tool.annotation.Tool
import org.springframework.ai.tool.annotation.ToolParam
import org.springframework.stereotype.Component

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
        @ToolParam(description = "항공편 상태 (정확한 일치)", required = false) status: String? = null
    ): List<FlightScheduleModel> {
        return flightScheduleRepository.findBySearchCriteria(
            flightNumber = flightNumber,
            departureAirport = departureAirport,
            arrivalAirport = arrivalAirport,
            airline = airline,
            status = status
        ).map { it.toDomainModel() }
    }

    @Tool(name = "getScheduleById", description = "ID로 항공편 일정을 가져옵니다")
    fun getScheduleById(id: Long): FlightScheduleModel = flightScheduleRepository.findById(id)
        .map { it.toDomainModel() }
        .orElseThrow { IllegalArgumentException("Schedule not found for ID: $id") }

    @Tool(name = "createSchedule", description = "새로운 항공편 일정을 생성합니다")
    fun createSchedule(
        @ToolParam(description = "항공기 번호") flightNumber: String,
        @ToolParam(description = "출발 공항 코드") departureAirport: String,
        @ToolParam(description = "도착 공항 코드") arrivalAirport: String,
        @ToolParam(description = "항공기 출발시간") departureTime: String,
        @ToolParam(description = "항공기 도착시간") arrivalTime: String,
        @ToolParam(description = "항공사") airline: String,
        @ToolParam(description = "항공편 상태") status: String,
    ): FlightScheduleModel {
        val schedule = FlightScheduleEntity(
            flightNumber = flightNumber,
            departureAirport = departureAirport,
            arrivalAirport = arrivalAirport,
            departureTime = LocalDateTime.parse(departureTime),
            arrivalTime = LocalDateTime.parse(arrivalTime),
            airline = airline,
            status = status
        )
        return flightScheduleRepository.save(schedule).toDomainModel()
    }
}
