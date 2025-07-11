package com.example.mcp.client.config

import io.modelcontextprotocol.client.McpSyncClient
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class McpClientConfig {

    @Bean
    fun chatClient(
        chatClientBuilder: ChatClient.Builder,
        mcpSyncClients: List<McpSyncClient>,
    ): ChatClient {
        return chatClientBuilder
            .defaultToolCallbacks(SyncMcpToolCallbackProvider(mcpSyncClients))
            .defaultAdvisors(SimpleLoggerAdvisor())
            .build()
    }
}
