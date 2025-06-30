package com.ryvk.unittesting.repository

import com.ryvk.unittesting.model.Order
import com.ryvk.unittesting.model.OrderStatus

interface OrderRepository {
    fun save(order: Order): Order
    fun findById(id: String): Order?
    fun findByUser(userId: String): List<Order>
    fun updateStatus(orderId: String, status: OrderStatus): Boolean
    fun delete(id: String): Boolean
}