package com.bonnjalal.wikiindaba.data.online.online_entity

import com.google.firebase.Timestamp

data class ProgramOnlineEntity(

    var id: String,
    var title: String,
    var authors: String,
//    var date: Double,
    var startTime: Timestamp,
    var endTime: Timestamp,
    var room: String,
    var responsible: String,
    var slide: String
)
