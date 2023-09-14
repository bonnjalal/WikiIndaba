package com.bonnjalal.wikiindaba.presentation.model

data class Program(
    var id: Int,
    var title: String,
    var authors: List<String>,
    var date: Double,
    var time: Double,
    var hall: String,
    var responsible: String,
    var resp_image: String
)
