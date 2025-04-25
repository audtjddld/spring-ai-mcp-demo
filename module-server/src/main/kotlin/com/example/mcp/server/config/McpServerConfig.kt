package com.example.mcp.server.config

import com.example.mcp.server.service.TodoService
import org.springframework.ai.tool.ToolCallbackProvider
import org.springframework.ai.tool.method.MethodToolCallbackProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class McpServerConfig {
    @Bean
    fun homeworkToolCallbackProvider(todoService: TodoService): ToolCallbackProvider {
        return MethodToolCallbackProvider
            .builder()
            .toolObjects(todoService)
            .build()
    }
}
