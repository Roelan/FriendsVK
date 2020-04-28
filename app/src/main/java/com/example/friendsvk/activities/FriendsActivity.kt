package com.example.friendsvk.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.friendsvk.R
import com.example.friendsvk.adapters.FriendsAdapter
import com.example.friendsvk.models.FriendModel
import com.example.friendsvk.presenters.FriendsPresenter
import com.example.friendsvk.views.FriendsView
import com.github.rahatarmanahmed.cpv.CircularProgressView
import me.zhanghai.android.materialprogressbar.MaterialProgressBar

class FriendsActivity : MvpAppCompatActivity(), FriendsView {

    @InjectPresenter
    lateinit var friendsPresenter: FriendsPresenter

    private lateinit var mAdapter: FriendsAdapter
    private lateinit var mTxtNoItems: TextView

    private lateinit var mCpvWait: CircularProgressView
    private lateinit var mRvFriends: RecyclerView

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        mRvFriends = findViewById(R.id.recycler_friends)
        mTxtNoItems = findViewById(R.id.txt_friends_no_items)
        mCpvWait = findViewById(R.id.cpv_friends)
        val mTextSearch = findViewById<EditText>(R.id.txt_friends_search)
        mTextSearch.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mAdapter.filter(s.toString())
            }

        })

        friendsPresenter.loadFriends()
        mAdapter = FriendsAdapter()
        mRvFriends.adapter = mAdapter
        mRvFriends.layoutManager =
            LinearLayoutManager(applicationContext, OrientationHelper.VERTICAL, false)
        mRvFriends.setHasFixedSize(true)
    }

    // Friend view implementation
    override fun showError(textResource: Int) {
        mTxtNoItems.text = getString(textResource)
    }

    override fun setupEmptyList() {
        mRvFriends.visibility = View.GONE
        mTxtNoItems.visibility = View.VISIBLE
    }

    override fun setupFriendsList(friendsList: ArrayList<FriendModel>) {
        mRvFriends.visibility = View.VISIBLE
        mTxtNoItems.visibility = View.GONE

        mAdapter.setUpFriends(friendsList = friendsList)
    }

    override fun startLoading() {
        mCpvWait.visibility = View.VISIBLE
        mTxtNoItems.visibility = View.GONE
        mRvFriends.visibility = View.GONE
    }

    override fun endLoading() {
        mCpvWait.visibility = View.GONE
    }
}