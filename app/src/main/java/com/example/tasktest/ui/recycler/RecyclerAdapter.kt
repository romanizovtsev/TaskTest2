package com.example.tasktest.ui.recycler

import android.graphics.Color.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktest.R
import com.example.tasktest.ui.model.NumberModel


class RecyclerAdapter :
    ListAdapter<NumberModel, RecyclerAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private var currentNumber: NumberModel? = null
        private val numberTextView: TextView = itemView.findViewById(R.id.number)


        fun bind(number: NumberModel) {
            currentNumber = number
            numberTextView.text = number.number.toString()

            when (number.id) {
                ZERO_POSITION -> numberTextView.setBackgroundColor(GRAY)
                THIRD_POSITION -> numberTextView.setBackgroundColor(GRAY)
                FIRST_POSITION -> numberTextView.setBackgroundColor(WHITE)
                SECOND_POSITION -> numberTextView.setBackgroundColor(WHITE)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val number = getItem(position)
        holder.bind(number)
    }

    private companion object {
        private const val ZERO_POSITION = 0
        private const val FIRST_POSITION = 1
        private const val SECOND_POSITION = 2
        private const val THIRD_POSITION = 3
    }
}

object DiffCallback : DiffUtil.ItemCallback<NumberModel>() {
    override fun areItemsTheSame(oldItem: NumberModel, newItem: NumberModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: NumberModel, newItem: NumberModel): Boolean {
        return oldItem == newItem
    }

}