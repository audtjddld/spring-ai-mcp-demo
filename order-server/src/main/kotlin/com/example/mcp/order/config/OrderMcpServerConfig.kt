package com.example.mcp.order.config

import com.example.mcp.order.service.OrderTool
import org.springframework.ai.tool.ToolCallbackProvider
import org.springframework.ai.tool.method.MethodToolCallbackProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OrderMcpServerConfig {

    @Bean
    fun orderToolCallbackProvider(orderTool: OrderTool): ToolCallbackProvider {
        return MethodToolCallbackProvider
            .builder()
            .toolObjects(orderTool)
            .build()
    }
}