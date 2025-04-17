package com.example.mcp.server.dao

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import java.time.LocalDateTime
import java.util.UUID
import org.hibernate.annotations.Comment
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp

@Entity
data class Homework(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private val seq: Long? = null,

    @Column(nullable = false)
    @Comment("UID")
    private var uid: String? = null,

    @Column(nullable = false)
    @Comment("제목")
    private var title: String? = null,

    @Column(nullable = false)
    @Comment("설명")
    private var description: String? = null,

    @Column(nullable = false)
    @Comment("제출 마감일")
    private var date: String? = null,

    @CreationTimestamp
    @Comment("등록일시")
    private val regDateTime: LocalDateTime? = null,

    @UpdateTimestamp
    @Comment("최종 수정일시")
    private val lastUpdateTime: LocalDateTime? = null
) {

    fun updateDetails(title: String?, description: String?, date: String?) {
        var date = date
        this.title = title ?: this.title
        this.description = description ?: this.description
        date = date ?: this.date
        this.date = date
    }

    companion object {
        fun of(title: String?, description: String?, date: String?): Homework {
            val homework = Homework()
            homework.uid = UUID.randomUUID().toString().replace("-", "")
            homework.title = title
            homework.description = description
            homework.date = date
            return homework
        }
    }
}
