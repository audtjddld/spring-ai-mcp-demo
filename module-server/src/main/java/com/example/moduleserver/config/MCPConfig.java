package com.example.moduleserver.config;

import com.example.moduleserver.service.HomeworkServiceTool;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MCPConfig {
    @Bean
    public ToolCallbackProvider homeworkTools(HomeworkServiceTool homeworkServiceTool) {
        System.err.println("🛠️ MCP Tool 등록됨: HomeworkServiceTool");
        return MethodToolCallbackProvider
                .builder()
                .toolObjects(homeworkServiceTool)
                .build();
    }
}
