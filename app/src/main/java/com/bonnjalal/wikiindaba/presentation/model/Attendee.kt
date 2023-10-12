package com.bonnjalal.wikiindaba.presentation.model

data class Attendee(
    var id: String,
    var name: String,
    var email: String,
    var role: String,
    var username: String,
    var room: String,
    var phoneNumber: String,
    var imgUrl:String
)
