package com.example.tasktest.ui.recycler

import android.graphics.Color
import androidx.room.PrimaryKey

data class DataModel(
    var number : Int,
    @PrimaryKey(autoGenerate = true)
    var id : Int//FIXME remove
)