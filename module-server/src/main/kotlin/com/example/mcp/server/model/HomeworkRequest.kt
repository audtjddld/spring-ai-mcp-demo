package com.example.mcp.server.model

class HomeworkRequest {

    data class Save(
        val title: String,
        val description: String,
        val date: String,
    )

    internal data class Details(
        val uid: String,
        val title: String,
        val description: String,
        val date: String,
    )

    data class Update(
        val title: String,
        val description: String,
        val date: String,
    )
}
