package com.example.mcp.server.domain

data class TodoModel(
    val id: Long,
    var title: String,
    var description: String,
    var date: String,
)
