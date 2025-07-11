package com.example.mcp.todo.service

import com.example.mcp.todo.domain.TodoEntity
import com.example.mcp.todo.domain.TodoModel
import com.example.mcp.todo.domain.TodoRepository
import java.util.logging.Logger
import org.springframework.ai.tool.annotation.Tool
import org.springframework.ai.tool.annotation.ToolParam
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoService(
    private val todoRepository: TodoRepository,
) {
    private val logger = Logger.getLogger(TodoService::class.java.name)

    @Transactional
    @Tool(name = "save", description = "할일을 등록하는 기능을 제공합니다.")
    fun save(@ToolParam(description = "제목, 설명, 날짜") todoModel: TodoModel): TodoEntity {
        return todoRepository.save(TodoEntity.of(todoModel.title, todoModel.description, todoModel.date))
    }

    @Tool(name = "findAll", description = "할일을 전체 조회하는 기능을 제공합니다.")
    fun findAll(): List<TodoModel> {
        return todoRepository.findAll().map { it.toModel() }
    }

    @Tool(name = "update", description = "할일을 수정하는 기능을 제공합니다.")
    @Transactional
    fun update(
        @ToolParam(description = "할일 ID") id: Long,
        @ToolParam(description = "제목") title: String,
        @ToolParam(description = "설명") description: String,
        @ToolParam(description = "날짜") date: String,
    ): TodoEntity {
        val existingTodo = todoRepository.findById(id)

        return if (existingTodo.isPresent) existingTodo.get().update(title, description, date)
        else throw IllegalArgumentException("업데이트 실패")
    }

    @Transactional
    @Tool(name = "delete", description = "할일을 삭제하는 기능을 제공합니다.")
    fun delete(id: Long) {
        todoRepository.deleteById(id)
    }
}
