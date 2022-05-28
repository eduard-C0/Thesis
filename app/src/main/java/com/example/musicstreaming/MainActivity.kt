package com.example.musicstreaming

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commitNow
import com.example.musicstreaming.music.authentification.WelcomeFragment
import com.example.musicstreaming.services.BackendService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BackendService().init("http://192.168.0.222:8080/","")
        goToMusicHomeScreen()
    }

    private fun goToMusicHomeScreen() {
        supportFragmentManager.commitNow {
            setReorderingAllowed(true)
            add(R.id.main_fragment_container, WelcomeFragment())
        }
    }
}