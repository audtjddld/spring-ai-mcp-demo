package com.example.mcp.client.web

import com.example.mcp.client.web.model.TodoRequest
import java.time.Duration
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.prompt.PromptTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Flux
import reactor.util.retry.Retry


@RestController
@RequestMapping("/v1/todo")
class TodoController(
    private val chatClient: ChatClient,
) {

    @PostMapping("request")
    fun test(@RequestBody request: TodoRequest): Flux<String>? {

        val promptTemplate = PromptTemplate(request.prompt.trimIndent())

        val pt = promptTemplate.create()

        return this.chatClient.prompt(pt).stream().content()
            .bufferUntil { it.endsWith(".") || it.endsWith("!") || it.endsWith("?") }
            .delayElements(Duration.ofSeconds(2))
            .map { chunks -> chunks.joinToString("").trim() }
            .retryWhen(
                Retry.backoff(3, Duration.ofSeconds(2))
                    .filter { it is WebClientResponseException.TooManyRequests })
            .onErrorResume { error ->
                when (error) {
                    is WebClientResponseException.TooManyRequests -> {
                        println("429 Too Many Requests: ${error.message}")
                        Flux.just("data: Error: Too many requests, please try again later\n\n")
                    }

                    is WebClientResponseException -> {
                        println("HTTP error: ${error.statusCode} - ${error.message}")
                        Flux.just("data: Error: ${error.message}\n\n")
                    }

                    else -> {
                        println("Unexpected error: ${error.message}")
                        Flux.just("data: Error: ${error.message}\n\n")
                    }
                }
            }
    }
}
