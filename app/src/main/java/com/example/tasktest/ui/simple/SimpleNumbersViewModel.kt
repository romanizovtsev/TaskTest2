package com.example.tasktest.ui.simple

import androidx.lifecycle.*
import com.example.tasktest.domain.entity.PackageData
import com.example.tasktest.manager.NumberGeneratorManager
import com.example.tasktest.ui.model.NumberModel
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent.inject
import java.math.BigDecimal


class SimpleNumbersViewModel : ViewModel() {

    private val numberGeneratorManager by inject<NumberGeneratorManager>(NumberGeneratorManager::class.java)

    private var startNumber: BigDecimal = BigDecimal.valueOf(3)
    private var dataList = mutableListOf<NumberModel>()
    private var id: Int = 0

    private val _results = MutableLiveData<List<NumberModel>>()
    val results: LiveData<List<NumberModel>> = _results

    private val viewModelJob = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + viewModelJob)

    fun setFirstData() {
        dataList.add(NumberModel(BigDecimal.ZERO, 0))
        _results.postValue(dataList)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun launchDataLoad() {
        scope.launch {
            startNumber = startNumber.plus(BigDecimal.valueOf(RANGE_OF_SIMPLE_NUMBERS))
            val packageData = numberGeneratorManager.getSimpleNumbers(startNumber, id)
            dataList = dataList.plus(packageData.dataList) as MutableList<NumberModel>
            _results.postValue(dataList)
            id = packageData.lastId
        }
    }

    private companion object {
        private const val RANGE_OF_SIMPLE_NUMBERS = 100L
    }
}