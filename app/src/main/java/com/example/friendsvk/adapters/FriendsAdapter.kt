package com.example.friendsvk.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.friendsvk.R
import com.example.friendsvk.models.FriendModel
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class FriendsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mSourceList: ArrayList<FriendModel> = ArrayList()
    private var mFriendsList: ArrayList<FriendModel> = ArrayList()

    fun setUpFriends(friendsList: ArrayList<FriendModel>) {
        mSourceList.clear()
        mSourceList.addAll(friendsList)
        filter(query = "")

    }

    fun filter(query: String) {
        mFriendsList.clear()
        mSourceList.forEach {
            if (it._name.contains(query, ignoreCase = true) ||
                it.surname.contains(query, ignoreCase = true)
            ) {
                mFriendsList.add(it)
            } else {
                it.city?.let { city ->
                    if (city.contains(query, ignoreCase = true))
                        mFriendsList.add(it)
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FriendsViewHolder) {
            holder.bind(mFriendsList[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.cell_friend, parent, false)
        return FriendsViewHolder(itemView = itemView)
    }

    override fun getItemCount(): Int {
        return mFriendsList.count()
    }

    class FriendsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var mCimAvatar = itemView.findViewById<CircleImageView>(R.id.friends_circle_avatar)
        private var mTextUserName = itemView.findViewById<TextView>(R.id.friend_txt_name)
        private var mTxtCity = itemView.findViewById<TextView>(R.id.friend_txt_city)
        private var mImgIsOnline = itemView.findViewById<View>(R.id.friend_img_online)

        @SuppressLint("SetTextI18n")
        fun bind(friendModel: FriendModel) {

            friendModel.avatar?.let { url ->
                val picasso = Picasso.get()
                picasso.load(url).into(mCimAvatar)
              //  Picasso.with(itemView.context).load(url).into(mCimAvatar)
            }

            mTextUserName.text = "${friendModel._name} ${friendModel.surname}"
            mTxtCity.text = itemView.context.getString(R.string.friend_no_city)
            friendModel.city?.let { mTxtCity.text = friendModel.city }

            if (friendModel.isOnline) {
                mImgIsOnline.visibility = View.VISIBLE
            } else {
                mImgIsOnline.visibility = View.GONE
            }
        }
    }
}