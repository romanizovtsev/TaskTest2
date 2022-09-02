package com.example.tasktest.ui.main

import android.content.Context
import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tasktest.ui.recycler.DataModel

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val results = MutableLiveData<MutableList<DataModel>>()
    var startNumber = 3

    fun setIndex(index: Int) {
        _index.value = index
    }
    fun updateDataList(endNumber: Int) {
        var dataList2 =mutableListOf<DataModel>()
        var i = startNumber
        while (i < endNumber) {
            if (isPrimeBruteForce(i)) {
                dataList2.add(DataModel(i, Color.BLUE))
            }
            i += 2
        }
        results.postValue(dataList2)//Тут замена!!!
        startNumber=endNumber
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
}
class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PageViewModel(
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}