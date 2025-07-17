package com.example.mcp.order.domain

import java.math.BigDecimal
import java.time.LocalDateTime
import org.springframework.ai.tool.annotation.ToolParam

data class OrderItemModel(
    val id: Long? = null,
    @ToolParam(description = "주문 아이템명")
    val itemName: String,
    @ToolParam(description = "주문 아이템 수량")
    val quantity: Int,
    @ToolParam(description = "주문 아이템 수량")
    val price: BigDecimal,
    @ToolParam(description = "주문 아이템 수량")
    val tax: BigDecimal,
    @ToolParam(description = "주문 아이템 수량")
    val createdAt: LocalDateTime,
    @ToolParam(description = "주문 아이템 수정 시간", required = false)
    val updatedAt: LocalDateTime? = null,
)
