package com.bonnjalal.wikiindaba.data.online.online_entity

import com.google.firebase.firestore.DocumentId

data class AttendeeOnlineEntity(
    @DocumentId var id: String = "",
    var name: String = "",
    var email: String = "",
    var role: String = "",
    var username: String = "",
    var room: String = "",
    var phone: String = "",
    var imgUrl: String = ""
)
