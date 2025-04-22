package com.example.mcp.server.domain

import java.time.LocalDateTime

data class Homework(
    val id: Long,
    var title: String,
    var description: String,
    var date: String,
    val regDateTime: LocalDateTime,
    val lastUpdateTime: LocalDateTime
)
