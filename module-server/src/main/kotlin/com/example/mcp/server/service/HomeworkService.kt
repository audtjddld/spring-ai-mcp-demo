package com.example.mcp.server.service

import com.example.mcp.server.dao.Homework
import com.example.mcp.server.dao.HomeworkRepository
import com.example.mcp.server.model.HomeworkRequest.Save
import com.example.mcp.server.model.HomeworkRequest.Update
import com.example.mcp.server.model.HomeworkResponse
import org.springframework.ai.tool.annotation.Tool
import org.springframework.ai.tool.annotation.ToolParam
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class HomeworkService(
    private val homeworkRepository: HomeworkRepository
) {
    @Tool(name = "save", description = "숙제를 등록하는 기능을 제공합니다.")
    fun save(@ToolParam(description = "제목, 설명, 날짜") request: Save) {
        homeworkRepository.save(Homework.of(request.title, request.description, request.date))
    }

    @Tool(name = "findAll", description = "숙제를 전체 조회하는 기능을 제공합니다.")
    fun findAll(): List<HomeworkResponse?>? {
        return homeworkRepository.findAllAsDTO()
    }

    @Tool(name = "update", description = "숙제를 수정하는 기능을 제공합니다.")
    fun update(@ToolParam(description = "숙제 ID") uid: String?, @ToolParam(description = "제목, 설명, 날짜") request: Update) {
        val homework =
            homeworkRepository.findByUid(uid)!!.orElseThrow {
                IllegalArgumentException(
                    "Invalid UID"
                )
            }!!
        homework.updateDetails(request.title, request.description, request.date)
    }

    @Tool(name = "delete", description = "숙제를 삭제하는 기능을 제공합니다.")
    fun delete(uid: String?) {
        homeworkRepository.deleteByUid(uid)
    }
}
