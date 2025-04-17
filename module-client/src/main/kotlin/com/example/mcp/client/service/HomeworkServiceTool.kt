package com.example.mcp.client.service

import org.springframework.ai.tool.annotation.Tool
import org.springframework.ai.tool.annotation.ToolParam
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class HomeworkServiceTool {
    private val restClient: RestClient = RestClient.builder()
        .baseUrl(BASE_URL)
        .defaultHeader("Accept", "application/json")
        .defaultHeader("User-Agent", "McpDemoClient/1.0 (minkyu1128@gmail.com)")
        .build()


    @Tool(name = "saveHomework", description = "숙제 등록")
    fun saveHomework(
        @ToolParam(description = "숙제 제목") title: String,
        @ToolParam(description = "설명") description: String,
        @ToolParam(description = "yyyy-mm-dd 형식의 제출 마감일") date: String
    ): String {
        System.err.println("📥 saveHomework 호출됨: $title, $description, $date")
        try {
            restClient.post()
                .uri(DEFAULT_ENDPOINT)
                .body(
                    mapOf(
                        "title" to title,
                        "description" to description,
                        "date" to date
                    )
                )
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .toBodilessEntity() // 또는 .body(String.class);

            return "OK"
        } catch (e: Exception) {
            System.err.println("[📥 saveHomework Error]: " + e.message)
            return "FAIL"
        }
    }

    @Tool(description = "전체 숙제 조회")
    fun findAllHomework(): String? {
        System.err.println("📥 findAllHomework 호출됨")
        try {
            return restClient.get()
                .uri(DEFAULT_ENDPOINT)
                .retrieve()
                .body(String::class.java) // 또는 적절한 응답 타입
        } catch (e: Exception) {
            System.err.println("[📥 findAllHomework Error]: " + e.message)
            return "FAIL"
        }
    }

    @Tool(description = "숙제 정보 수정")
    fun updateHomework(
        @ToolParam(description = "UID") uid: String,
        @ToolParam(description = "숙제 제목", required = false) title: String,
        @ToolParam(description = "설명", required = false) description: String,
        @ToolParam(description = "제출 마감일", required = false) date: String
    ): String {
        System.err.println("📥 updateHomework 호출됨")
        try {
            restClient.put()
                .uri("$DEFAULT_ENDPOINT/$uid")
                .body(
                    mapOf(
                        "title" to title,
                        "description" to description,
                        "date" to date
                    )
                )
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .toBodilessEntity() // 또는 .body(String.class);
            return "OK"
        } catch (e: Exception) {
            System.err.println("[📥 updateHomework Error]: " + e.message)
            return "FAIL"
        }
    }

    @Tool(description = "숙제 삭제")
    fun deleteHomework(@ToolParam(description = "UID") uid: String): String {
        System.err.println("📥 deleteHomework 호출됨")
        try {
            restClient.delete()
                .uri("$DEFAULT_ENDPOINT/$uid")
                .retrieve()
                .toBodilessEntity() // 또는 .body(String.class);
            return "OK"
        } catch (e: Exception) {
            System.err.println("[📥 deleteHomework Error]: " + e.message)
            return "FAIL"
        }
    }

    companion object {
        private const val BASE_URL = "http://localhost:8080"
        private const val DEFAULT_ENDPOINT = "/api/homeworks"
    }
}
