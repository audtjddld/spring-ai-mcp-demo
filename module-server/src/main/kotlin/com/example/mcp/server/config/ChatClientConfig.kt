package com.example.mcp.server.config

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.tool.ToolCallbackProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ChatClientConfig {

    @Bean
    fun chatClient(
        builder: ChatClient.Builder,
        homeworkToolCallbackProvider: ToolCallbackProvider
    ): ChatClient {
        return builder.defaultSystem("당신은 숙제 관리 도우미 입니다. 숙제를 등록하고, 수정하고, 삭제하는 기능을 제공합니다.")
            .defaultTools(homeworkToolCallbackProvider)
            .build()
    }
}
