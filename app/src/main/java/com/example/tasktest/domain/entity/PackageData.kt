package com.example.tasktest.domain.entity

import com.example.tasktest.ui.model.NumberModel

data class PackageData(
    val dataList: List<NumberModel>,
    val lastId: Int
)
