package com.example.mcp.todo.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TodoRepository : JpaRepository<TodoEntity, Long> {
    
    @Query("SELECT t FROM todo t WHERE " +
           "(:title IS NULL OR t.title LIKE %:title%) AND " +
           "(:description IS NULL OR t.description LIKE %:description%) AND " +
           "(:date IS NULL OR t.date = :date)")
    fun findBySearchCriteria(
        @Param("title") title: String?,
        @Param("description") description: String?,
        @Param("date") date: String?
    ): List<TodoEntity>
}
