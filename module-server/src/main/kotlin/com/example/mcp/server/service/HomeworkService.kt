package com.example.mcp.server.service

import com.example.mcp.server.dao.HomeworkEntity
import com.example.mcp.server.dao.HomeworkRepository
import com.example.mcp.server.domain.Homework
import com.example.mcp.server.model.HomeworkRequest.Save
import com.example.mcp.server.model.HomeworkRequest.Update
import java.util.logging.Logger
import org.springframework.ai.tool.annotation.Tool
import org.springframework.ai.tool.annotation.ToolParam
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class HomeworkService(
    private val homeworkRepository: HomeworkRepository
) {
    private val logger = Logger.getLogger(HomeworkService::class.java.name)

    @Transactional(readOnly = true)
    @Tool(name = "save", description = "숙제를 등록하는 기능을 제공합니다.")
    fun save(@ToolParam(description = "제목, 설명, 날짜") request: Save): Mono<HomeworkEntity> {
        return homeworkRepository.save(HomeworkEntity.of(request.title, request.description, request.date))
    }

    @Tool(name = "findAll", description = "숙제를 전체 조회하는 기능을 제공합니다.")
    fun findAll(): Flux<Homework> {
        logger.info(">>>>>>>>>>>>>>>>>>>>> 실행 됨")
        return homeworkRepository.findAll().map {
            it.toHomework()
        }
    }

    @Tool(name = "update", description = "숙제를 수정하는 기능을 제공합니다.")
    fun update(
        @ToolParam(description = "숙제 ID") id: Long,
        @ToolParam(description = "제목, 설명, 날짜") request: Update
    ): Mono<Unit> {
        return homeworkRepository.findById(id)
            .switchIfEmpty(Mono.error(IllegalArgumentException("Invalid UID")))
            .map { it.updateDetails(request.title, request.description, request.date) }
            .flatMap { homeworkRepository.save(it) }
            .thenReturn(Unit)
    }

    @Tool(name = "delete", description = "숙제를 삭제하는 기능을 제공합니다.")
    fun delete(id: Long) {
        homeworkRepository.deleteById(id)
    }
}
