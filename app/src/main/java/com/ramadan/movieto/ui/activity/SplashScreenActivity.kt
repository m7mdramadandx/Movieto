@file:Suppress("DEPRECATION")

package com.ramadan.movieto.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.ramadan.movieto.MainActivity
import com.ramadan.movieto.R
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        appTheme()
        supportActionBar?.hide()
        window!!.navigationBarColor = Color.TRANSPARENT
        val animation = AnimationUtils.loadAnimation(this, R.anim.pop_enter)
        animation.duration = 800
        appImg.animation = animation
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1000)

    }

    private fun appTheme() {
//        when (localeHelper.getDefaultTheme(this)) {
//            "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            "night" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
//        }
//    }
    }
}