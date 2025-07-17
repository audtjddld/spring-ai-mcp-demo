package com.example.mcp.order.domain

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(name = "order_item")
data class OrderItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(name = "item_name")
    val itemName: String,
    @Column(name = "quantity")
    val quantity: Int,
    @Column(name = "price")
    val price: BigDecimal,
    @Column(name = "tax")
    val tax: BigDecimal,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: OrderEntity? = null,
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),
) {
    fun toDomainModel(): OrderItemModel {
        return OrderItemModel(
            id = this.id,
            itemName = this.itemName,
            quantity = this.quantity,
            price = this.price,
            tax = this.tax,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }
}