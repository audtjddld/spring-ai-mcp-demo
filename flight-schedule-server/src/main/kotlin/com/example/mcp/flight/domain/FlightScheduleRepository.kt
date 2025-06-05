package com.example.mcp.flight.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FlightScheduleRepository : JpaRepository<FlightScheduleEntity, Long>
