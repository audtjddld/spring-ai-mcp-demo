package com.example.mcp.server.dao

import com.example.mcp.server.model.HomeworkResponse
import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface HomeworkRepository : JpaRepository<Homework, Long> {
    @Query("SELECT new com.example.mcp.server.model.HomeworkResponse(a.uid, a.title, a.description, a.date) FROM Homework a")
    fun findAllAsDTO(): List<HomeworkResponse?>?

    fun findByUid(uid: String?): Optional<Homework?>?

    fun deleteByUid(uid: String?)
}
