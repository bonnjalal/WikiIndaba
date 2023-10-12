package com.bonnjalal.wikiindaba.presentation.model

import com.google.firebase.Timestamp
import java.util.Date

data class Program(
    var id: String,
    var title: String,
    var authors: String,
    var startTime: Date,
    var endTime: Date,
    var room: String,
    var responsible: String,
    var slide: String
)
