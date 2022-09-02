package com.example.tasktest.ui.recycler

import android.graphics.Color.BLUE
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class OnScrollListener(val layoutManager: LinearLayoutManager, val adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>, var dataList: MutableList<DataModel>) : RecyclerView.OnScrollListener() {
    var previousTotal = 0
    var loading = true
    val visibleThreshold = 10
    var firstVisibleItem = 0
    var visibleItemCount = 0
    var totalItemCount = 0
    var startNumber=3

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView.childCount
        totalItemCount = layoutManager.itemCount
        firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            val initialSize = dataList.size
            Log.e("Загружаю","${startNumber}-${startNumber}")
            updateDataList(dataList,startNumber+20)
            val updatedSize = dataList.size
            recyclerView.post { adapter.notifyItemRangeInserted(initialSize, updatedSize) }
            loading = true
        }
    }
    private fun updateDataList(dataList: MutableList<DataModel>, endNumber: Int) {
        var dataList2 =mutableListOf<DataModel>()
            var i = startNumber
            while (i < endNumber) {
                if (isPrimeBruteForce(i)) {
                    dataList2.add(DataModel(i, BLUE))
                }
                i += 2
            }
        this.dataList +=dataList2
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