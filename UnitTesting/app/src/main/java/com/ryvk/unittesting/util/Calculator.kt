package com.ryvk.unittesting.util

class Calculator {
    fun add(a: Int, b: Int): Int = a + b
    fun subtract(a: Int, b: Int): Int = a - b
    fun multiply(a: Int, b: Int): Int = a * b
    fun divide(a: Int, b: Int): Int {
        require(b != 0) { "Cannot divide by zero" }
        return a / b
    }

    fun calculateDiscount(originalPrice: Double, discountPercentage: Double): Double {
        require(originalPrice >= 0) { "Price cannot be negative" }
        require(discountPercentage in 0.0..100.0) { "Discount must be between 0 and 100" }
        return originalPrice * (1 - discountPercentage / 100)
    }
}