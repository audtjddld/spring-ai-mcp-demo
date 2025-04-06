package com.example.moduleserver.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class HomeworkServiceTool {
    private final RestClient restClient;
    private static final String BASE_URL = "http://localhost:8080";
    private static final String DEFAULT_ENDPOINT = "/api/homeworks";

    public HomeworkServiceTool(RestTemplateBuilder restTemplateBuilder) {
        this.restClient = RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader("Accept", "application/json")
                .defaultHeader("User-Agent", "McpDemoClient/1.0 (minkyu1128@gmail.com)")
                .build();
    }

    @Tool(name = "saveHomework", description = "숙제 등록")
    public String saveHomework(@ToolParam(description = "숙제 제목") String title,
                               @ToolParam(description = "설명") String description,
                               @ToolParam(description = "yyyy-mm-dd 형식의 제출 마감일") String date
    ) {
        System.err.println("📥 saveHomework 호출됨: " + title + ", " + description + ", " + date);
        try {
            restClient.post()
                    .uri(DEFAULT_ENDPOINT)
                    .body(Map.of(
                            "title", title,
                            "description", description,
                            "date", date
                    ))
                    .contentType(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toBodilessEntity(); // 또는 .body(String.class);

            return "OK";
        } catch (Exception e) {
            System.err.println("[📥 saveHomework Error]: " + e.getMessage());
            return "FAIL";
        }
    }

    @Tool(description = "전체 숙제 조회")
    public String findAllHomework() {
        System.err.println("📥 findAllHomework 호출됨");
        try {
            return restClient.get()
                    .uri(DEFAULT_ENDPOINT)
                    .retrieve()
                    .body(String.class);  // 또는 적절한 응답 타입
        } catch (Exception e) {
            System.err.println("[📥 findAllHomework Error]: " + e.getMessage());
            return "FAIL";
        }
    }

    @Tool(description = "숙제 정보 수정")
    public String updateHomework(@ToolParam(description = "UID") String uid,
                                 @ToolParam(description = "숙제 제목", required = false) String title,
                                 @ToolParam(description = "설명", required = false) String description,
                                 @ToolParam(description = "제출 마감일", required = false) String date
    ) {
        System.err.println("📥 updateHomework 호출됨");
        try {
            restClient.put()
                    .uri(DEFAULT_ENDPOINT + "/" + uid)
                    .body(Map.of(
                            "title", title,
                            "description", description,
                            "date", date
                    ))
                    .contentType(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toBodilessEntity(); // 또는 .body(String.class);
            return "OK";
        } catch (Exception e) {
            System.err.println("[📥 updateHomework Error]: " + e.getMessage());
            return "FAIL";
        }
    }

    @Tool(description = "숙제 삭제")
    public String deleteHomework(@ToolParam(description = "UID") String uid) {
        System.err.println("📥 deleteHomework 호출됨");
        try {
            restClient.delete()
                    .uri(DEFAULT_ENDPOINT + "/" + uid)
                    .retrieve()
                    .toBodilessEntity(); // 또는 .body(String.class);
            return "OK";
        } catch (Exception e) {
            System.err.println("[📥 deleteHomework Error]: " + e.getMessage());
            return "FAIL";
        }
    }

}
