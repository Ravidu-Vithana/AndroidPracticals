package com.ryvk.unittesting.service

import com.ryvk.unittesting.model.Order
import com.ryvk.unittesting.repository.OrderRepository
import com.ryvk.unittesting.repository.ProductRepository

class OrderService(
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository
) {
    fun placeOrder(userId: String, items: List<Order.OrderItem>): Order {
        require(items.isNotEmpty()) { "Order must have at least one item" }

        // Check product availability
        items.forEach { item ->
            val product = productRepository.findById(item.productId)
                ?: throw IllegalArgumentException("Product ${item.productId} not found")

            if (product.stockQuantity < item.quantity) {
                throw IllegalStateException("Insufficient stock for product ${product.name}")
            }
        }

        // Calculate total
        val total = items.sumOf { it.quantity * it.unitPrice }

        // Create order
        val order = Order(
            id = generateOrderId(),
            userId = userId,
            products = items,
            totalAmount = total
        )

        // Save order
        val savedOrder = orderRepository.save(order)

        // Update stock
        items.forEach { item ->
            val product = productRepository.findById(item.productId)!!
            productRepository.save(
                product.copy(stockQuantity = product.stockQuantity - item.quantity)
            )
        }

        return savedOrder
    }

    private fun generateOrderId(): String = "ORD-${System.currentTimeMillis()}"
}