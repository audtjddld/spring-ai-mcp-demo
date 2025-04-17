package com.example.mcp.client.config

import com.example.mcp.client.service.HomeworkServiceTool
import org.springframework.ai.tool.ToolCallbackProvider
import org.springframework.ai.tool.method.MethodToolCallbackProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class McpClientConfig {
    @Bean
    fun homeworkTools(homeworkServiceTool: HomeworkServiceTool): ToolCallbackProvider {
        System.err.println("🛠️ MCP Tool 등록됨: HomeworkServiceTool")
        return MethodToolCallbackProvider
            .builder()
            .toolObjects(homeworkServiceTool)
            .build()
    }
}
