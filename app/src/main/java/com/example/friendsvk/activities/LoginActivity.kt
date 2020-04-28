package com.example.friendsvk.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter

import com.example.friendsvk.presenters.LoginPresenter
import com.example.friendsvk.views.LoginView
import com.example.friendsvk.R
import com.github.rahatarmanahmed.cpv.CircularProgressView

class LoginActivity : MvpAppCompatActivity(), LoginView {

    private lateinit var mTextLoginHello: TextView
    private lateinit var mButtonEnter: Button
    private lateinit var mCpvWait: CircularProgressView

    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        mTextLoginHello = findViewById(R.id.text_login_hello)
        mButtonEnter = findViewById(R.id.button_login)
        mCpvWait = findViewById(R.id.cpv_login)

        mButtonEnter.setOnClickListener {
            loginPresenter.login(isSuccess = true)
        }
    }

    override fun startLoading() {
        mButtonEnter.visibility = View.GONE
        mCpvWait.visibility = View.VISIBLE
    }

    override fun endLoading() {
        mButtonEnter.visibility = View.VISIBLE
        mCpvWait.visibility = View.GONE
    }

    override fun showError(textResource: Int) {
        Toast.makeText(applicationContext, getString(textResource), Toast.LENGTH_SHORT).show()
    }

    override fun loadFriends() {
        startActivity(Intent(applicationContext, FriendsActivity::class.java))
    }
}