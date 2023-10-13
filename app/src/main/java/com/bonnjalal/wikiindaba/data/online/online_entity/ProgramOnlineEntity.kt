package com.bonnjalal.wikiindaba.data.online.online_entity

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId

data class ProgramOnlineEntity(

    @DocumentId var id: String = "",
    var title: String = "",
    var authors: String =  "",
//    var date: Double,
//    @get:PropertyName("startTime")
//    @set:PropertyName("startTime")
    var startDate: Timestamp = Timestamp.now(),
//    @get:PropertyName("endTime")
//    @set:PropertyName("endTime")
    var endDate: Timestamp = Timestamp.now(),
    var room: String = "",
    var responsible: String = "",
    var slide: String = ""
)

