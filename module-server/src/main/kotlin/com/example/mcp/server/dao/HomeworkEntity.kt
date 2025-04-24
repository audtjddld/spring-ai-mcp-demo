package com.example.mcp.server.dao

import com.example.mcp.server.domain.Homework
import java.time.LocalDateTime
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("homework")
data class HomeworkEntity(
    @Id
    val id: Long? = null,
    var title: String,
    var description: String,
    var date: String,
    val regDateTime: LocalDateTime? = LocalDateTime.now(),
    val lastUpdateTime: LocalDateTime? = null,
) {
    fun updateDetails(title: String, description: String, date: String) = copy(
        title = title,
        description = description,
        date = date,
        lastUpdateTime = LocalDateTime.now()
    )

    fun toHomework() = Homework(
        id = id!!,
        title = title,
        description = description,
        date = date,
    )

    companion object {
        fun of(title: String, description: String, date: String) = HomeworkEntity(
            title = title,
            description = description,
            date = date
        )
    }
}
