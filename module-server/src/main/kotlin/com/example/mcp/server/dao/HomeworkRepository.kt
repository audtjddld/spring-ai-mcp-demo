package com.example.mcp.server.dao

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface HomeworkRepository : ReactiveCrudRepository<HomeworkEntity, Long>
