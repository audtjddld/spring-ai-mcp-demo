package com.example.mcp.order.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(name = "order_table")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(name = "product_name")
    val productName: String,
    @Column(name = "total_amount")
    val totalAmount: BigDecimal,
    @Column(name = "total_tax")
    val totalTax: BigDecimal,
    @Column(name = "total_quantity")
    val totalQuantity: Int,
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val orderItems: List<OrderItemEntity> = emptyList(),
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),
) {
    fun toDomainModel(): OrderModel {
        return OrderModel(
            id = this.id,
            productName = this.productName,
            totalAmount = this.totalAmount,
            totalTax = this.totalTax,
            totalQuantity = this.totalQuantity,
            orderItems = this.orderItems.map { it.toDomainModel() },
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }
}
