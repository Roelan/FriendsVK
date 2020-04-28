package com.example.friendsvk.models

data class FriendModel(
    var _name: String,
    var surname: String,
    var city: String?,
    var avatar: String?,
    var isOnline: Boolean
)