package com.example.tasktest.manager

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tasktest.ui.recycler.DataModel
import java.math.BigInteger

class NumberGeneratorManager {
    var id=0//Тут ???
    fun updateDataList(endNumber: Int): MutableList<DataModel> {
        var dataList2 =mutableListOf<DataModel>()
        var i = endNumber-100
        while (i < endNumber) {
            if (isPrimeBruteForce(i)) {
                if(id<3)
                    id++
                else
                    id=0
                dataList2.add(DataModel(i,id))

            }
            i += 2
        }
        return dataList2//Тут замена!!!

    }

    private fun isPrimeBruteForce(number: Int): Boolean {
        var i = 2
        while (i*i <= number) {
            if (number % i == 0) {
                return false;
            }
            i++
        }
        return true;
    }

    fun updateDataList2(preLast: Int,Last:Int): MutableList<DataModel> {
        var dataList2 =mutableListOf<DataModel>()
        for(i in 0..25)
            dataList2.add(DataModel(i,0))
        return dataList2//Тут замена!!!

    }
//    fun getFibonacciByIndexInfinite(index: BigInteger): BigInteger {
//        if (index == BigInteger.ZERO) {
//            return BigInteger.ZERO
//        }
//        if (index < BigInteger.ZERO) {
//            throw IndexOutOfBoundsException(index.toString())
//        }
//        var n0 = BigInteger.ZERO
//        var n1 = BigInteger.ONE
//        var i = BigInteger.TWO
//        while (i <= index) {
//            val n2 = n0 + n1
//            n0 = n1
//            n1 = n2
//            i++
//        }
//        return n1
//    }
}