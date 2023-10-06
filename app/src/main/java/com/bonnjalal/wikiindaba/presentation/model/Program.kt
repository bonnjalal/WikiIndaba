package com.bonnjalal.wikiindaba.presentation.model

data class Program(
    var id: Int,
    var title: String,
    var authors: List<String>,
    var date: Double,
    var time: Double,
    var hallNumber: String,
    var hallResponsible: String,
    var respImage: String
)
