package com.ryvk.unittesting.model

data class Order(
    val id: String,
    val userId: String,
    val products: List<OrderItem>,
    val totalAmount: Double,
    val status: OrderStatus = OrderStatus.PENDING
) {
    data class OrderItem(
        val productId: String,
        val quantity: Int,
        val unitPrice: Double
    )
}

enum class OrderStatus {
    PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED
}