package com.ryvk.unittesting.repository

import com.ryvk.unittesting.model.Product

interface ProductRepository {
    fun save(product: Product): Product
    fun findById(id: String): Product?
    fun findAll(): List<Product>
    fun findByCategory(category: String): List<Product>
    fun delete(id: String): Boolean
}