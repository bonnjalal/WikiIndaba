package com.bonnjalal.wikiindaba.presentation.model

import com.google.firebase.Timestamp

data class Program(
    var id: String,
    var title: String,
    var authors: String,
    var startTime: Timestamp,
    var endTime: Timestamp,
    var room: String,
    var responsible: String,
    var slide: String
)
