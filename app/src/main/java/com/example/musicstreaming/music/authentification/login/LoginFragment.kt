package com.example.musicstreaming.music.authentification.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.musicstreaming.R
import com.example.musicstreaming.databinding.LoginFragmentBinding

class LoginFragment() : Fragment() {

    companion object{
        private const val TAG = "LoginFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<LoginFragmentBinding>(
            layoutInflater,
            R.layout.login_fragment,
            container,
            false
        ).root
    }
}