package com.ryvk.unittesting.util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CalculatorTest {
    @ParameterizedTest
    @MethodSource("provideAddTestData")
    fun testAdd(a: Int, b: Int, expected: Int){
        val calculator = Calculator()
        val result = calculator.add(a, b)
        assertEquals(expected, result)
    }
    @ParameterizedTest
    @MethodSource("provideSubtractTestData")
    fun testSubtract(a: Int, b: Int, expected: Int) {
        val calculator = Calculator()
        val result = calculator.subtract(a, b)
        assertEquals(expected, result)
    }
    @ParameterizedTest
    @MethodSource("provideMultiplyTestData")
    fun testMultiply(a: Int, b: Int, expected: Int) {
        val calculator = Calculator()
        val result = calculator.multiply(a, b)
        assertEquals(expected, result)
    }
    @ParameterizedTest
    @MethodSource("provideDivideTestData")
    fun testDivide(a: Double, b: Double, expected: Double) {
        val calculator = Calculator()
        val result = calculator.divide(a, b)
        assertEquals(expected, result)
    }

    companion object {
        @JvmStatic
        fun provideAddTestData(): Stream<Arguments>{
            return Stream.of(
                Arguments.of(1, 2, 3),
                Arguments.of(5, 10, 15),
                Arguments.of(-3, 4, 1),
            )
        }
        @JvmStatic
        fun provideSubtractTestData(): Stream<Arguments>{
            return Stream.of(
                Arguments.of(1, 2, -1),
                Arguments.of(5, 10, -5),
                Arguments.of(-3, 4, -7),
            )
        }
        @JvmStatic
        fun provideMultiplyTestData(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(1, 2, 2),
                Arguments.of(5, 10, 50),
                Arguments.of(-3, 4, -12),
            )
        }
        @JvmStatic
        fun provideDivideTestData(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(1, 2, 0.5),
                Arguments.of(5, 10, 0.5),
                Arguments.of(-3, 4, -0.75),
            )
        }
    }
}