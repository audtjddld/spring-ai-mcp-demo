package com.example.mcp.server.domain

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface TodoRepository : ReactiveCrudRepository<TodoEntity, Long>
