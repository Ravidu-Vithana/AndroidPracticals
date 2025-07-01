package com.ryvk.unittesting.repository

import com.ryvk.unittesting.model.Product
import java.util.concurrent.atomic.AtomicReference

class FakeProductRepository : ProductRepository{
    private val products = mutableMapOf<String, Product>()

    override fun save(product: Product): Product {
        this.products[product.id] = product
        return product
    }

    override fun findById(id: String): Product? {
        return products[id]
    }

    override fun findAll(): List<Product> {
        return products.values.toList()
    }

    override fun findByCategory(category: String): List<Product> {
        return products.values.filter { it.category.equals(category, ignoreCase = true) }
    }

    override fun delete(id: String): Boolean {
        val productExists = products.containsKey(id)
        products.remove(id)
        return productExists
    }

    fun clear() {
        products.clear()
    }
}