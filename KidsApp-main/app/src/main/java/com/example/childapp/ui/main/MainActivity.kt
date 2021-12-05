package com.example.childapp.ui.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.view.WindowManager
import com.example.childapp.R
import com.example.childapp.db.room.AppDao
import com.example.childapp.ui.BaseActivity
import com.example.childapp.ui.splash.SplashActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var appDao: AppDao



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.colorWhite)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController!!.setSystemBarsAppearance(APPEARANCE_LIGHT_STATUS_BARS,  APPEARANCE_LIGHT_STATUS_BARS )
        }
    }

    override fun onStart() {
        super.onStart()
        if (appDao.getChild()== null) {
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
        }
    }
}