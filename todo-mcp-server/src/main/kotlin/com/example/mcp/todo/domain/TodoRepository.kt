package com.example.mcp.todo.domain

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface TodoRepository : ReactiveCrudRepository<TodoEntity, Long>
