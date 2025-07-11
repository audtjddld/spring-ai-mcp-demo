package com.example.mcp.todo.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity(name = "todo")
data class TodoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(name = "title")
    var title: String,
    @Column(name = "description")
    var description: String,
    @Column(name = "date")
    var date: String,
    @Column(name = "reg_date_time")
    val createdDateTime: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "last_update_time")
    val lastUpdateTime: LocalDateTime? = null,
) {
    fun update(title: String, description: String, date: String) = copy(
        title = title,
        description = description,
        date = date,
        lastUpdateTime = LocalDateTime.now()
    )

    fun toModel() = TodoModel(
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
