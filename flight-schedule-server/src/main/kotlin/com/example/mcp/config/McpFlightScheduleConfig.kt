package com.example.mcp.config

import com.example.mcp.flight.service.FlightScheduleTool
import org.springframework.ai.tool.ToolCallbackProvider
import org.springframework.ai.tool.method.MethodToolCallbackProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class McpFlightScheduleConfig {

    @Bean
    fun flightScheduleToolCallbackProvider(flightScheduleTool: FlightScheduleTool): ToolCallbackProvider {
        return MethodToolCallbackProvider
            .builder()
            .toolObjects(flightScheduleTool)
            .build()
    }
}
