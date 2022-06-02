package com.example.musicstreaming

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.commitNow
import com.example.musicstreaming.music.authentification.WelcomeFragment
import com.example.musicstreaming.music.musicstreaming.MainFragment
import com.example.musicstreaming.services.BackendService
import com.example.musicstreaming.services.retrofit.RetrofitBackendService
import com.example.musicstreaming.utils.readStringFromSharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkSharedPreferences()
    }

    private fun goToWelcomeScreen() {
        supportFragmentManager.commitNow {
            setReorderingAllowed(true)
            add(R.id.main_fragment_container, WelcomeFragment())
        }
    }

    private fun goToMusicHomeScreen() {
        supportFragmentManager.commitNow {
            setReorderingAllowed(true)
            add(R.id.main_fragment_container, MainFragment())
        }
    }

    private fun checkSharedPreferences() {
        val token = readStringFromSharedPreferences(applicationContext, "Token")
        Log.d("MainActivityToken", token.toString())
        if (token.isNullOrEmpty() || token.isNullOrBlank()) {
            goToWelcomeScreen()
            return
        }
        goToMusicHomeScreen()
    }
}