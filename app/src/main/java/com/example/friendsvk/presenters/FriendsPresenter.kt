package com.example.friendsvk.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.friendsvk.R
import com.example.friendsvk.models.FriendModel
import com.example.friendsvk.providers.FriendsProvider
import com.example.friendsvk.views.FriendsView
import kotlinx.android.synthetic.main.activity_friends.view.*

@InjectViewState
class FriendsPresenter : MvpPresenter<FriendsView>() {
    fun loadFriends() {
        viewState.startLoading()
        FriendsProvider(presenter = this).testLoadFriends(true)
    }

    fun friendsLoaded(friendsList: ArrayList<FriendModel>) {
        viewState.endLoading()
        if (friendsList.size == 0) {
            viewState.setupEmptyList()
            viewState.showError(R.string.friends_no_item)
        } else {
            viewState.setupFriendsList(friendsList = friendsList)
        }
    }
}