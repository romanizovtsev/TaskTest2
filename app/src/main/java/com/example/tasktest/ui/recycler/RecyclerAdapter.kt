package com.example.tasktest.ui.recycler
import android.graphics.Color.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktest.R


class RecyclerAdapter() :
    ListAdapter<DataModel, RecyclerAdapter.ViewHolder>(DiffCallback) {
    public var a: Int = 0

    private var prev = true

    /* ViewHolder for Flower, takes in the inflated view and the onClick behavior. */
    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private var currentNumber: DataModel? = null
        private val NumberTextView: TextView = itemView.findViewById(R.id.number)


        /* Bind flower name and image. */
        fun bind(number: DataModel) {
            currentNumber = number
            NumberTextView.text = number.number.toString()

//            if (prev) {
//                NumberTextView.setBackgroundColor(GRAY)
//                prev=!prev
//                Log.e("Даем цвет","Цвет")
//            }
            Log.e("Красим", "id =${number.id}; число = ${number.number}")
            when (number.id)
            {
                0->NumberTextView.setBackgroundColor(GRAY)
                3->NumberTextView.setBackgroundColor(GRAY)
                1->NumberTextView.setBackgroundColor(WHITE)
                2->NumberTextView.setBackgroundColor(WHITE)
            }




        }
    }

    /* Creates and inflates view and return FlowerViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    /* Gets current flower and uses it to bind view. */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val number = getItem(position)
        holder.bind(number)

    }
}

object DiffCallback : DiffUtil.ItemCallback<DataModel>() {
    override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem == newItem
    }
}