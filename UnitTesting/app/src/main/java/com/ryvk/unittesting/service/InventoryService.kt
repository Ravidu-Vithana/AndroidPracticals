package com.ryvk.unittesting.service

import com.ryvk.unittesting.model.Product
import com.ryvk.unittesting.repository.ProductRepository

class InventoryService(private val productRepository: ProductRepository) {
    fun addProduct(product: Product): Product {
        require(product.stockQuantity >= 0) { "Stock quantity cannot be negative" }
        return productRepository.save(product)
    }

    fun getAvailableProducts(): List<Product> {
        return productRepository.findAll().filter { it.stockQuantity > 0 }
    }

    fun restockProduct(productId: String, quantity: Int): Product? {
        require(quantity > 0) { "Restock quantity must be positive" }

        val product = productRepository.findById(productId) ?: return null
        val updatedProduct = product.copy(stockQuantity = product.stockQuantity + quantity)
        return productRepository.save(updatedProduct)
    }
}