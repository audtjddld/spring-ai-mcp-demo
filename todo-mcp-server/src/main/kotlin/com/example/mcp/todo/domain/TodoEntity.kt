package com.example.mcp.todo.domain

import java.time.LocalDateTime
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("todo")
data class TodoEntity(
    @Id
    val id: Long? = null,
    var title: String,
    var description: String,
    var date: String,
    val regDateTime: LocalDateTime? = LocalDateTime.now(),
    val lastUpdateTime: LocalDateTime? = null,
) {
    fun update(title: String, description: String, date: String) = copy(
        title = title,
        description = description,
        date = date,
        lastUpdateTime = LocalDateTime.now()
    )

    fun toHomework() = TodoModel(
        id = id!!,
        title = title,
        description = description,
        date = date,
    )

    companion object {
        fun of(title: String, description: String, date: String) = TodoEntity(
            title = title,
            description = description,
            date = date
        )
    }
}
