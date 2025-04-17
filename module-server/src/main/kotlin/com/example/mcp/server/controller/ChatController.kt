package com.example.mcp.server.controller

import org.slf4j.LoggerFactory
import org.springframework.ai.chat.client.ChatClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/chat")
class ChatController(
    private val chatClient: ChatClient
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun chat(@RequestBody request: ChatRequest): ResponseEntity<ChatResponse> {
        return try {
            val userMessage = request.message
            val content = chatClient.prompt()
                .user(userMessage)
                .call()
            logger.info("ChatGPT 응답: ${content.chatResponse()}")

            ResponseEntity.ok(ChatResponse(content.content()))
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.ok(ChatResponse("요청 처리 중 오류 발생: ${e.message}"))
        }
    }
}

class ChatRequest(
    val message: String,
)

class ChatResponse(val content: String?)
