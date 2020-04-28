package com.example.friendsvk.providers

import android.os.Handler
import com.example.friendsvk.models.FriendModel
import com.example.friendsvk.presenters.FriendsPresenter

class FriendsProvider(var presenter: FriendsPresenter) {
    fun testLoadFriends(hasFriends: Boolean) {
        Handler().postDelayed({
            var friendsList: ArrayList<FriendModel> = ArrayList()
            if (hasFriends) {
                val friend1 = FriendModel(
                    _name = "Nick",
                    surname = "Zadolbovich",
                    city = "Tallinn",
                    avatar = "https://sun9-72.userapi.com/c857224/v857224484/170223/iQHpA2DnFZ4.jpg",
                    isOnline = false
                )
                val friend2 = FriendModel(
                    _name = "Tema",
                    surname = "Zaharov",
                    city = "Saint-Petersburg",
                    avatar = "https://sun9-61.userapi.com/c627930/v627930874/22acf/iW8RDjfWs3E.jpg",
                    isOnline = false
                )
                val friend3 = FriendModel(
                    _name = "Anna",
                    surname = "Puzachova",
                    city = "Tallinn",
                    avatar = "https://sun9-52.userapi.com/c854120/v854120533/12e4d/qI6M7CqgsTI.jpg",
                    isOnline = true
                )
                friendsList.add(friend1)
                friendsList.add(friend2)
                friendsList.add(friend3)
            }

            presenter.friendsLoaded(friendsList = friendsList)
        }, 2000)
    }
}