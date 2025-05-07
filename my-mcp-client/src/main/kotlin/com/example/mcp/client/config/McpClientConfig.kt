package com.example.mcp.client.config

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor
import org.springframework.ai.tool.ToolCallbackProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class McpClientConfig {

    @Bean
    fun chatClient(
        chatClientBuilder: ChatClient.Builder,
        tools: ToolCallbackProvider
    ): ChatClient {
        return chatClientBuilder
            .defaultTools(tools)
            .defaultAdvisors(SimpleLoggerAdvisor())
            .build()
    }
}
