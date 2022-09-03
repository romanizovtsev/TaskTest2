package com.example.tasktest.ui.recycler

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OnScrollListener(
    private val layoutManager: GridLayoutManager,
    val callback: () -> Unit
) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0
    private var loading = true
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

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

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + VISIBLE_THRESHOLD)) {
            callback()
            loading = true
        }
    }

    private companion object {
        private const val VISIBLE_THRESHOLD = 17
    }
}
