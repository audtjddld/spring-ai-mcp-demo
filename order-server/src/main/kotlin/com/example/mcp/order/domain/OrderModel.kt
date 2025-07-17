package com.example.mcp.order.domain

import java.math.BigDecimal
import java.time.LocalDateTime
import org.springframework.ai.tool.annotation.ToolParam

data class OrderModel(
    val id: Long? = null,
    @ToolParam(description = "주문 상품명")
    val productName: String,
    @ToolParam(description = "총 주문 금액")
    val totalAmount: BigDecimal,
    @ToolParam(description = "총 주문 세금")
    val totalTax: BigDecimal,
    @ToolParam(description = "총 주문 수량")
    val totalQuantity: Int,
    @ToolParam(description = "주문 아이템 목록")
    val orderItems: List<OrderItemModel>,
    @ToolParam(description = "주문 생성 시간")
    val createdAt: LocalDateTime,
    @ToolParam(description = "주문 수정 시간", required = false)
    val updatedAt: LocalDateTime? = null,
)
