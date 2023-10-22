package com.bonnjalal.wikiindaba.data.online.online_entity

import com.google.firebase.firestore.DocumentId

data class OrganizerOnlineEntity(
    @DocumentId var id: String = "",
    var name: String = "",
    var username: String = "",
    var room: String = "",
    var phoneNumber: String = "",
    var image: String = ""
)
