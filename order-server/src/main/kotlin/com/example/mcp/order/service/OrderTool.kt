package com.example.mcp.order.service

import com.example.mcp.order.domain.OrderEntity
import com.example.mcp.order.domain.OrderItemEntity
import com.example.mcp.order.domain.OrderItemRepository
import com.example.mcp.order.domain.OrderModel
import com.example.mcp.order.domain.OrderRepository
import java.math.BigDecimal
import java.math.RoundingMode
import org.springframework.ai.tool.annotation.Tool
import org.springframework.ai.tool.annotation.ToolParam
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class OrderTool(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
) {

    @Tool(name = "createOrder", description = "주문 정보를 등록합니다.")
    @Transactional
    fun createOrder(
        @ToolParam(description = "주문 정보 ", required = true) order: OrderModel,
    ): OrderModel {
        val orderEntity = OrderEntity(
            productName = order.productName,
            totalAmount = order.totalAmount,
            totalTax = order.totalTax,
            totalQuantity = order.totalQuantity
        )

        val savedOrder = orderRepository.save(orderEntity)

        order.orderItems.forEach { orderItem ->
            val orderItemEntity = OrderItemEntity(
                itemName = orderItem.itemName,
                quantity = orderItem.quantity,
                price = orderItem.price,
                tax = orderItem.tax,
                order = savedOrder
            )
            orderItemRepository.save(orderItemEntity)
        }

        return orderRepository.findById(savedOrder.id!!).get().toDomainModel()
    }

    @Tool(name = "getOrderById", description = "ID로 주문 정보를 조회합니다")
    fun getOrderById(
        @ToolParam(description = "주문 ID") orderId: Long,
    ): OrderModel {
        return orderRepository.findById(orderId)
            .map { it.toDomainModel() }
            .orElseThrow { IllegalArgumentException("Order not found for ID: $orderId") }
    }

    @Tool(name = "getAllOrders", description = "모든 주문 정보를 조회합니다")
    fun getAllOrders(): List<OrderModel> {
        return orderRepository.findAll().map { it.toDomainModel() }
    }

    private fun distributeAmount(unitPrice: BigDecimal, quantity: Int): List<BigDecimal> {
        val baseAmount = unitPrice.divide(BigDecimal(quantity), 2, RoundingMode.DOWN)
        val remainder = unitPrice.subtract(baseAmount.multiply(BigDecimal(quantity)))

        val amounts = mutableListOf<BigDecimal>()
        for (i in 0 until quantity) {
            if (i < remainder.multiply(BigDecimal("100")).toInt()) {
                amounts.add(baseAmount.add(BigDecimal("0.01")))
            } else {
                amounts.add(baseAmount)
            }
        }
        return amounts
    }
}
