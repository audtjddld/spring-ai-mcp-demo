package com.example.mcp.client.service

import io.modelcontextprotocol.client.McpAsyncClient
import io.modelcontextprotocol.spec.McpSchema
import io.modelcontextprotocol.spec.McpSchema.CallToolResult
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class McpClientService(
    private val mcpAsyncClients: List<McpAsyncClient>
) {
    fun findAll(): Mono<CallToolResult> {
        return mcpAsyncClients[0].callTool(
            McpSchema.CallToolRequest(
                "findAll",
                emptyMap()
            )
        )
    }
}
