package com.example.tasktest.ui.main

import android.content.Context
import android.graphics.Color
import androidx.lifecycle.*
import com.example.tasktest.manager.NumberGeneratorManager
import com.example.tasktest.ui.recycler.DataModel
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent.inject


class PageViewModel () : ViewModel() {

    private val numberGeneratorManager by inject<NumberGeneratorManager>(NumberGeneratorManager::class.java)

    private val _index = MutableLiveData<Int>()
    val results = MutableLiveData<MutableList<DataModel>>()
    var preLast:Int = 0
    var Last:Int = 1
    var startNumber = 3

    fun setIndex(index: Int) {
        _index.value = index
    }
    private val viewModelJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     * Since we pass viewModelJob, you can cancel all coroutines
     * launched by uiScope by calling viewModelJob.cancel()
     */
    private val uiScope = CoroutineScope(Dispatchers.Default + viewModelJob)

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    fun launchDataLoad(EndNumber: Int) {
        if (_index.value==1)
        uiScope.launch {
            results.postValue(numberGeneratorManager.updateDataList(EndNumber)) // happens on the background
        }
    }




}





//class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(PageViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return PageViewModel(
//            ) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}