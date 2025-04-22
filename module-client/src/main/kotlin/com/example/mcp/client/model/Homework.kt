package com.example.mcp.client.model

import java.time.LocalDateTime

data class Homework(
    val id: Long,
    val title: String,
    val description: String,
    val date: String,
    val regDateTime: LocalDateTime,
    val lastUpdateTime: LocalDateTime
)
