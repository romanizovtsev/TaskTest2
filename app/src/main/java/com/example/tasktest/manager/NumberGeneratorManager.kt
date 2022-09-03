package com.example.tasktest.manager

import com.example.tasktest.domain.entity.PackageData
import com.example.tasktest.ui.model.NumberModel
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import kotlin.math.sqrt


class NumberGeneratorManager {

    fun getSimpleNumbers(endNumber: BigDecimal, id: Int): PackageData { // return instance of PackageData class with list of simple numbers and id(0..3) of the last element
        val dataList = mutableListOf<NumberModel>()
        var idFun = id
        var i: BigDecimal = endNumber.minus(BigDecimal.valueOf(RANGE_OF_SIMPLE_NUMBERS))
        while (i < endNumber) {
            if (isSimple(i)) {
                idFun = if (idFun < MAX_ID) idFun + 1 else 0
                dataList.add(NumberModel(i, idFun))

            }
            i = i.plus(BigDecimal.valueOf(2))
        }
        return PackageData(dataList, idFun)

    }

    private fun isSimple(number: BigDecimal): Boolean {
        var i: BigDecimal = BigDecimal.valueOf(2)
        while (i * i <= number) {
            if (number.rem(i) == BigDecimal.ZERO) {
                return false
            }
            i++
        }
        return true
    }

    fun getFibonacciNumbers(preLast: BigDecimal, last: BigDecimal, id: Int): PackageData { // return instance of PackageData class with list of Fibonacci numbers and id(0..3) of the last element
        val dataList = mutableListOf<NumberModel>()
        var newNumber: BigDecimal
        var preLastFun: BigDecimal = preLast
        var lastFun: BigDecimal = last
        var idFun = id

        for (i in 0..SIZE_OF_FIBONACCI_NUMBERS) {
            idFun = if (idFun < 3) idFun + 1 else 0
            newNumber = preLastFun + lastFun
            dataList.add(NumberModel(newNumber.setPrecision(10), idFun))
            preLastFun = lastFun
            lastFun = newNumber
        }

        return PackageData(dataList, idFun)

    }

    private fun BigDecimal.setPrecision(newPrecision: Int) =
        BigDecimal(toPlainString(), MathContext(newPrecision, RoundingMode.HALF_UP)) // return exponential form of a number

    private companion object {
        private const val SIZE_OF_FIBONACCI_NUMBERS = 20
        private const val RANGE_OF_SIMPLE_NUMBERS = 100L
        private const val MAX_ID = 3
    }
}