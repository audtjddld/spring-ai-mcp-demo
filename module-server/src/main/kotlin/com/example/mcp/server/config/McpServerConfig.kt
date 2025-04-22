package com.example.mcp.server.config

import com.example.mcp.server.service.HomeworkService
import org.springframework.ai.tool.ToolCallbackProvider
import org.springframework.ai.tool.method.MethodToolCallbackProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class McpServerConfig {
    @Bean
    fun homeworkToolCallbackProvider(homeworkService: HomeworkService): ToolCallbackProvider {
        return MethodToolCallbackProvider
            .builder()
            .toolObjects(homeworkService)
            .build()
    }
}
