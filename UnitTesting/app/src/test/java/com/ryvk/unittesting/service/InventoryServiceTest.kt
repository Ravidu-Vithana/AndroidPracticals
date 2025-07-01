package com.ryvk.unittesting.service

import com.ryvk.unittesting.model.Product
import com.ryvk.unittesting.repository.FakeProductRepository
import com.ryvk.unittesting.repository.ProductRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class InventoryServiceTest {
    private lateinit var inventoryService: InventoryService
    private lateinit var productRepository: ProductRepository

    @BeforeEach
    fun setUp(){
        productRepository = FakeProductRepository()
        inventoryService = InventoryService(productRepository)

        val testingProductList = mutableListOf<Product>()
        (1..30).forEachIndexed { index, c ->
            testingProductList.add(
                Product(
                    id = c.toString(),
                    name = c.toString(),
                    category = c.toString(),
                    price = index.toDouble(),
                    stockQuantity = index,
                    description = c.toString(),
                )
            )
        }
        testingProductList.shuffle()
        testingProductList.forEach {
            inventoryService.addProduct(it)
        }
    }

    @Test
    fun `add a product and check whether details are added properly`() {
        val product = Product(
            id = "1",
            name = "Product 1",
            category = "Category 1",
            price = 10.0,
            stockQuantity = 10,
            description = "Description 1"
        )
        val addedProduct = inventoryService.addProduct(product)
        assertEquals(product, addedProduct)
    }

    @Test
    fun `get a list of available products and check whether the list is not empty`() {
        val availableProducts = inventoryService.getAvailableProducts()
        assertTrue(availableProducts.isNotEmpty())
    }

    @Test
    fun `restock a product and check whether the stock quantity is updated properly`() {
        val productId = "1"
        val restockQuantity = 5
        val updatedProduct = inventoryService.restockProduct(productId, restockQuantity)
        assertEquals(restockQuantity, updatedProduct?.stockQuantity)

    }

}