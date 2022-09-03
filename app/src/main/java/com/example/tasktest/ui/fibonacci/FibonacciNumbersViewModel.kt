package com.example.tasktest.ui.fibonacci


import androidx.lifecycle.*
import com.example.tasktest.domain.entity.PackageData
import com.example.tasktest.manager.NumberGeneratorManager
import com.example.tasktest.ui.model.NumberModel
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent.inject
import java.math.BigDecimal


class FibonacciNumbersViewModel : ViewModel() {

    private val numberGeneratorManager by inject<NumberGeneratorManager>(NumberGeneratorManager::class.java)

    private val _results = MutableLiveData<List<NumberModel>>()
    val results: LiveData<List<NumberModel>> = _results

    private var id = 1
    private var preLast: BigDecimal = BigDecimal.ZERO
    private var last: BigDecimal = BigDecimal.ONE

    private var dataList = mutableListOf<NumberModel>()
    private val viewModelJob = SupervisorJob()

    fun setFirstData() {
        dataList.add(NumberModel(BigDecimal.ZERO, 0))
        dataList.add(NumberModel(BigDecimal.ONE, 1))
        _results.postValue(dataList)
    }

    private val scope = CoroutineScope(Dispatchers.Default + viewModelJob)
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun launchDataLoad() {
        scope.launch {
            val packageData = numberGeneratorManager.getFibonacciNumbers(preLast, last, id)
            dataList = dataList.plus(packageData.dataList) as MutableList<NumberModel>
            _results.postValue(dataList)
            preLast = packageData.dataList[packageData.dataList.size - 2].number
            last = packageData.dataList[packageData.dataList.size - 1].number//
            id = packageData.lastId
        }
    }

}